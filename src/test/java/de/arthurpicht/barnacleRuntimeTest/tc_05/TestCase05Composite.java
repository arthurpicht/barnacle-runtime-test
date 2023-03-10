package de.arthurpicht.barnacleRuntimeTest.tc_05;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.dao.AddressCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.dao.AddressDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.dao.UserCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.dao.UserDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_05.persistence.vo.*;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase05Composite {

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
        UserCompositePK userCompositePK = new UserCompositePK("id", "1");
        UserCompositeVO userCompositeVO = new UserCompositeVO(userCompositePK);
        userCompositeVO.setUserName("dummy-1");
        UserCompositeDAO.create(userCompositeVO);

        AddressCompositePK addressCompositePK = new AddressCompositePK("id", "1");
        AddressCompositeVO addressCompositeVO = new AddressCompositeVO(addressCompositePK);
        addressCompositeVO.setEmail("dummy1@example.org");
        AddressCompositeDAO.create(addressCompositeVO);
    }

    @Test
    @Order(3)
    public void findByFk() throws DataSourceException, EntityNotFoundException {
        UserCompositePK userCompositePK = new UserCompositePK("id", "1");
        UserCompositeVO userCompositeVO = UserCompositeDAO.load(userCompositePK);
        assertEquals("dummy-1", userCompositeVO.getUserName());

        List<AddressCompositeVO> addressCompositeVOList = userCompositeVO.getAddressCompositeVOByFk_address_composite();
        assertEquals(1, addressCompositeVOList.size());

        AddressCompositeVO addressCompositeVO = addressCompositeVOList.get(0);
        assertEquals("id", addressCompositeVO.getUserId1());
        assertEquals("1", addressCompositeVO.getUserId2());
        assertEquals("dummy1@example.org", addressCompositeVO.getEmail());
    }

}
