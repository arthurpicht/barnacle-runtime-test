package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_03;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.dao.ObjectTypesDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.vo.ObjectTypesVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0103Object extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException {
        ObjectTypesVO objectTypesVO = new ObjectTypesVO("id-1");
        objectTypesVO.setMyBoolean(true);
        objectTypesVO.setMyInt(42);
        objectTypesVO.setMyLong(123456789L);
        objectTypesVO.setMyFloat(12345.6789F);
        objectTypesVO.setMyDouble(12345.6789D);
        objectTypesVO.setMyByte((byte)12345);
        objectTypesVO.setMyShort((short)123);

        ObjectTypesDAO.create(objectTypesVO);
    }

    @Test
    @Order(3)
    public void read() throws DataSourceException, EntityNotFoundException {
        ObjectTypesVO objectTypesVO = ObjectTypesDAO.load("id-1");

        assertEquals("id-1", objectTypesVO.getId());
        assertTrue(objectTypesVO.getMyBoolean());
        assertEquals(42, objectTypesVO.getMyInt());
        assertEquals(123456789L, objectTypesVO.getMyLong());
        assertEquals(12345.6789F, objectTypesVO.getMyFloat());
        assertEquals(12345.6789D, objectTypesVO.getMyDouble());
        assertEquals((byte)12345, objectTypesVO.getMyByte());
        assertEquals((short)123, objectTypesVO.getMyShort());
    }

    @Test
    @Order(4)
    public void update() throws DataSourceException, EntityNotFoundException {
        ObjectTypesVO objectTypesVO = ObjectTypesDAO.load("id-1");

        objectTypesVO.setMyBoolean(false);
        objectTypesVO.setMyInt(77);
        objectTypesVO.setMyLong(56789L);
        objectTypesVO.setMyFloat(5.6789F);
        objectTypesVO.setMyDouble(5.6789D);
        objectTypesVO.setMyByte((byte)5);
        objectTypesVO.setMyShort((short)77);

        ObjectTypesDAO.update(objectTypesVO);

        ObjectTypesVO objectTypesVOReloaded = ObjectTypesDAO.load("id-1");
        assertFalse(objectTypesVOReloaded.getMyBoolean());
        assertEquals(77, objectTypesVOReloaded.getMyInt());
        assertEquals(56789L, objectTypesVOReloaded.getMyLong());
        assertEquals(5.6789F, objectTypesVOReloaded.getMyFloat());
        assertEquals(5.6789D, objectTypesVOReloaded.getMyDouble());
        assertEquals((byte)5, objectTypesVOReloaded.getMyByte());
        assertEquals((short)77, objectTypesVOReloaded.getMyShort());
    }

    @Test
    @Order(5)
    public void findAll() throws DataSourceException {
        List<ObjectTypesVO> objectTypesVOList = ObjectTypesDAO.findAll();

        assertEquals(1, objectTypesVOList.size());

        ObjectTypesVO objectTypesVO = objectTypesVOList.get(0);
        assertFalse(objectTypesVO.getMyBoolean());
        assertEquals(77, objectTypesVO.getMyInt());
        assertEquals(56789L, objectTypesVO.getMyLong());
        assertEquals(5.6789F, objectTypesVO.getMyFloat());
        assertEquals(5.6789D, objectTypesVO.getMyDouble());
        assertEquals((byte)5, objectTypesVO.getMyByte());
        assertEquals((short)77, objectTypesVO.getMyShort());
    }

    @Test
    @Order(6)
    public void delete() throws DataSourceException {
        ObjectTypesDAO.delete("id-1");
        Assertions.assertThrows(EntityNotFoundException.class, () -> ObjectTypesDAO.load("id-1"));
    }

}
