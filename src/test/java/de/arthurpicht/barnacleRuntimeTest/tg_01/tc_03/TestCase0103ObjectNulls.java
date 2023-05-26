package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_03;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.dao.ObjectTypesDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.vo.ObjectTypesVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0103ObjectNulls extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException {
        ObjectTypesVO objectTypesVO = new ObjectTypesVO("id-1");
        objectTypesVO.setMyBoolean(null);
        objectTypesVO.setMyInt(null);
        objectTypesVO.setMyLong(null);
        objectTypesVO.setMyFloat(null);
        objectTypesVO.setMyDouble(null);
        objectTypesVO.setMyByte(null);
        objectTypesVO.setMyShort(null);

        ObjectTypesDAO.create(objectTypesVO);
    }

    @Test
    @Order(3)
    public void read() throws DataSourceException, EntityNotFoundException {
        ObjectTypesVO objectTypesVO = ObjectTypesDAO.load("id-1");

        assertEquals("id-1", objectTypesVO.getId());
        assertFalse(objectTypesVO.getMyBoolean());
        assertNull(objectTypesVO.getMyInt());
        assertNull(objectTypesVO.getMyLong());
        assertNull(objectTypesVO.getMyFloat());
        assertNull(objectTypesVO.getMyDouble());
        assertNull(objectTypesVO.getMyByte());
        assertNull(objectTypesVO.getMyShort());
    }

    @Test
    @Order(5)
    public void delete() throws DataSourceException {
        ObjectTypesDAO.delete("id-1");
        Assertions.assertThrows(EntityNotFoundException.class, () -> ObjectTypesDAO.load("id-1"));
    }

}
