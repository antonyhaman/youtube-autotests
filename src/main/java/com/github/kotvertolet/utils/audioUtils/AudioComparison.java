package com.github.kotvertolet.utils.audioUtils;

import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.wave.Wave;

import java.util.List;
import java.util.logging.Logger;

import static com.github.kotvertolet.utils.fileUtils.FileUtils.PATH_TO_AUDIO_OUTPUT;

public class AudioComparison {

    private final static Logger LOG = Logger.getLogger(AudioComparison.class.getName());

    public void checkSoundFileSimilarity(List<String> fileNameList1, List<String> fileNameList2) {
        for (int i = 0; i < fileNameList1.size(); i++) {
            checkSoundFileSimilarity(fileNameList1.get(i), fileNameList2.get(i));
        }
    }

    public void checkSoundFileSimilarity(String fileWithAdblock, String fileWithoutAdblock) {
        Wave waveWithAdblock = new Wave(PATH_TO_AUDIO_OUTPUT + fileWithAdblock);
        Wave waveWithoutAdblock = new Wave(PATH_TO_AUDIO_OUTPUT + fileWithoutAdblock);
        FingerprintSimilarity fingerprintSimilarity = waveWithAdblock.getFingerprintSimilarity(waveWithoutAdblock);
        float similarity = fingerprintSimilarity.getSimilarity();

        if (similarity < 0.9) {
            LOG.warning(String.format("File %s most likely has pre-roll", fileWithoutAdblock));
        } else {
            LOG.warning(String.format("File %s do not have pre-roll", fileWithoutAdblock));
        }
    }
}
