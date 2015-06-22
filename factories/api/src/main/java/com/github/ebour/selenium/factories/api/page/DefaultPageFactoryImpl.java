package com.github.ebour.selenium.factories.api.page;

import com.github.ebour.selenium.factories.api.browser.Browser;

/**
 * Created by ebour on 21/06/15.
 */
public class DefaultPageFactoryImpl
{
    public static Page buildPage(Browser browser, String pageClassName) throws Exception
    {
        return (Page) Class.forName(pageClassName).getConstructor(Browser.class).newInstance(browser);
    }

    public static String getPageClassName()
    {
        return System.getProperty("pageClassName", "");
    }

    public static Page buildPage(Browser browser) throws Exception
    {
        return buildPage(browser, getPageClassName());
    }
}
