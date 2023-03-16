package de.arthurpicht.barnacleRuntimeTest.tg_02.tc_04;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_04.persistence.dao.GarmentCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_04.persistence.dao.PersonCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_04.persistence.vo.GarmentCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_04.persistence.vo.GarmentCompositeVO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_04.persistence.vo.PersonCompositeVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0204 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        GarmentCompositePK garmentCompositePK = new GarmentCompositePK("id-1-1", "id-1-2");
        GarmentCompositeVO garmentCompositeVO = new GarmentCompositeVO(garmentCompositePK);
        garmentCompositeVO.setName("mickey mouse shirt");
        GarmentCompositeDAO.create(garmentCompositeVO);

        PersonCompositeVO personCompositeVO = new PersonCompositeVO("person-id-1");
        personCompositeVO.setUserName("dummy-1");
        personCompositeVO.setFavoriteGarmentId1("id-1-1");
        personCompositeVO.setFavoriteGarmentId2("id-1-2");
        PersonCompositeDAO.create(personCompositeVO);
    }

    @Test
    @Order(3)
    public void findByFk() throws DataSourceException, EntityNotFoundException {
        GarmentCompositeVO garmentCompositeVO = GarmentCompositeDAO.load(new GarmentCompositePK("id-1-1", "id-1-2"));
        List<PersonCompositeVO> personCompositeVOs = garmentCompositeVO.getPersonCompositeVOByFk_person_garment();
        assertEquals(1, personCompositeVOs.size());

        PersonCompositeVO personCompositeVO = personCompositeVOs.get(0);
        assertEquals("person-id-1", personCompositeVO.getId());
        assertEquals("dummy-1", personCompositeVO.getUserName());
        assertEquals("id-1-1", personCompositeVO.getFavoriteGarmentId1());
        assertEquals("id-1-2", personCompositeVO.getFavoriteGarmentId2());
    }

}
