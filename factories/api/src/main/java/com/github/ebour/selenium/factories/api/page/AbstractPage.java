package com.github.ebour.selenium.factories.api.page;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.logging.Logger;

/**
 * Created by ebour on 25/06/15.
 */
public abstract class AbstractPage implements Page
{
    private final static Logger LOG = Logger.getLogger(AbstractPage.class.getName());

    private final Browser            browser;
    private       PageReadyCondition pageReadinessCondition;

    public AbstractPage(Browser browser)
    {
        this.browser = browser;
    }

    @Override
    public Long getLoadDuration()
    {
        final String pageTitle = getTitle();
        return browser.getBrowserProxy().getPageLoadDuration(pageTitle);
    }

    @Override
    public int getYOffset()
    {
        return 0;
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
    public String getTitle()
    {
        return browser.getTitle();
    }
    
    @Override
    public Browser getBrowser()
    {
        return browser;
    }

    @Override
    public boolean isReady() throws Exception
    {
        return browser.awaitPageReadiness();
    }

    @Override
    public abstract ExpectedCondition getReadinessCondition() throws Exception;

}
