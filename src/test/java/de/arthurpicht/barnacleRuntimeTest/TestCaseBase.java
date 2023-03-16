package de.arthurpicht.barnacleRuntimeTest;

import de.arthurpicht.barnacleGeneratorTest.utils.TestUtils;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class TestCaseBase {

    @BeforeAll
    public void prepare() {
        String testGroupId = TestUtils.getTestGroupId(this);
        String testCaseId = TestUtils.getTestCaseId(this);
        CleanUp.exec(testGroupId, testCaseId);
    }

    @Test
    @Order(1)
    public void deploySchema() {
        String testGroupId = TestUtils.getTestGroupId(this);
        String testCaseId = TestUtils.getTestCaseId(this);
        SchemaDeploy.deploy(testGroupId, testCaseId);
    }

}
