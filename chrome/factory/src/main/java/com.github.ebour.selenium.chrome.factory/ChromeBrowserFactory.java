package com.github.ebour.selenium.chrome.factory;

import com.github.ebour.selenium.factories.api.*;
import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.browser.BrowserFactory;
import com.github.ebour.selenium.factories.api.browser.BrowserFactoryHelper;
import com.github.ebour.selenium.factories.api.browser.BrowserInitializationException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ebour on 20/06/15.
 */
public class ChromeBrowserFactory implements BrowserFactory
{
    private final static Logger LOG = Logger.getLogger(ChromeBrowserFactory.class.getName());

    @Override
    public Browser instantiate(String browserType, String seleniumHubUrl, boolean browserIsRemote) throws BrowserInitializationException
    {
        final Browser browser = new ChromeBrowser();

        try
        {
            final String driver = BrowserFactoryHelper.getDriverPath();
            // String binary = getBrowserPath();


            // ImmutableMap<String, String> env = new ImmutableMap.Builder<String, String>().build();
            final ChromeDriverService.Builder serviceBuilder = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(driver))
                    .usingAnyFreePort();

            // serviceBuilder.withEnvironment( env );
            final DriverService service = serviceBuilder.build();

            final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            // capabilities.setCapability( "chrome.binary", binary );
            // capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));

            browser.setSeleniumService(new SeleniumService(service));
            browser.setWebDriver      ( new ChromeDriver( (ChromeDriverService) service, capabilities ) );

            return browser;
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new BrowserInitializationException(browser.getBrowserType(), e.getMessage());
        }
    }
}
