package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_06;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_06.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_06.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase0106 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO1 = new PersonVO("id-1");
        personVO1.setFirstName("Joe");
        personVO1.setLastName("Dummy");
        PersonDAO.create(personVO1);

        PersonVO personVO2 = new PersonVO("id-2");
        personVO2.setFirstName("Mike");
        personVO2.setLastName("Foo");
        PersonDAO.create(personVO2);

        PersonVO reload1 = PersonDAO.load("id-1");
        assertEquals("id-1", reload1.getId());
        assertEquals("Joe", reload1.getFirstName());
        assertEquals("Dummy", reload1.getLastName());

        PersonVO reload2 = PersonDAO.load("id-2");
        assertEquals("id-2", reload2.getId());
        assertEquals("Mike", reload2.getFirstName());
        assertEquals("Foo", reload2.getLastName());
    }

    @Test
    @Order(3)
    public void findByUk() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO = PersonDAO.findByUk_name("Mike", "Foo");
        assertEquals("id-2", personVO.getId());
        assertEquals("Mike", personVO.getFirstName());
        assertEquals("Foo", personVO.getLastName());
    }

}
