package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_05;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_05.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_05.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase0105 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO1 = new PersonVO("id-1");
        personVO1.setFirstName("Joe");
        personVO1.setUserName("joe");
        PersonDAO.create(personVO1);

        PersonVO personVO2 = new PersonVO("id-2");
        personVO2.setFirstName("Mike");
        personVO2.setUserName("mike");
        PersonDAO.create(personVO2);

        PersonVO reload1 = PersonDAO.load("id-1");
        assertEquals("id-1", reload1.getId());
        assertEquals("Joe", reload1.getFirstName());
        assertEquals("joe", reload1.getUserName());

        PersonVO reload2 = PersonDAO.load("id-2");
        assertEquals("id-2", reload2.getId());
        assertEquals("Mike", reload2.getFirstName());
        assertEquals("mike", reload2.getUserName());
    }

    @Test
    @Order(3)
    public void findByUk() throws DataSourceException, EntityNotFoundException {
        PersonVO personVO = PersonDAO.findByUk_userName("mike");
        assertEquals("id-2", personVO.getId());
        assertEquals("Mike", personVO.getFirstName());
        assertEquals("mike", personVO.getUserName());
    }

}
