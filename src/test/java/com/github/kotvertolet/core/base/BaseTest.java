package com.github.kotvertolet.core.base;

import com.github.kotvertolet.utils.audioUtils.AudioComparison;
import org.apache.commons.io.input.AutoCloseInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.github.kotvertolet.utils.fileUtils.FileUtils.PATH_TO_LOGGER_PROPERTIES;

public class BaseTest {

    private final static Logger LOG = Logger.getLogger(AudioComparison.class.getName());

    public BaseTest() {
        InputStream is = null;
        try {
            is = new AutoCloseInputStream(new FileInputStream(PATH_TO_LOGGER_PROPERTIES));
        } catch (FileNotFoundException e) {
            LOG.log(Level.WARNING, "Error while reading logger.properties", e);
        }
        try {
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error while initialization logger.properties", e);
        }
    }
}
