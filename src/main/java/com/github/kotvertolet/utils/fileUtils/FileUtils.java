package com.github.kotvertolet.utils.fileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String PATH_TO_RESOURCES = "src/main/resources/";
    public static final String AUDIO_OUTPUT_DIR = "audioOutput/";
    public static final String PATH_TO_AUDIO_OUTPUT = PATH_TO_RESOURCES + AUDIO_OUTPUT_DIR;
    public static final String PATH_TO_RECORDER = PATH_TO_RESOURCES + "fmedia";
    public static final String PATH_TO_ADBLOCK = PATH_TO_RESOURCES + "adblock/4.3.1_0.crx";
    public static final String PATH_TO_CHROMEDRIVER = PATH_TO_RESOURCES + "chromeDriver/chromedriver.exe";
    public static final String PATH_TO_LOGGER_PROPERTIES = PATH_TO_RESOURCES + "properties/logging.properties";

    public List<String> prepareFilenames(int countOfFilenames, boolean adblockOn) {
        List<String> filenamesList = new ArrayList<>(countOfFilenames);
        String filenameMask = "%s_adblock-%s.wav";
        for (int i = 0; i < countOfFilenames; i++) {
            if (adblockOn) {
                filenamesList.add(String.format(filenameMask, i, "on"));
            } else {
                filenamesList.add(String.format(filenameMask, i, "off"));
            }
        }
        return filenamesList;
    }

    public void initAudioDir() {
        File outputFolder = new File(PATH_TO_AUDIO_OUTPUT);
        if (outputFolder.exists()) {
            File[] fileArr = getFilesInOutputDir(outputFolder);
            if (fileArr.length > 0) {
                for (File file : fileArr) {
                    file.delete();
                }
            }
        } else {
            outputFolder.mkdir();
        }
    }

    public File[] getFilesInOutputDir() {
        return new File(PATH_TO_AUDIO_OUTPUT).listFiles();
    }

    public File[] getFilesInOutputDir(File dir) {
        return dir.listFiles();
    }
}
