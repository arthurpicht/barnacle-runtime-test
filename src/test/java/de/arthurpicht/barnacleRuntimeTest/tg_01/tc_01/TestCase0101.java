package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_01;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_01.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_01.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0101 extends TestCaseBase {

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
