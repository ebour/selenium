package com.github.ebour.selenium.factories.api.browser;

/**
 * Created by ebour on 20/06/15.
 */
public class BrowserFactoryFactory
{
    public static BrowserFactory instantiateBrowserFactory(String browserType) throws BrowserFactoryFactoryInitializationException
    {
        String packageName = BrowserFactoryFactory.class.getPackage().getName();
        String browserFactoryClassName = "";
        switch (browserType)
        {
            case "Firefox":
                browserFactoryClassName = packageName + ".FirefoxBrowserFactory";
                break;

            case "Chrome":
                browserFactoryClassName = packageName + ".ChromeBrowserFactory";
                break;

            case "PhantomJS":
                browserFactoryClassName = packageName + ".PhantomjsBrowserFactory";
                break;

            default:
                throw new BrowserFactoryFactoryInitializationException("Unable to find any browser factory for browser with type: " + browserType);
        }

        return newInstanceOf(browserFactoryClassName);
    }

    private static class BrowserFactoryFactoryInitializationException extends Exception
    {
        public BrowserFactoryFactoryInitializationException(String msg)
        {
            super(msg);
        }
    }

    private static BrowserFactory newInstanceOf(final String browserFactoryClassName) throws BrowserFactoryFactoryInitializationException
    {
        try
        {
            return (BrowserFactory) Class.forName(browserFactoryClassName).getConstructor().newInstance();
        }
        catch(Exception e)
        {
            throw new BrowserFactoryFactoryInitializationException("Unable to instantiate browser factory: " + browserFactoryClassName);
        }
    }

}
