package com.github.ebour.selenium.factories.api.page;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import org.openqa.selenium.By;

/**
 * Created by ebour on 20/06/15.
 */
public interface Page
{
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
     * Search for an element given a selector {@link org.openqa.selenium.By}.
     *
     * In order to interact with any element, first the page is scrolled
     * to bring the element in the viewport
     *
     * @param selector
     * @return
     * @throws Exception
     */
    Page click(By selector) throws Exception;

    /**
     * Retrieve the page title
     *
     * @return
     */
    String getTitle();

    /**
     * Retrieve an element in the page
     *
     * @param selector
     * @return
     */
    Element getElement(By selector);

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

    boolean isReady();
}
