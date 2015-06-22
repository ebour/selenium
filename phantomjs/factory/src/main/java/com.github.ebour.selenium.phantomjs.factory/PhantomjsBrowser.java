package com.github.ebour.selenium.phantomjs.factory;

import com.github.ebour.selenium.factories.api.browser.AbstractBrowser;
import com.github.ebour.selenium.factories.api.browser.BrowserType;

/**
 * Created by ebour on 20/06/15.
 */
public class PhantomjsBrowser extends AbstractBrowser
{
    public PhantomjsBrowser()
    {
        super(BrowserType.PhantomJS);
    }
}
