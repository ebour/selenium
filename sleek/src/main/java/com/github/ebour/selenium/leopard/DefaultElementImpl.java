package com.github.ebour.selenium.leopard;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import com.github.ebour.selenium.factories.api.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.logging.Logger;

public class DefaultElementImpl implements Element
{
    private final static Logger LOG = Logger.getLogger(DefaultElementImpl.class.getCanonicalName());

    private By context = null;
    private final By      selector;
    private final Browser browser;
    private final String  pageUrl;
    private       Page    page;

    public DefaultElementImpl(final Browser browser, final By selector)
    {
        this.browser = browser;
        this.selector = selector;
        this.pageUrl = browser.getUrl();
        this.page = getPage();
    }

    @Override
    public Page getPage()
    {
        return new DefaultPageImpl(browser);
    }

    @Override
    public By getSelector()
    {
        return selector;
    }

    @Override
    public Page click() throws Exception
    {
        return click(true, true);
    }

    @Override
    public Page click(boolean doScroll, boolean doAwaitPageToBeReady) throws Exception
    {
        final WebElement element = getWebElement();

        if(doScroll)
        {
            final int yOffset = browser.getPage().getYOffset();
            browser.scrollTo(0, element.getLocation().getY() - yOffset);
            Thread.sleep(300);
        }

        click0(element);

        if(doAwaitPageToBeReady)
        {
            page.isReady();
        }
        return this.page;
    }

    @Override
    public Page mouseOver() throws Exception
    {
        final WebElement element = getWebElement();

        mouseOver0(element);

        return this.page;
    }

    @Override
    public Page select(final String option) throws Exception
    {
        final WebElement element = getWebElement();

        select0(element, option);

        return this.page;
    }

    @Override
    public Page type(final String value) throws Exception
    {
        type0(value, true);

        return this.page;
    }

    @Override
    public Page type(final String value, final boolean doClear) throws Exception
    {
        type0(value, doClear);

        return this.page;
    }

    @Override
    public WebElement getWebElement() throws Exception
    {
        browser.goTo(getPageUrl());
		if(hasContext())
		{
			return browser.getElement(getContext(), getSelector());
		}
		else
		{
			return browser.getElement(getSelector());
		}

    }	

	@Override
    public List<WebElement> getWebElements() throws Exception
	{
		 browser.goTo(getPageUrl());
		if(hasContext())
		{
			return browser.getElements(getContext(), getSelector());
		}
		else
		{
			return browser.getElements(getSelector());
		}
	}
	
    @Override
    public String getAttribute(final String attributeName) throws Exception
    {
        return getWebElement().getAttribute(attributeName);
    }

    @Override
    public Browser getBrowser()
    {
        return browser;
    }

    @Override
    public String getPageUrl()
    {
        return pageUrl;
    }

    @Override
    public String getText() throws Exception
    {
        String text;

        if(isInput())
        {
            text = getAttribute("value");
        }
        else
        {
            text = getWebElement().getText();
        }

        return text;
    }

    @Override
    public String getId() throws Exception
    {
        return getWebElement().getAttribute("id");
    }

	@Override
    public Boolean isDisplayed() throws Exception
	{
		return getWebElement().isDisplayed();
	}

	@Override
    public Boolean exists()
	{
		Boolean elementExists = true;
		try
		{
			getWebElement();
		}
		catch (Exception e)
		{
			elementExists = false;
		}

		return elementExists;
	}
	
	@Override
    public By getContext()
	{
		return context;
	}

	@Override
    public Element setContext(By context)
	{
		this.context = context;
        return this;
	}

    public String toString()
    {
        return getSelector().toString();
    }
	

// - Private area

    private void select0(final WebElement element, final String optionToSelect)
    {
        element.click();
        for(WebElement option : element.findElements(By.tagName("option")))
        {
            if(option.getText().contentEquals(optionToSelect))
            {
                element.click();
            }
        }
    }

    private void click0(final WebElement element)
    {
        try
        {
            element.click();

        }
        catch (Exception e)
        {
            // Just a retry if this is still true
            // then the exception will be raised again
            // same causes => same consequences
            tictac();

            element.click();
        }

        tictac();
    }

    private void type0(final String value, boolean doClear) throws Exception
    {
        final WebElement field = getWebElement();

        try
        {
            if(doClear)
            {
                field.clear();
            }
            field.sendKeys(value);
        }
        catch (Exception e)
        {
            // Just a retry if this is still true
            // then the exception will be raised again
            // same causes => same consequences
            tictac();

            if(doClear)
            {
                field.clear();
            }
            field.sendKeys(value);
        }

        tictac();
    }

    private void mouseOver0(final WebElement element) throws Exception
    {
        final Actions actions = getBrowser().getActions();

        try
        {
            actions.moveToElement(element).perform();
        }
        catch (Exception e)
        {
            tictac();

            actions.moveToElement(element).perform();
        }

        tictac();
    }

    private void tictac()
    {
        try
        {
            final int duration = 2000;
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            // Ignore gently
        }
    }

    private boolean isInput() throws Exception
    {
        final String tagName = getWebElement().getTagName();
        return "input".equalsIgnoreCase(tagName) || "textarea".equalsIgnoreCase(tagName);
    }
	  
	private boolean hasContext() 
	{
		return context!=null;
	}

}
