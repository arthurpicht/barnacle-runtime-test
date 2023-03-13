package de.arthurpicht.barnacleRuntimeTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RuntimeTestPaths {

    public static Path getSql(String testCase) {
        return Paths.get("../barnacle-generator-test/src/main/java-gen/de/arthurpicht/barnacleGeneratorTest/" + testCase + "/sql/test.sql");
    }

    public static Path getDb(String testCaseId) {
        return Paths.get("db/" + testCaseId + ".mv.db");
    }

}
