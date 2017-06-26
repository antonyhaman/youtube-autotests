package com.github.kotvertolet.utils.commandLineUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cmd {

    private final static Logger LOG = Logger.getLogger(Cmd.class.getName());

    private Cmd() {
    }

    public static void exec(Command cmdCommand) {
        LOG.fine("Executing: " + cmdCommand.cmd);
        getOutputFromProgram(cmdCommand.cmd);
        LOG.fine("Cmd execution success");
    }

    private static String getOutputFromProgram(String program) {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(program);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Cmd command execution failed", e);
            throw new RuntimeException(e);
        }

        return Stream.of(proc.getErrorStream(), proc.getInputStream()).parallel().map((InputStream isForOutput) -> {
            StringBuilder output = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line);
                    output.append("\n");
                }
            } catch (IOException e) {
                LOG.log(Level.SEVERE,"Error while listening to cmd I/O", e);
                throw new RuntimeException(e);
            }
            return output;
        }).collect(Collectors.joining());
    }
}
