package de.arthurpicht.barnacleRuntimeTest;

public class Prepare {

    public static void prepare(String testCaseId) {
        CleanUp.exec(testCaseId);
    }

}
