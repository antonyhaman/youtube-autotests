package audioUtils;

import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.wave.Wave;

import java.util.List;
import java.util.logging.Logger;

import static fileUtils.FileUtils.PATH_TO_AUDIO;

/**
 * Created by kotvertolet on 6/25/2017.
 */
public class AudioComparison {
    private final static Logger LOG = Logger.getLogger(AudioComparison.class.getName());

    public void checkSoundFileSimilarity(List<String> fileNameList1, List<String> fileNameList2) {
        for (int i = 0; i < fileNameList1.size(); i++) {
            checkSoundFileSimilarity(fileNameList1.get(i), fileNameList2.get(i));
        }
    }

    public void checkSoundFileSimilarity(String file1, String file2) {
        Wave waveWithAdblock = new Wave(PATH_TO_AUDIO + file1);
        Wave waveWithoutAdblock = new Wave(PATH_TO_AUDIO + file2);
        FingerprintSimilarity fingerprintSimilarity = waveWithAdblock.getFingerprintSimilarity(waveWithoutAdblock);
        float similarity = fingerprintSimilarity.getSimilarity();

        if (similarity < 0.9) {
            LOG.warning(String.format("File %s most likely has preroll", file2));
        } else {
            LOG.info(String.format("File %s and %s are identical so there is no preroll", file1, file2));
        }
    }

}
