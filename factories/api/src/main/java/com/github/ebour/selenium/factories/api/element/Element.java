package com.github.ebour.selenium.factories.api.element;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Element
{
    Page getPage() throws Exception;

    By getSelector();

    Page click() throws Exception;

    Page click(boolean doScroll, boolean doAwaitPageToBeReady) throws Exception;

    Page mouseOver() throws Exception;

    Page select(String option) throws Exception;

    Page type(String value) throws Exception;

    Page type(String value, boolean doClear) throws Exception;

    WebElement getWebElement() throws Exception;

    List<WebElement> getWebElements() throws Exception;

    String getAttribute(String attributeName) throws Exception;

    Browser getBrowser();

    String getPageUrl();

    String getText() throws Exception;

    String getId() throws Exception;

    Boolean isDisplayed() throws Exception;

    Boolean exists();

    By getContext();

    Element setContext(By context);
}
