package de.arthurpicht.barnacleRuntimeTest.tc_01;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_01.persistence.dao.PersonDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_01.persistence.vo.PersonVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase01Bulk {

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
