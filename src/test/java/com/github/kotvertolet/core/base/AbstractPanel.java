package com.github.kotvertolet.core.base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public abstract class AbstractPanel extends AbstractBasePage {

    protected SelenideElement baseElement;
    protected ElementsCollection baseElements;

    protected AbstractPanel(SelenideElement element) {
        this.baseElement = element;
    }

    protected AbstractPanel(ElementsCollection element) {
        this.baseElements = element;
    }
}
