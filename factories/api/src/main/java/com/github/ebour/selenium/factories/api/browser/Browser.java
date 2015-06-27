package com.github.ebour.selenium.factories.api.browser;

import com.github.ebour.selenium.factories.api.page.Page;
import com.github.ebour.selenium.factories.api.SeleniumService;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Browser
{
    Browser close() throws Exception;

    Browser setWebDriver(WebDriver webDriver);

    String getBrowserType();

    Browser setSeleniumService(SeleniumService seleniumService);

    String getUrl();

    Boolean scrollTo(int x, int y) throws Exception;

    Page getPage() throws Exception;

    Page goTo(String url) throws Exception;

    WebElement getElement(By selector) throws Exception;

    WebElement getElement(By context, By selector) throws Exception;

    List<WebElement> getElements(By selector) throws Exception;

    List<WebElement> getElements(By context, By selector) throws Exception;

    Actions getActions();

    Boolean isAtUrl(String url);

    String getTitle();

    Browser setBrowserProxy(BrowserProxy proxy);

    Dimension getSize();

    Point getPosition();

    BufferedImage getScreenshot();

    void setSize(String width, String height);

    Object execJavaScript(String script) throws Exception;

    int getScrollY() throws Exception;

    Browser awaitCondition(ExpectedCondition condition, int timeoutInSec) throws Exception;

    boolean awaitPageReadiness() throws Exception;

    void setPageClassImpl(String pageClassName);

    BrowserProxy getBrowserProxy();

    boolean getEnableProfiling();

    String getPageClassImpl();
}
