package com.github.kotvertolet.pageObject.panels;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import com.github.kotvertolet.core.base.AbstractPanel;

import java.util.ArrayList;
import java.util.List;

public class TrendingVideosFeedPanel extends AbstractPanel {

    public TrendingVideosFeedPanel(ElementsCollection elements) {
        super(elements);
    }

    public TrendingVideoSubpanel getTopTrendingVideos() {
        super.waitUtils.waitUntilCollectionFilled(120, 200, baseElements);
        return new TrendingVideoSubpanel(baseElements.get(0));
    }

    public TrendingVideoSubpanel getWeeklyTrendingVideos() {
        return new TrendingVideoSubpanel(baseElements.get(1));
    }

    public class TrendingVideoSubpanel extends AbstractPanel {

        private String videoBlocksSelector = ".//div[@class='expanded-shelf-content-item']";

        public TrendingVideoSubpanel(SelenideElement element) {
            super(element);
        }

        private List<SelenideElement> getVideoBlocksList() {
            return super.baseElement.$$(By.xpath(videoBlocksSelector));
        }

        public List<String> getVideoLinks(int desiredNumberOfLinks) {
            List<SelenideElement> videosList = getVideoBlocksList();
            List<String> list = new ArrayList<>(desiredNumberOfLinks);
            for (int i = 1; i <= desiredNumberOfLinks; i++) {
                list.add(videosList.get(i).$(By.xpath(".//h3/a")).attr("href"));
            }
            return list;
        }
    }
}
