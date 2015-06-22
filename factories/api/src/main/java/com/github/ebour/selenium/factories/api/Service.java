package com.github.ebour.selenium.factories.api;

public interface Service
{
    boolean isRunning();

    void start() throws Exception;

    void stop() throws Exception;
}
