package com.github.ebour.selenium.factories.api.element;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import org.openqa.selenium.By;

public interface ElementFactory
{
    Element newElement(Browser browser, By selector);

}
