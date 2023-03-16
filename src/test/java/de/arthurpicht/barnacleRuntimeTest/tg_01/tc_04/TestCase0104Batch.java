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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0104Batch extends TestCaseBase {

    private static List<AutoIncVO> autoIncVOList;

    @Test
    @Order(2)
    public void create() throws DataSourceException {
        autoIncVOList = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            AutoIncVO autoIncVO = new AutoIncVO(4242 + i);
            autoIncVO.setName("dummy-" + i);
            autoIncVOList.add(autoIncVO);
        }
        AutoIncDAO.create(autoIncVOList);

        int lastId = -1;
        for (AutoIncVO autoIncVO : autoIncVOList) {
            assertTrue(autoIncVO.getId() > lastId);
            lastId = autoIncVO.getId();
        }
    }

    @Test
    @Order(3)
    public void reload() throws DataSourceException, EntityNotFoundException {
        for (AutoIncVO autoIncVO : autoIncVOList) {
            AutoIncVO autoIncVOReload = AutoIncDAO.load(autoIncVO.getId());
            assertEquals(autoIncVO.getName(), autoIncVOReload.getName());
        }
    }

    @Test
    @Order(4)
    public void findAll() throws DataSourceException {
        List<AutoIncVO> autoIncVOListReload = AutoIncDAO.findAll();
        for (AutoIncVO autoIncVO : autoIncVOList) {
            Optional<AutoIncVO> autoIncVOReload = autoIncVOListReload.stream()
                    .filter(a -> a.getId() == autoIncVO.getId())
                    .findFirst();
            assertTrue(autoIncVOReload.isPresent());
            assertEquals(autoIncVO.getName(), autoIncVOReload.get().getName());
        }
    }

}
