package de.arthurpicht.barnacleRuntimeTest;

import de.arthurpicht.utils.io.nio2.FileUtils;

import java.nio.file.Path;

public class CleanUp {

    public static void exec(String testCaseId) {
        Path dbPath = RuntimeTestPaths.getDb(testCaseId);
        FileUtils.rmDirSilently(dbPath);
    }

}
