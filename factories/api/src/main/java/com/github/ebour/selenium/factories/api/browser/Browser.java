package com.github.ebour.selenium.factories.api.browser;

import com.github.ebour.selenium.factories.api.page.Page;
import com.github.ebour.selenium.factories.api.SeleniumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by ebour on 20/06/15.
 */
public interface Browser
{
    Browser close() throws Exception;

    Browser setWebDriver(WebDriver webDriver);

    String getBrowserType();

    Browser setSeleniumService(SeleniumService seleniumService);

    String getUrl();

    Boolean scrollTo(int x, int y) throws Exception;

    Page getPage() throws Exception;

    Page goTo(String url) throws Exception;

    WebElement getElement(By selector) throws Exception;

    WebElement getElement(By context, By selector) throws Exception;

    List<WebElement> getElements(By selector) throws Exception;

    List<WebElement> getElements(By context, By selector) throws Exception;

    Actions getActions();

    Boolean isAtUrl(String url);

    String getTitle();

    Browser awaitCondition(ExpectedCondition condition, int timeoutInSec);
}
