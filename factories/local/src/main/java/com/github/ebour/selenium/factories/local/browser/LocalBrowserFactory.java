package com.github.ebour.selenium.factories.local.browser;

import com.github.ebour.selenium.factories.api.browser.BrowserFactoryFactory;
import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.browser.BrowserFactory;
import com.github.ebour.selenium.factories.api.browser.BrowserInitializationException;
import com.github.ebour.selenium.factories.api.browser.BrowserStopper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalBrowserFactory implements BrowserFactory
{
    private final static Logger LOG = Logger.getLogger(LocalBrowserFactory.class.getName());

    @Override
    public Browser instantiate(String browserType, String seleniumHubUrl, boolean browserIsRemote) throws BrowserInitializationException
    {
        try
        {
            LOG.log(Level.INFO, "Instantiating browser: '" + browserType + "'");
            final long mark = System.currentTimeMillis();

            final BrowserFactory browserFactory = BrowserFactoryFactory.instantiateBrowserFactory(browserType);
            final Browser browser = browserFactory.instantiate(browserType, seleniumHubUrl, browserIsRemote);

            final int duration = new Double((System.currentTimeMillis() - mark) / 1000.0).intValue();
            LOG.log(Level.INFO, "Instantiating browser: '" + browserType + "' [Done] [Duration=" + duration + "sec]");

            Runtime.getRuntime().addShutdownHook(new BrowserStopper(browser));

            return browser;

        }
        catch(Exception e)
        {
            LOG.log(Level.SEVERE, "Instantiating browser: '" + browserType + "' [FAILED]");
            throw new BrowserInitializationException("", "Instantiating browser: '" + browserType + "' [FAILED]");
        }
    }

}
