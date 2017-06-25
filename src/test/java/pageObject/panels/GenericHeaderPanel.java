package pageObject.panels;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObject.base.AbstractPanel;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class GenericHeaderPanel extends AbstractPanel {

    private final String searchField = ".//input[@id='masthead-search-term']";

    public GenericHeaderPanel(SelenideElement element) {
        super(element);
    }

    public SelenideElement getSearchField() {
        return super.baseElement.$(By.xpath(searchField));
    }

}
