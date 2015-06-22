package com.github.ebour.selenium.factories.api.browser;

/**
 * Created by ebour on 20/06/15.
 */
public enum BrowserType
{
    Firefox, Chrome, PhantomJS;

    public static boolean equals(String browserType, BrowserType browserTypeType)
    {
        return String.valueOf(browserTypeType).equalsIgnoreCase(browserType);
    }
}
