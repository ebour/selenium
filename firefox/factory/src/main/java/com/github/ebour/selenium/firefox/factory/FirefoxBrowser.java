package com.github.ebour.selenium.firefox.factory;

import com.github.ebour.selenium.factories.api.browser.AbstractBrowser;
import com.github.ebour.selenium.factories.api.browser.BrowserType;

/**
 * Created by ebour on 20/06/15.
 */
public class FirefoxBrowser extends AbstractBrowser
{
    public FirefoxBrowser()
    {
        super(BrowserType.Firefox);
    }
}
