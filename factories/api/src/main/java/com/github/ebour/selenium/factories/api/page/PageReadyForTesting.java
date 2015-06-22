package com.github.ebour.selenium.factories.api.page;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface PageReadyForTesting extends ExpectedCondition<Boolean>
{
    ExpectedCondition<Boolean> isReadyForTesting(final Page page);
}
