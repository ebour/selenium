package com.github.ebour.selenium.firefox.factory;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.browser.BrowserFactory;
import com.github.ebour.selenium.factories.api.browser.BrowserFactoryHelper;
import com.github.ebour.selenium.factories.api.browser.BrowserInitializationException;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ebour on 20/06/15.
 */
public class FirefoxBrowserFactory implements BrowserFactory
{
    private final static Logger LOG = Logger.getLogger(FirefoxBrowserFactory.class.getName());

    @Override
    public Browser instantiate(final String browserType, final String seleniumHubUrl, final boolean browserIsRemote) throws BrowserInitializationException
    {
        Browser browser = new FirefoxBrowser();

        final FirefoxProfile profile = new FirefoxProfile();
        final DesiredCapabilities capabilities = new DesiredCapabilities();

        try
        {
            final FirefoxBinary firefoxBinary = (BrowserFactoryHelper.isBrowserPathDefined() ?
                    new FirefoxBinary() : new FirefoxBinary(new File(BrowserFactoryHelper.getBrowserPath())));


            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);

            browser.setWebDriver(new FirefoxDriver(firefoxBinary, profile, capabilities));

            return browser;
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new BrowserInitializationException(browser.getBrowserType(), e.getMessage());
        }
    }


}
