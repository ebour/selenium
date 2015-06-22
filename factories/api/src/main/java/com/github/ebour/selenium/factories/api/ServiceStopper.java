package com.github.ebour.selenium.factories.api;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ebour on 08/02/14.
 */
public class ServiceStopper extends Thread
{
    private static final Logger LOG = Logger.getLogger(ServiceStopper.class.getName());

    private final Service service;

    public ServiceStopper(Service service)
    {
        this.service = service;
    }

    public void run()
    {
        if(service.isRunning())
        {
            try
            {
                service.stop();
            }
            catch(Exception e)
            {
                LOG.log(Level.SEVERE, "Unable to stop service: "+service.getClass().getSimpleName()+"!", e);
            }
        }
    }
}
