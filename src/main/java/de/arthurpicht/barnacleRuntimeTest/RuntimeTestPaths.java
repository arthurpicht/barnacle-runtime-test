package de.arthurpicht.barnacleRuntimeTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RuntimeTestPaths {

    public static Path getSql(String testGroupId, String testCaseId) {
        return Paths.get("../barnacle-generator-test/src/main/java-gen/de/arthurpicht/barnacleGeneratorTest/"
                + testGroupId + "/" + testCaseId + "/sql/test.sql");
    }

    public static Path getDb(String testGroupId, String testCaseId) {
        return Paths.get("db/" + testGroupId + "_" + testCaseId + ".mv.db");
    }

}
