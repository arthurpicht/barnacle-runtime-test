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
        PersonVO personVO = new PersonVO("id-1");
        personVO.setFirstName("Joe");
        personVO.setLastName("Dummy");

        PersonDAO.create(personVO);
    }

    @Test
    @Order(3)
    public void read() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO = PersonDAO.load("id-1");
        assertEquals("Joe", personVO.getFirstName());
        assertEquals("Dummy", personVO.getLastName());
    }

    @Test
    @Order(4)
    public void update() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO = PersonDAO.load("id-1");
        personVO.setLastName("Dummy Dumm");

        PersonDAO.update(personVO);

        PersonVO personVOReloaded = PersonDAO.load("id-1");
        assertEquals("Joe", personVOReloaded.getFirstName());
        assertEquals("Dummy Dumm", personVOReloaded.getLastName());
    }

    @Test
    @Order(5)
    public void delete() throws DataSourceException {
        PersonDAO.delete("id-1");
        Assertions.assertThrows(EntityNotFoundException.class, () -> PersonDAO.load("id-1"));
    }

}
