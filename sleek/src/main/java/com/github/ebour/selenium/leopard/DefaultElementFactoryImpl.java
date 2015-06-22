package com.github.ebour.selenium.leopard;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import com.github.ebour.selenium.factories.api.element.ElementFactory;
import org.openqa.selenium.By;

/**
 * Created by ebour on 21/06/15.
 */
public class DefaultElementFactoryImpl implements ElementFactory
{
    public Element buildElement(Browser browser, By selector, String pageClassName)
    {
        try
        {
            return (Element) Class.forName(pageClassName).getConstructor(Browser.class, By.class).newInstance(browser, selector);
        }
        catch(Exception e)
        {
            return new DefaultElementImpl(browser, selector);
        }
    }

    public Element newElement(Browser browser, By selector)
    {
        return buildElement(browser, selector, DefaultElementImpl.class.getCanonicalName());
    }

}
