package com.github.ebour.selenium.factories.api.page;

import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by ebour on 20/06/15.
 */
public interface PageReadyForTesting extends ExpectedCondition<Boolean>
{
    ExpectedCondition<Boolean> isReadyForTesting(final Page page);
}
