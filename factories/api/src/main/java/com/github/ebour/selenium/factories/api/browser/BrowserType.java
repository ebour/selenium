package com.github.ebour.selenium.factories.api.browser;

public enum BrowserType
{
    Firefox, Chrome, PhantomJS;

    public static boolean equals(String browserType, BrowserType browserTypeType)
    {
        return String.valueOf(browserTypeType).equalsIgnoreCase(browserType);
    }
}
