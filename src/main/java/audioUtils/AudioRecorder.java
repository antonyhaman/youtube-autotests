package audioUtils;

import commandLineUtils.Cmd;
import commandLineUtils.Command;

import java.io.File;

import static fileUtils.FileUtils.PATH_TO_RECORDER;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class AudioRecorder {

    private final String RECORDER_ABSOLUTE_PATH = new File(PATH_TO_RECORDER).getAbsolutePath();

    public void makeRecord(String fileName, int durationMinutes, int durationSeconds) {
        Command cmdCommand = Command.newBuilder()
                .changeDirectory(RECORDER_ABSOLUTE_PATH)
                .and()
                .startRecord(fileName, durationMinutes, durationSeconds)
                .build();

        try {
            Cmd.exec(cmdCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
