package com.github.kotvertolet.pageObject.panels;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import com.github.kotvertolet.core.base.AbstractPanel;

public class GenericHeaderPanel extends AbstractPanel {

    private final String searchField = ".//input[@id='masthead-search-term']";

    public GenericHeaderPanel(SelenideElement element) {
        super(element);
    }

    public SelenideElement getSearchField() {
        return super.baseElement.$(By.xpath(searchField));
    }

}
