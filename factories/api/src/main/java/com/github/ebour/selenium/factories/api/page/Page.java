package com.github.ebour.selenium.factories.api.page;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import org.browsermob.core.har.Har;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface Page
{
    Long getLoadDuration();

    /**
     * Retrieve the current page url
     *
     * @return
     */
    String getUrl();

    /**
     * Open the page on the browser at the given url
     *
     * @param url
     * @return
     * @throws Exception
     */
    Page open(String url) throws Exception;

    /**
     * Retrieve the page title
     *
     * @return
     */
    String getTitle();

    /**
     * Retrieve the browser that renders this page
     *
     * @return
     */
    Browser getBrowser();

    /**
     * When clicking on an element in the page
     * a scroll is performed to put the element at the top of the view port.
     *
     * The yOffset used to prevent an element from being hidden behind a static header.
     *
     * @return the number of pixel that is fixed at the top the page
     */
    int getYOffset();

    boolean isReady() throws Exception;

    ExpectedCondition getReadinessCondition() throws Exception;

}
