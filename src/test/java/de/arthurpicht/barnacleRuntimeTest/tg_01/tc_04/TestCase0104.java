package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_04;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_04.persistence.dao.AutoIncDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_04.persistence.vo.AutoIncVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0104 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        AutoIncVO autoIncVO1 = new AutoIncVO(4242);
        autoIncVO1.setName("dummy-1");
        AutoIncDAO.create(autoIncVO1);
        int id1 = autoIncVO1.getId();

        AutoIncVO autoIncVO2 = new AutoIncVO(4243);
        autoIncVO2.setName("dummy-2");
        AutoIncDAO.create(autoIncVO2);
        int id2 = autoIncVO2.getId();

        assertTrue(id1 > 0);
        assertTrue(id2 > id1);

        AutoIncVO autoIncVO1read = AutoIncDAO.load(id1);
        assertEquals("dummy-1", autoIncVO1read.getName());

        AutoIncVO autoIncVO2read = AutoIncDAO.load(id2);
        assertEquals("dummy-2", autoIncVO2read.getName());
    }

}
