package core;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static fileUtils.FileUtils.PATH_TO_ADBLOCK;
import static fileUtils.FileUtils.PATH_TO_CHROMEDRIVER;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class WebdriverService {

    public static void initDriver(boolean adblockOn) {
        setChromeDriverWindows();
        ChromeDriver driver;

        if (adblockOn) {
            File adblock = new File(PATH_TO_ADBLOCK);
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(adblock);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(capabilities);
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    private static void setChromeDriverWindows() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER);
    }

}
