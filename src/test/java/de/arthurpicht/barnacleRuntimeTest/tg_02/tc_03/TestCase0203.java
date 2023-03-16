package de.arthurpicht.barnacleRuntimeTest.tg_02.tc_03;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_03.persistence.dao.GarmentDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_03.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_03.persistence.vo.GarmentVO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_03.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0203 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        GarmentVO garmentVO = new GarmentVO("shirt-1");
        garmentVO.setName("mickey mouse shirt");
        GarmentDAO.create(garmentVO);

        PersonVO personVO = new PersonVO("id-1");
        personVO.setUserName("dummy-1");
        personVO.setFavoriteGarmentId("shirt-1");
        PersonDAO.create(personVO);
    }

    @Test
    @Order(3)
    public void findByFk() throws DataSourceException, EntityNotFoundException {
        GarmentVO garmentVO = GarmentDAO.load("shirt-1");
        List<PersonVO> personVOs = garmentVO.getPersonVOByFk_person_1();
        assertEquals(1, personVOs.size());

        PersonVO personVO = personVOs.get(0);
        assertEquals("id-1", personVO.getId());
        assertEquals("dummy-1", personVO.getUserName());
        assertEquals("shirt-1", personVO.getFavoriteGarmentId());
    }

}
