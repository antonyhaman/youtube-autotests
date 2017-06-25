package pageObject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import pageObject.panels.GenericVideoPanel;
import pageObject.base.AbstractPage;
import pageObject.utils.PageNavigation;

import java.util.List;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class YoutubeGenericVideoPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='player-api']")
    private SelenideElement videoplayerPanelBaseElement;

    public GenericVideoPanel getVideoPlayerPanel() {
        return new GenericVideoPanel(videoplayerPanelBaseElement);
    }

    public void navigateToVideoAndRecordSoundClip(List<String> urlList, List<String> filenamesArr, int durationMinutes, int durationSeconds) {
        for (int i = 0; i < urlList.size(); i++) {
            navigateToVideoAndRecordSoundClip(urlList.get(i), filenamesArr.get(i), durationMinutes, durationSeconds);
        }
    }

    public void navigateToVideoAndRecordSoundClip(String url, String filename, int durationMinutes, int durationSeconds) {
        YoutubeGenericVideoPage videoPage = PageNavigation.navigateAndGetPage(this.getClass(), url);
        videoPage.getVideoPlayerPanel().recordSoundFromVideo(filename, durationMinutes, durationSeconds);
    }

}
