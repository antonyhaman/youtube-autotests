package pageObject.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.Url;
import org.openqa.selenium.support.FindBy;
import pageObject.panels.GenericHeaderPanel;
import pageObject.panels.TrendingVideosFeedPanel;
import pageObject.base.AbstractPage;

/**
 * Created by kotvertolet on 6/24/2017.
 */

@Url("https://www.youtube.com/feed/trending")
public class YoutubeTrendingPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='masthead-positioner']")
    private SelenideElement headerBaseElement;

    @FindBy(xpath = "//div[contains(@class, 'feed-item-container')]")
    private ElementsCollection trendingFeedElement;

    public GenericHeaderPanel getHeaderPanel() {
        return new GenericHeaderPanel(headerBaseElement);
    }

    public TrendingVideosFeedPanel getTopTrendingVideosFeedPanel() {
        return new TrendingVideosFeedPanel(trendingFeedElement);
    }
}
