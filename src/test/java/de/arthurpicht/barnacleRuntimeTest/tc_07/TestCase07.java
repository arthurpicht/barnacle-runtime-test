package de.arthurpicht.barnacleRuntimeTest.tc_07;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_06.persistence.dao.AddressCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_06.persistence.dao.UserCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_06.persistence.vo.AddressCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tc_06.persistence.vo.AddressCompositeVO;
import de.arthurpicht.barnacleGeneratorTest.tc_06.persistence.vo.UserCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tc_06.persistence.vo.UserCompositeVO;
import de.arthurpicht.barnacleGeneratorTest.tc_07.persistence.dao.GarmentDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_07.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_07.persistence.vo.GarmentVO;
import de.arthurpicht.barnacleGeneratorTest.tc_07.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase07 {

    private static final String testCaseId = "tc_07";

    @BeforeAll
    public static void prepare() {
        CleanUp.exec(testCaseId);
    }

    @Test
    @Order(1)
    public void deploySchema() {
        SchemaDeploy.deploy(testCaseId);
    }

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
