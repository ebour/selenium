package com.github.ebour.selenium.factories.api.browser;

import com.github.ebour.selenium.factories.api.*;
import com.github.ebour.selenium.factories.api.page.*;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractBrowser implements Browser
{
    private final static Logger LOG = Logger.getLogger(AbstractBrowser.class.getName());

    private WebDriver         webDriver;
    private ExpectedCondition pageReadyForTesting;
    private Service           webDriverService;
    private String            browserType;
    private BrowserProxy      browserProxy;
    private SeleniumService   seleniumService;
    private boolean           remote;
    private String            pageClassImpl;
    private boolean           enableProfiling;

    public AbstractBrowser(String browserType)
    {
        this.browserType = browserType;
    }

    public AbstractBrowser(BrowserType browserType)
    {
        this.browserType = browserType.toString();
    }

    @Override
    public Browser setSeleniumService(SeleniumService seleniumService)
    {
        this.seleniumService = seleniumService;
        return this;
    }

    @Override
    public String getUrl()
    {
        return webDriver.getCurrentUrl();
    }

    @Override
    public Boolean scrollTo(int x, int y) throws Exception
    {
        final int scrollY = getScrollY();
        execJavaScript("scroll(" + x + ",+" + y + ")");
        return getScrollY() > scrollY;
    }

    @Override
    public Page getPage() throws Exception
    {
        return DefaultPageFactoryImpl.buildPage(this);
    }

    @Override
    public Page goTo(String url) throws Exception
    {
        if(!isAtUrl(url))
        {
            if(enableProfiling)
            {
                getBrowserProxy().resetData();
            }

            webDriver.navigate().to(url);
            awaitPageReadiness();
        }

        return getPage();
    }

    @Override
    public WebElement getElement(final By selector) throws Exception
    {
        // execJavaScript(this, "window.focus();");
        final String title = webDriver.getTitle();
        try
        {
            return webDriver.findElement(selector);
        }
        catch (Exception e)
        {
            throw new NoSuchElementException("Unable to find element with selector: '" + selector.toString() + "' in page [title="+title+"]");
        }
    }

    @Override
    public WebElement getElement(final By contextSelector, final By selector) throws Exception
    {
        // execJavaScript(this, "window.focus();");
        final String title = webDriver.getTitle();
        try
        {
            WebElement displayedElement = null;

            // Find the first visible element as context
            final List<WebElement> contextualElements = webDriver.findElements(contextSelector);
            for(WebElement contextualElement: contextualElements)
            {
                if(contextualElement.isDisplayed())
                {
                    displayedElement = contextualElement;
                    break;
                }
            }
            return displayedElement.findElement(selector);
        }
        catch (Exception e)
        {
            throw new NoSuchElementException("Unable to find element with selector: '" + selector.toString() +"' within the context element: '"+ contextSelector.toString() +"' in page[title="+title+"]");
        }
    }

    @Override
    public List<WebElement> getElements(final By selector) throws Exception
    {
        // execJavaScript(this, "window.focus();");
        final String title = webDriver.getTitle();
        try
        {
            return webDriver.findElements(selector);
        }
        catch (Exception e)
        {
            throw new NoSuchElementException("Unable to find elements with selector: '" + selector.toString() + "' in page [title="+title+"]");
        }
    }

    @Override
    public List<WebElement> getElements(final By contextSelector, final By selector) throws Exception
    {
        // execJavaScript(this, "window.focus();");
        final String title = webDriver.getTitle();
        try
        {
            final WebElement contextualElement = webDriver.findElement(contextSelector);
            return contextualElement.findElements(selector);
        }
        catch(Exception e)
        {
            throw new NoSuchElementException("Unable to find elements with selector: '" + selector.toString() + "' within the context element: '" + contextSelector.toString() + "' in page [title=" + title + "]");
        }
    }

    @Override
    public Actions getActions()
    {
        return new Actions(webDriver);
    }

    @Override
    public Boolean isAtUrl(String url)
    {
        return getUrl().equals(url);
    }

    @Override
    public String getTitle()
    {
        return webDriver.getTitle();
    }

    @Override
    public Browser close() throws Exception
    {
        if(webDriver != null)
        {
            handleAlert();
            webDriver.quit();
            webDriver = null;
        }
        if(webDriverService != null && webDriverService.isRunning())
        {
            webDriverService.stop();
            webDriverService = null;
        }

        return this;
    }

    @Override
    public Browser setWebDriver(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        return this;
    }

    @Override
    public String getBrowserType()
    {
        return browserType;
    }

    private void handleAlert()
    {
        // PhantomJS does not implement the JSonWire protocol completely
        // One of the missing part is the native browser alert popup management
        if(BrowserType.equals(browserType, BrowserType.PhantomJS))
        {
            confirmAlertQuietly();
        }
    }

    private void confirmAlertQuietly()
    {
        try
        {
            webDriver.switchTo().alert().accept();
        }
        catch (Exception e)
        {
            // Ignore
        }
    }

    @Override
    public Browser setBrowserProxy(BrowserProxy proxy)
    {
        this.browserProxy = browserProxy;
        return this;
    }

    @Override
    public Dimension getSize()
    {
        return webDriver.manage().window().getSize();
    }

    @Override
    public Point getPosition()
    {
        return webDriver.manage().window().getPosition();
    }

    @Override
    public BufferedImage getScreenshot()
    {
        try
        {
            final Robot robot = new Robot();
            final Rectangle captureSize = new Rectangle();
            captureSize.setBounds(  getPosition().getX(),
                    getPosition().getY(),
                    getSize().getWidth(),
                    getSize().getHeight()  );

            return robot.createScreenCapture(captureSize);
        }
        catch (AWTException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAllCookies()
    {
        webDriver.manage().deleteAllCookies();
    }

    public boolean scrollToTop() throws Exception
    {
        return scrollTo(0, 0);
    }

    public boolean isRemote()
    {
        return remote;
    }

    @Override
    public void setSize(final String width, final String height)
    {
        if(width == null || height == null)
        {
            webDriver.manage().window().maximize();
        }
        else
        {
            final Dimension dimension = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
            webDriver.manage().window().setSize(dimension);
        }
    }

    @Override
    public Object execJavaScript(final String script) throws Exception
    {
        try
        {
            final JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return js.executeScript( script );
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public int getScrollY() throws Exception
    {
        return ((Long) execJavaScript("return window.scrollY;")).intValue();
    }

    @Override
    public Browser awaitCondition(final ExpectedCondition condition, final int timeoutInSec)
    {
        final WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSec);
        wait.until(condition);

        return this;
    }

    @Override
    public boolean awaitPageReadiness()
    {
        final int timeoutInSec = Integer.getInteger("maxPageLoadDuration", 30);
        try
        {
            final Page page = getPage();
            final ExpectedCondition readinessCondition = page.getReadinessCondition();
            if(readinessCondition != null)
            {
                final String title = webDriver.getTitle();
                final String logMsg = "Awaiting page [title=" + title + "] load end ";
                LOG.log(Level.INFO, logMsg);
                final long mark = System.currentTimeMillis();

                awaitCondition(readinessCondition, timeoutInSec);

                final int duration = new Double((System.currentTimeMillis() - mark) / 1000.0).intValue();
                LOG.log(Level.INFO, logMsg + "[Done] [Duration="+duration+"sec]");
            }
            return true;
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, "Page readiness condition not verified after : '" + timeoutInSec + "'sec", e);
            return false;

        }

    }

    @Override
    public void setPageClassImpl(String pageClassName)
    {
        this.pageClassImpl = pageClassName;
    }

    @Override
    public BrowserProxy getBrowserProxy()
    {
        return browserProxy;
    }

    @Override
    public boolean getEnableProfiling()
    {
        return enableProfiling;
    }

    @Override
    public String getPageClassImpl()
    {
        return pageClassImpl;
    }
}
