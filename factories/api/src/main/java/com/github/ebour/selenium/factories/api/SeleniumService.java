package com.github.ebour.selenium.factories.api;

import org.openqa.selenium.remote.service.DriverService;

/**
 * Created by su on 06/02/14.
 */
public class SeleniumService implements Service
{
    private final DriverService driverService;

    public SeleniumService(DriverService driverService)
    {
        this.driverService = driverService;
    }

    @Override
    public boolean isRunning()
    {
        return driverService.isRunning();
    }

    @Override
    public void start() throws Exception
    {
        driverService.start();
        Runtime.getRuntime().addShutdownHook(new ServiceStopper(this));
    }

    @Override
    public void stop() throws Exception
    {
        driverService.stop();
    }
}
