package com.github.kotvertolet.core.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import com.github.kotvertolet.annotations.Url;
import com.github.kotvertolet.core.base.AbstractBasePage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PageNavigation {

    private final static Logger LOG = Logger.getLogger(PageNavigation.class.getName());

    public static <P extends AbstractBasePage> P getPage(Class<P> p) {
        Class<?> clazz;
        try {
            clazz = Class.forName(p.getName());
        } catch (ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, "Error while instantiating Page Object class", ex);
            throw new RuntimeException(ex);
        }

        String url = null;
        if (clazz.isAnnotationPresent(Url.class)) {
            url = clazz.getAnnotation(Url.class).value();
        }
        if (url != null) {
            P page = open(url, p);
            closeAdblockGreeting();
            return page;
        } else {
            return page(p);
        }
    }

    public static <P extends AbstractBasePage> P navigateAndGetPage(Class<P> p, String url) {
        return open(url, p);
    }

    public static void closeAdblockGreeting() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
    }
}
