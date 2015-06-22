package com.github.ebour.selenium.factories.api.browser;


import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.browser.BrowserInitializationException;

public interface BrowserFactory
{
    public Browser instantiate(final String browserType, final String seleniumHubUrl, final boolean browserIsRemote) throws BrowserInitializationException;

}
