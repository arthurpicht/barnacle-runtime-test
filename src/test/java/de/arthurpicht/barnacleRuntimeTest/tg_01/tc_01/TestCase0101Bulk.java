package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_01;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_01.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_01.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0101Bulk extends TestCaseBase {

    @Test
    @Order(2)
    public void bulkCreate() throws DataSourceException {
        List<PersonVO> personVOList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            PersonVO personVO = new PersonVO("id-" + i);
            personVO.setFirstName("first-" + i);
            personVO.setLastName("last-" + i);
            personVOList.add(personVO);
        }
        PersonDAO.create(personVOList);
    }

    @Test
    @Order(3)
    public void findAll() throws DataSourceException {
        List<PersonVO> personVOList = PersonDAO.findAll();
        for (int i = 0; i <= 9; i++) {
            PersonVO personVO = new PersonVO("id-" + i);
            personVO.setFirstName("first-" + i);
            personVO.setLastName("last-" + i);
            assertTrue(personVOList.contains(personVO));
        }
        assertEquals(10, personVOList.size());
    }

}
