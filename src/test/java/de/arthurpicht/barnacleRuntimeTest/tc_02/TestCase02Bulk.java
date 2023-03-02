package de.arthurpicht.barnacleRuntimeTest.tc_02;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.tc_02.persistence.dao.PersonCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_02.persistence.vo.PersonCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tc_02.persistence.vo.PersonCompositeVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase02Bulk {

    private static final String testCaseId = "tc_02";

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
    public void bulkCreate() throws DataSourceException {
        List<PersonCompositeVO> personCompositeVOList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            PersonCompositePK personCompositePK = new PersonCompositePK("first-" + i, "last-" + i);
            PersonCompositeVO personCompositeVO = new PersonCompositeVO(personCompositePK);
            personCompositeVO.setAge(i);
            personCompositeVOList.add(personCompositeVO);
        }
        PersonCompositeDAO.create(personCompositeVOList);
    }

    @Test
    @Order(3)
    public void findAll() throws DataSourceException {
        List<PersonCompositeVO> personCompositeVOList = PersonCompositeDAO.findAll();

        System.out.println("OUT:");
        for (PersonCompositeVO personCompositeVO : personCompositeVOList) {
            System.out.println(personCompositeVO);
        }
        System.out.println("ENDE OUT");

        for (int i = 0; i <= 9; i++) {
            PersonCompositePK personCompositePK = new PersonCompositePK("first-" + i, "last-" + i);
            PersonCompositeVO personCompositeVO = new PersonCompositeVO(personCompositePK);
            personCompositeVO.setAge(i);
            assertTrue(personCompositeVOList.contains(personCompositeVO));
        }
        assertEquals(10, personCompositeVOList.size());
    }

}
