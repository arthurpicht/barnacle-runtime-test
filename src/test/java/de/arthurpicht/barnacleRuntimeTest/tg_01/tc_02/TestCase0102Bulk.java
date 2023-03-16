package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_02;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_02.persistence.dao.PersonCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_02.persistence.vo.PersonCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_02.persistence.vo.PersonCompositeVO;
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
public class TestCase0102Bulk extends TestCaseBase {

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
