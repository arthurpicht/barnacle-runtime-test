package de.arthurpicht.barnacleRuntimeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CleanUp {

    public static void exec(String testGroupId, String testCaseId) {
        System.out.println("Cleanup.exec ...");

        try (Stream<Path> files = Files.list(Paths.get("db"))) {
            List<Path> filesToBeDeleted = files
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().startsWith(testGroupId + "_" + testCaseId))
                    .collect(Collectors.toList());
            for (Path path : filesToBeDeleted) {
                System.out.println("Delete file: " + path.toAbsolutePath());
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
