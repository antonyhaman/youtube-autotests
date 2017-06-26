package com.github.kotvertolet.pageObject.panels;

import com.github.kotvertolet.utils.audioUtils.AudioRecorder;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import com.github.kotvertolet.core.base.AbstractPanel;

public class GenericVideoPanel extends AbstractPanel {

    private final String videoPlayerSelector = ".//div[@id='movie_player']";

    public GenericVideoPanel(SelenideElement element) {
        super(element);
    }

    public void recordSoundFromVideo(String filename, int durationMinutes, int durationSeconds) {
        SelenideElement elem = super.baseElement.$(By.xpath(videoPlayerSelector));
        super.waitUtils.waitUntilVideoStartToPlay(120, 20, elem);
        new AudioRecorder().makeRecord(filename, durationMinutes, durationSeconds);
    }
}
