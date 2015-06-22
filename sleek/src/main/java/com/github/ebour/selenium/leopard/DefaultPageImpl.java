package com.github.ebour.selenium.leopard;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import com.github.ebour.selenium.factories.api.page.Page;
import org.openqa.selenium.By;

public class DefaultPageImpl implements Page
{
    private final Browser browser;

    public DefaultPageImpl(Browser browser)
    {
        this.browser = browser;
    }

    @Override
    public String getUrl()
    {
        return browser.getUrl();
    }

    @Override
    public Page open(String url) throws Exception
    {
        browser.goTo(url);
        return browser.getPage();
    }

    @Override
    public Page click(By selector) throws Exception
    {
        browser.getElement(selector).click();
        return browser.getPage();
    }

    @Override
    public String getTitle()
    {
        return browser.getTitle();
    }

    @Override
    public Element getElement(By selector)
    {
        final DefaultElementFactoryImpl defaultElementFactory = new DefaultElementFactoryImpl();
        return defaultElementFactory.newElement(browser, selector);
    }

    @Override
    public Browser getBrowser()
    {
        return browser;
    }

    @Override
    public int getYOffset()
    {
        return 0;
    }

    public boolean isReady()
    {
        return true;
    }
}
