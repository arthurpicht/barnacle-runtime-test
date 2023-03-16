package de.arthurpicht.barnacleRuntimeTest.tg_02.tc_01;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_01.persistence.dao.AddressDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_01.persistence.dao.UserDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_01.persistence.vo.AddressVO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_01.persistence.vo.UserVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0201 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException, EntityNotFoundException {
        UserVO userVO = new UserVO("id-1");
        userVO.setUserName("dummy-1");
        UserDAO.create(userVO);

        AddressVO addressVO = new AddressVO("id-1");
        addressVO.setEmail("dummy1@example.org");
        AddressDAO.create(addressVO);
    }

    @Test
    @Order(3)
    public void findByFk() throws DataSourceException, EntityNotFoundException {
        UserVO userVO = UserDAO.load("id-1");
        assertEquals("dummy-1", userVO.getUserName());

        List<AddressVO> addressVOList = userVO.getAddressVOByFk_address_1();
        assertEquals(1, addressVOList.size());

        AddressVO addressVO = addressVOList.get(0);
        assertEquals("id-1", addressVO.getUserId());
        assertEquals("dummy1@example.org", addressVO.getEmail());
    }

}
