package com.github.ebour.selenium.factories.api.element;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import org.openqa.selenium.By;

/**
 * Created by ebour on 22/06/15.
 */
public interface ElementFactory
{
    Element newElement(Browser browser, By selector);

}
