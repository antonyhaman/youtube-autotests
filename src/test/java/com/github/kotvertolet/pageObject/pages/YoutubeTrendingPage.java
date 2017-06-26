package com.github.kotvertolet.pageObject.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.kotvertolet.annotations.Url;
import org.openqa.selenium.support.FindBy;
import com.github.kotvertolet.core.base.AbstractPage;
import com.github.kotvertolet.pageObject.panels.GenericHeaderPanel;
import com.github.kotvertolet.pageObject.panels.TrendingVideosFeedPanel;

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
