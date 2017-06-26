package com.github.kotvertolet.utils.audioUtils;

import com.github.kotvertolet.utils.commandLineUtils.Cmd;
import com.github.kotvertolet.utils.commandLineUtils.Command;

import java.io.File;
import java.util.logging.Logger;

import static com.github.kotvertolet.utils.fileUtils.FileUtils.PATH_TO_RECORDER;

public class AudioRecorder {

    private final String RECORDER_ABSOLUTE_PATH = new File(PATH_TO_RECORDER).getAbsolutePath();

    public void makeRecord(String fileName, int durationMinutes, int durationSeconds) {
        Command cmdCommand = Command.newBuilder()
                .changeDirectory(RECORDER_ABSOLUTE_PATH)
                .and()
                .startRecord(fileName, durationMinutes, durationSeconds)
                .build();

        Cmd.exec(cmdCommand);
    }
}
