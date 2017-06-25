package pageObject.utils;

import core.Url;
import pageObject.base.AbstractBasePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class PageNavigation {

    public static <P extends AbstractBasePage> P getPage(Class<P> p) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(p.getName());
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
        }

        String url = null;
        if (clazz.isAnnotationPresent(Url.class)) {
            url = clazz.getAnnotation(Url.class).value();
        }
        if (url != null) {
            return open(url, p);
        } else {
            return page(p);
        }
    }

    public static <P extends AbstractBasePage> P navigateAndGetPage(Class<P> p, String url) {
        return open(url, p);
    }
}
