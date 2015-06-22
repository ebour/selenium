package com.github.ebour.selenium.factories.api.browser;

import java.io.File;

/**
 * Created by ebour on 20/06/15.
 */
public class BrowserFactoryHelper
{
    public static boolean isBrowserPathDefined()
    {
        return System.getProperty("browserPath", "").isEmpty();
    }

    public static String getBrowserPath() throws Exception
    {
        if(System.getProperty("browserPath", "").isEmpty())
        {
            throw new Exception("Undefined 'browserPath' property!");
        }
        else
        {
            final File browserPath = new File(System.getProperty("browserPath"));
            browserPath.setExecutable(true);
            if(!browserPath.exists())
            {
                throw new Exception("Browser executable not found. U pdate the 'browserPath' property value!");
            }
            return browserPath.getPath();
        }
    }

    public static String getDriverPath() throws Exception
    {
        if(System.getProperty("driverPath", "").isEmpty())
        {
            throw new Exception("Undefined 'driverPath' property!");
        }
        else
        {
            final File driver = new File(System.getProperty("driverPath"));
            if(!driver.exists())
            {
                throw new Exception("Driver executable not found. Update the 'driverPath' property value!");
            }
            driver.setExecutable(true);
            return driver.getPath();
        }
    }

    public static boolean isWindows()
    {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}
