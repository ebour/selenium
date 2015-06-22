package com.github.ebour.selenium.factories.api;

/**
 * Created by ebour on 20/06/15.
 */
public interface Service
{
    boolean isRunning();

    void start() throws Exception;

    void stop() throws Exception;
}
