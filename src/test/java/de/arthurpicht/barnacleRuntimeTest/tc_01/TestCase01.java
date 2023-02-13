package de.arthurpicht.barnacleRuntimeTest.tc_01;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_01.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_01.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase01 {

    private static final String testCaseId = "tc_01";

    @BeforeAll
    public static void prepare() {
        CleanUp.exec(testCaseId);
    }

    @Test
    @Order(1)
    public void deploySchema() {
        SchemaDeploy.deploy("tc_01");
    }

    @Test
    @Order(2)
    public void create() throws DataSourceException {
        PersonVO personVO = new PersonVO("Joe");
        personVO.setName("Dummy");
        personVO.setAge(42);
        personVO.setStreet("Nowhere Ln.");

        PersonDAO.create(personVO);
    }

    @Test
    @Order(3)
    public void read() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO = PersonDAO.load("Joe");
        assertEquals("Joe", personVO.getFirstName());
        assertEquals("Dummy", personVO.getName());
        // TODO ...
    }

    @Test
    @Order(4)
    public void update() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO = new PersonVO("Joe");
        personVO.setName("Dummy Dumm");
        personVO.setAge(99);
        personVO.setStreet("Highway to hell");

        PersonDAO.update(personVO);

        PersonVO personVOReloaded = PersonDAO.load("Joe");
        assertEquals("Joe", personVOReloaded.getFirstName());
        assertEquals("Dummy Dumm", personVOReloaded.getName());
        // TODO ...
    }

    @Test
    @Order(5)
    public void delete() throws DataSourceException {
        PersonDAO.delete("Joe");
        Assertions.assertThrows(EntityNotFoundException.class, () -> PersonDAO.load("Joe"));
    }

}
