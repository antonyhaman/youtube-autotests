package com.github.kotvertolet.utils.commandLineUtils;

import com.github.kotvertolet.utils.fileUtils.FileUtils;

public class Command {

    public String cmd = "cmd /c ";

    private Command() {
    }

    public static CommandBuilder newBuilder() {
        return new Command().new CommandBuilder();
    }

    public class CommandBuilder {
        private final String CHANGE_DIR = " cd ";
        private final String LOGICAL_AND = " && ";
        private final String START_RECORD = "fmedia --record --dev-loopback=0 --until=%s:%s  --out=../%s%s";
        private final String SET_PATH_VARIABLE = " set PATH=%PATH%";

        private CommandBuilder() {
        }

        public CommandBuilder changeDirectory(String path) {
            cmd = cmd + CHANGE_DIR + path;
            return this;
        }

        public CommandBuilder and() {
            cmd = cmd + LOGICAL_AND;
            return this;
        }

        public CommandBuilder startRecord(String filename, int durationMinutes, int durationSeconds) {
            cmd = cmd + String.format(START_RECORD, durationMinutes, durationSeconds, FileUtils.AUDIO_OUTPUT_DIR, filename);
            return this;
        }

        public CommandBuilder setPathVariable(String path) {
            cmd = cmd + SET_PATH_VARIABLE + path;
            return this;
        }

        public Command build() {
            return Command.this;
        }
    }

}
