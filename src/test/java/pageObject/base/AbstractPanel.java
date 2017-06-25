package pageObject.base;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/**
 * Created by kotvertolet on 6/24/2017.
 */
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
