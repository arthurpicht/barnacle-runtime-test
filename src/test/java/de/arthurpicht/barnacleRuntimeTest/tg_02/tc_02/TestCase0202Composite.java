package de.arthurpicht.barnacleRuntimeTest.tg_02.tc_02;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_02.persistence.dao.AddressCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_02.persistence.dao.UserCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_02.persistence.vo.AddressCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_02.persistence.vo.AddressCompositeVO;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_02.persistence.vo.UserCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tg_02.tc_02.persistence.vo.UserCompositeVO;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0202Composite extends TestCaseBase {

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
