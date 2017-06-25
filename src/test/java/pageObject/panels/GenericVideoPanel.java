package pageObject.panels;

import audioUtils.AudioRecorder;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObject.base.AbstractPanel;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class GenericVideoPanel extends AbstractPanel {

    private final String videoPlayerSelector = ".//div[@id='movie_player']";

    public GenericVideoPanel(SelenideElement element) {
        super(element);
    }

    public void recordSoundFromVideo(String filename, int durationMinutes, int durationSeconds) {
        SelenideElement elem = super.baseElement.$(By.xpath(videoPlayerSelector));
        super.waitUtils.waitUntilVideoStartToPlay(10000, 20, elem);
        new AudioRecorder().makeRecord(filename, durationMinutes, durationSeconds);
    }
}
