package commandLineUtils;

import audioUtils.AudioComparison;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class Cmd {

    private final static Logger LOG = Logger.getLogger(AudioComparison.class.getName());

    private Cmd() {
    }

    public static void exec(Command cmdCommand) {
        LOG.info("Executing: " + cmdCommand.cmd);
        try {
            getOutputFromProgram(cmdCommand.cmd);
            LOG.info("Success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getOutputFromProgram(String program) throws IOException {
        Process proc = Runtime.getRuntime().exec(program);
        return Stream.of(proc.getErrorStream(), proc.getInputStream()).parallel().map((InputStream isForOutput) -> {
            StringBuilder output = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line);
                    output.append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return output;
        }).collect(Collectors.joining());
    }

    private static int waitForProcess(Process pr) {
        new Thread(new Runnable() {
            public void run() {
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;

                try {
                    while ((line = input.readLine()) != null)
                        System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int exitCode = 999;
        try {
            exitCode = pr.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exitCode;
    }
}
