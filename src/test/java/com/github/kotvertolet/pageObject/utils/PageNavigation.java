package com.github.kotvertolet.pageObject.utils;

import com.github.kotvertolet.annotations.Url;
import com.github.kotvertolet.core.base.AbstractBasePage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by kotvertolet on 6/24/2017.
 */
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
            return open(url, p);
        } else {
            return page(p);
        }
    }

    public static <P extends AbstractBasePage> P navigateAndGetPage(Class<P> p, String url) {
        return open(url, p);
    }
}
