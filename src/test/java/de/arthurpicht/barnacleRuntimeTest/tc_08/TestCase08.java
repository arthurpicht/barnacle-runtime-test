package de.arthurpicht.barnacleRuntimeTest.tc_08;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_08.persistence.dao.GarmentCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_08.persistence.dao.PersonCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_08.persistence.vo.GarmentCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tc_08.persistence.vo.GarmentCompositeVO;
import de.arthurpicht.barnacleGeneratorTest.tc_08.persistence.vo.PersonCompositeVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase08 {

    private static final String testCaseId = "tc_08";

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
