package com.github.ebour.selenium.phantomjs.factory;

import com.github.ebour.selenium.factories.api.*;
import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.browser.BrowserFactory;
import com.github.ebour.selenium.factories.api.browser.BrowserFactoryHelper;
import com.github.ebour.selenium.factories.api.browser.BrowserInitializationException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PhantomjsBrowserFactory implements BrowserFactory
{
    private final static Logger LOG = Logger.getLogger(PhantomjsBrowserFactory.class.getCanonicalName());

    @Override
    public Browser instantiate(final String browserType, final String seleniumHubUrl, final boolean browserIsRemote) throws BrowserInitializationException
    {
        Browser browser = new PhantomjsBrowser();

        try
        {
            final DesiredCapabilities cfg = DesiredCapabilities.phantomjs();
            cfg.setJavascriptEnabled(true);
            if(BrowserFactoryHelper.isWindows())
            {
                cfg.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, System.getProperty("browserOptions", "").split(","));
            }
            cfg.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, new String[]{  "--logFile="+System.getProperty("browserLogFile", "phantomjs.log"),
                    "--port="+System.getProperty("browserPort", "9091") });
            cfg.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, BrowserFactoryHelper.getBrowserPath());

            final DriverService service = PhantomJSDriverService.createDefaultService(cfg);

            browser.setSeleniumService(new SeleniumService(service));
            browser.setWebDriver      ( new PhantomJSDriver((PhantomJSDriverService) service, cfg) );

            return browser;
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new BrowserInitializationException(browser.getBrowserType(), e.getMessage());
        }
    }
}
