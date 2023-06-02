package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_07;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_07.persistence.dao.EntityCustomTypeDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_07.persistence.vo.EntityCustomTypeVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase0107 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        EntityCustomTypeVO entityCustomTypeVO = new EntityCustomTypeVO(1);
        entityCustomTypeVO.setCode("1234");
        EntityCustomTypeDAO.create(entityCustomTypeVO);

        EntityCustomTypeVO entityCustomTypeVOReloaded = EntityCustomTypeDAO.load(1);
        assertEquals(1, entityCustomTypeVOReloaded.getId());
        assertEquals("1234", entityCustomTypeVOReloaded.getCode());
    }

}
