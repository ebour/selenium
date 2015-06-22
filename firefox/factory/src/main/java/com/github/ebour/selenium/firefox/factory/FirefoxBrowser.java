package com.github.ebour.selenium.firefox.factory;

import com.github.ebour.selenium.factories.api.browser.AbstractBrowser;
import com.github.ebour.selenium.factories.api.browser.BrowserType;

public class FirefoxBrowser extends AbstractBrowser
{
    public FirefoxBrowser()
    {
        super(BrowserType.Firefox);
    }
}
