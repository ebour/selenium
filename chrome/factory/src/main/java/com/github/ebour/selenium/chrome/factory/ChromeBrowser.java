package com.github.ebour.selenium.chrome.factory;

import com.github.ebour.selenium.factories.api.browser.AbstractBrowser;
import com.github.ebour.selenium.factories.api.browser.BrowserType;

public class ChromeBrowser extends AbstractBrowser
{
    public ChromeBrowser()
    {
        super(BrowserType.Chrome);
    }
}
