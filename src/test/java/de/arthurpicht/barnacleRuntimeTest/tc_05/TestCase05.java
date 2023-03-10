package de.arthurpicht.barnacleRuntimeTest.tc_05;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_04.persistence.dao.AutoIncDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_04.persistence.vo.AutoIncVO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.dao.AddressDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.dao.UserDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.vo.AddressVO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.vo.UserVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase05 {

    private static final String testCaseId = "tc_05";

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
