package de.arthurpicht.barnacleRuntimeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CleanUp {

    public static void exec(String testCaseId) {
        Path dbPath = RuntimeTestPaths.getDb(testCaseId);
        try {
            Files.deleteIfExists(dbPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
