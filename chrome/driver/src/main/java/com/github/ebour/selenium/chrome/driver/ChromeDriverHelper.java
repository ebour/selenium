package com.github.ebour.selenium.chrome.driver;

import java.io.File;

/**
 * Created by ebour on 20/06/15.
 */
public class ChromeDriverHelper
{
    private static final String CHROMEDRIVER_SUBFOLDER = "drivers/chromedriver";

    public enum Arch
    {
        WINDOWS_X86_64("windows-x86-64.exe"), LINUX_X86_64("linux-x86-64"), LINUX_X86_32("linux-x86-64"), MACOSX_X86_32("macosx-x86-64");

        private String value;

        private Arch(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return this.value;
        }
    }

    public static File getChromeDriver(final File baseDir, final Arch arch)
    {
        return new File(baseDir, CHROMEDRIVER_SUBFOLDER + arch.toString());
    }

    public static File getChromeDriver(final File baseDir, final String chromeDriverSubFolder, final Arch arch)
    {
        return new File(baseDir, chromeDriverSubFolder + "/" + CHROMEDRIVER_SUBFOLDER + arch.toString());
    }

}
