package com.github.ebour.selenium.sleek;

import com.github.ebour.selenium.factories.api.browser.Browser;
import com.github.ebour.selenium.factories.api.element.Element;
import com.github.ebour.selenium.factories.api.page.AbstractPage;
import com.github.ebour.selenium.factories.api.page.Page;
import com.github.ebour.selenium.factories.api.page.PageReadyCondition;
import com.github.ebour.selenium.factories.api.page.UndefinedPageReadyClassNameException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultPageImpl extends AbstractPage
{
    private final static Logger LOG = Logger.getLogger(DefaultPageImpl.class.getName());
    private PageReadyCondition pageReadinessCondition;

    public DefaultPageImpl(Browser browser)
    {
        super(browser);
    }

    @Override
    public ExpectedCondition getReadinessCondition() throws Exception
    {
        if(pageReadinessCondition == null && System.getProperty("pageReadyForTestingClassName") != null)
        {
            final String clazz = System.getProperty("pageReadyForTestingClassName");
            try
            {
                LOG.log(Level.INFO, "Instantiating pageReadyForTestingClassName: '"+clazz+"'");
                pageReadinessCondition = (PageReadyCondition) Class.forName(clazz).newInstance();
            }
            catch (Exception e)
            {
                LOG.log(Level.SEVERE, "Unable to instantiate pageReadyForTestingClassName: '"+clazz+"'. Take care to implement the PageReadyForTesting!", e);
                throw new UndefinedPageReadyClassNameException("Unable to instantiate pageReadyForTestingClassName: '"+clazz+"' as defined from property: 'pageReadyForTestingClassName'");
            }
        }
        return pageReadinessCondition;
    }


}
