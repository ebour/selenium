package com.github.ebour.selenium.factories.api.browser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserStopper extends Thread
{
    private final static Logger LOG = Logger.getLogger(BrowserStopper.class.getName());

    private final Browser browser;

    public BrowserStopper(Browser browser)
    {
        this.browser = browser;
    }

    @Override
    public void run()
    {
        if(browser != null)
        {
            try
            {
                browser.close();
            }
            catch (Exception e)
            {
                LOG.log(Level.SEVERE, "Exception occurred during browser: " + browser + " shutdown. Some processes may still be alive in your system!", e);
            }
        }
    }
}
