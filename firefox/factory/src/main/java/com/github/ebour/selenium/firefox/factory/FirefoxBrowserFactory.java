package com.github.ebour.selenium.firefox.factory;

import com.github.ebour.selenium.factories.api.browser.*;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirefoxBrowserFactory implements BrowserFactory
{
    private final static Logger LOG = Logger.getLogger(FirefoxBrowserFactory.class.getName());

    private boolean enableProfiling;

    public Browser instantiate() throws BrowserInitializationException
    {
        return instantiate(BrowserType.Firefox.toString(), "", false);
    }

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

            if(enableProfiling)
            {
                ProxyServer proxy = newProxy(profile, capabilities);

                // create a simple proxy manager
                final FirefoxBrowserProfilingProxy profilingProxy = new FirefoxBrowserProfilingProxy(proxy);

                // store the proxy in the browser to be able to export har file when closing the browser
                browser.setBrowserProxy(profilingProxy);
            }

            browser.setWebDriver(new FirefoxDriver(firefoxBinary, profile, capabilities));

            return browser;
        }
        catch (Exception e)
        {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new BrowserInitializationException(browser.getBrowserType(), e.getMessage());
        }
    }

    private ProxyServer newProxy(final FirefoxProfile profile, final DesiredCapabilities capabilities) throws Exception
    {
        final Integer proxyPort = Integer.getInteger("SELENIUM_PROXY_PORT", 4444);

        final ProxyServer server = new ProxyServer(proxyPort);
        server.start();

        // captures the mouse movements and navigations
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);

        // get the Selenium proxy object
        final Proxy proxy = server.seleniumProxy();

        // set network proxy definition according to the proxy server created above
        profile.setPreference("network.proxy.http", "localhost");
        profile.setPreference("network.proxy.http_port", proxyPort);
        profile.setPreference("network.proxy.ssl", "localhost");
        profile.setPreference("network.proxy.ssl_port", proxyPort);
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.no_proxies_on", "");
        // profile.setProxyPreferences(proxy);

        // configure it as a desired capability
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        return server;
    }


    public void setEnableProfiling(boolean enableProfiling)
    {
        this.enableProfiling = enableProfiling;
    }

    public boolean getEnableProfiling()
    {
        return this.enableProfiling;
    }


}
