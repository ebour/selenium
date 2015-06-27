package com.github.ebour.selenium.factories.api.browser;

import org.browsermob.core.har.Har;

public interface BrowserProxy
{
    void resetData();

    Long getPageLoadDuration(String pageTitle);
}
