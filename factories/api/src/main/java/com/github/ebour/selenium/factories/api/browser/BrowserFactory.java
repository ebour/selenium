package com.github.ebour.selenium.factories.api.browser;


import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.browser.BrowserInitializationException;

/**
 * Created by ebour on 20/06/15.
 */
public interface BrowserFactory
{
    public Browser instantiate(final String browserType, final String seleniumHubUrl, final boolean browserIsRemote) throws BrowserInitializationException;

}
