package com.github.ebour.selenium.factories.api.browser;

/**
 * Created by ebour on 20/06/15.
 */
public class BrowserInitializationException extends Exception
{
    public BrowserInitializationException(String msg)
    {
        super(msg);
    }

    public BrowserInitializationException(String browserType, String msg)
    {
        super("Unable to instantiate browser: "+browserType + ". Reason: " + msg);
    }
}
