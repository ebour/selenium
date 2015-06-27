package com.github.ebour.selenium.factories.api.page;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface PageReadyCondition extends ExpectedCondition<Boolean>
{
    ExpectedCondition<Boolean> isReady(final Page page);
}
