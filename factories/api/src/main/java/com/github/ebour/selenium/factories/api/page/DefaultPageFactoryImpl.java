package com.github.ebour.selenium.factories.api.page;

import com.github.ebour.selenium.factories.api.browser.Browser;

public class DefaultPageFactoryImpl
{
    public static Page buildPage(Browser browser, String pageClassName) throws Exception
    {
        return (Page) Class.forName(pageClassName).getConstructor(Browser.class).newInstance(browser);
    }

    public static Page buildPage(Browser browser) throws Exception
    {
        return buildPage(browser, browser.getPageClassImpl());
    }
}
