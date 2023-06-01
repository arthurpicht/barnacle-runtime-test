package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_03;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.dao.BasicTypesDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.dao.ObjectTypesDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.vo.BasicTypesVO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_03.persistence.vo.ObjectTypesVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0103Basic extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException {
        BasicTypesVO basicTypesVO = new BasicTypesVO("id-1");
        basicTypesVO.setMyBoolean(true);
        basicTypesVO.setMyInt(42);
        basicTypesVO.setMyLong(123456789L);
        basicTypesVO.setMyFloat(12345.6789F);
        basicTypesVO.setMyDouble(12345.6789D);
        basicTypesVO.setMyByte((byte)12345);
        basicTypesVO.setMyShort((short)123);

        BasicTypesDAO.create(basicTypesVO);
    }

    @Test
    @Order(3)
    public void read() throws DataSourceException, EntityNotFoundException {
        BasicTypesVO basicTypesVO = BasicTypesDAO.load("id-1");

        assertEquals("id-1", basicTypesVO.getId());
        assertTrue(basicTypesVO.getMyBoolean());
        assertEquals(42, basicTypesVO.getMyInt());
        assertEquals(123456789L, basicTypesVO.getMyLong());
        assertEquals(12345.6789F, basicTypesVO.getMyFloat());
        assertEquals(12345.6789D, basicTypesVO.getMyDouble());
        assertEquals((byte)12345, basicTypesVO.getMyByte());
        assertEquals((short)123, basicTypesVO.getMyShort());

    }

    @Test
    @Order(4)
    public void update() throws DataSourceException, EntityNotFoundException {
        BasicTypesVO basicTypesVO = BasicTypesDAO.load("id-1");

        basicTypesVO.setMyBoolean(false);
        basicTypesVO.setMyInt(77);
        basicTypesVO.setMyLong(56789L);
        basicTypesVO.setMyFloat(5.6789F);
        basicTypesVO.setMyDouble(5.6789D);
        basicTypesVO.setMyByte((byte)5);
        basicTypesVO.setMyShort((short)77);

        BasicTypesDAO.update(basicTypesVO);

        BasicTypesVO basicTypesVOReloaded = BasicTypesDAO.load("id-1");
        assertFalse(basicTypesVOReloaded.getMyBoolean());
        assertEquals(77, basicTypesVOReloaded.getMyInt());
        assertEquals(56789L, basicTypesVOReloaded.getMyLong());
        assertEquals(5.6789F, basicTypesVOReloaded.getMyFloat());
        assertEquals(5.6789D, basicTypesVOReloaded.getMyDouble());
        assertEquals((byte)5, basicTypesVOReloaded.getMyByte());
        assertEquals((short)77, basicTypesVOReloaded.getMyShort());
    }

    @Test
    @Order(5)
    public void findAll() throws DataSourceException {
        List<BasicTypesVO> basicTypesVOList = BasicTypesDAO.findAll();

        assertEquals(1, basicTypesVOList.size());

        BasicTypesVO basicTypesVO = basicTypesVOList.get(0);
        assertFalse(basicTypesVO.getMyBoolean());
        assertEquals(77, basicTypesVO.getMyInt());
        assertEquals(56789L, basicTypesVO.getMyLong());
        assertEquals(5.6789F, basicTypesVO.getMyFloat());
        assertEquals(5.6789D, basicTypesVO.getMyDouble());
        assertEquals((byte)5, basicTypesVO.getMyByte());
        assertEquals((short)77, basicTypesVO.getMyShort());
    }

    @Test
    @Order(6)
    public void delete() throws DataSourceException {
        BasicTypesDAO.delete("id-1");
        Assertions.assertThrows(EntityNotFoundException.class, () -> BasicTypesDAO.load("id-1"));
    }

}
