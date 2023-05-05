package de.arthurpicht.barnacleRuntimeTest;

import org.junit.jupiter.api.*;
import de.arthurpicht.utils.test.TestIds;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class TestCaseBase {

    @BeforeAll
    public void prepare() {
        String testGroupId = TestIds.getTestGroupId(this);
        String testCaseId = TestIds.getTestCaseId(this);
        CleanUp.exec(testGroupId, testCaseId);
    }

    @Test
    @Order(1)
    public void deploySchema() {
        String testGroupId = TestIds.getTestGroupId(this);
        String testCaseId = TestIds.getTestCaseId(this);
        SchemaDeploy.deploy(testGroupId, testCaseId);
    }

}
