package de.arthurpicht.barnacleRuntimeTest.tg_03.tc_03;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tg_03.tc_03.persistence.dao.CustomerDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_03.tc_03.persistence.dao.CustomerItemDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_03.tc_03.persistence.dao.ItemDAO;
import de.arthurpicht.barnacleGeneratorTest.tg_03.tc_03.persistence.vo.*;
import de.arthurpicht.barnacleRuntimeTest.TestCaseBase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0303 extends TestCaseBase {

    @Test
    @Order(2)
    public void create() throws DataSourceException {
        CustomerPK customerPK = new CustomerPK("customer-A-1", "customer-A-2");
        CustomerVO customerVO1 = new CustomerVO(customerPK);
        customerVO1.setName("customer-name-1");
        CustomerDAO.create(customerVO1);

        CustomerPK customerPK2 = new CustomerPK("customer-B-1", "customer-B-2");
        CustomerVO customerVO2 = new CustomerVO(customerPK2);
        customerVO2.setName("customer-name-2");
        CustomerDAO.create(customerVO2);

        ItemVO itemVO1 = new ItemVO(new ItemPK("item-A-1", "item-A-2"));
        itemVO1.setDescription("item-desc-1");
        ItemDAO.create(itemVO1);

        ItemVO itemVO2 = new ItemVO(new ItemPK("item-B-1", "item-B-2"));
        itemVO2.setDescription("item-desc-2");
        ItemDAO.create(itemVO2);

        ItemVO itemVO3 = new ItemVO(new ItemPK("item-C-1", "item-C-2"));
        itemVO3.setDescription("item-desc-3");
        ItemDAO.create(itemVO3);

        CustomerItemDAO.addAssociation(customerVO1, itemVO1);
        CustomerItemDAO.addAssociation(customerVO1, itemVO2);
        CustomerItemDAO.addAssociation(customerVO2, itemVO2);
        CustomerItemDAO.addAssociation(customerVO2, itemVO3);
    }

    @Test
    @Order(3)
    public void findAll() throws DataSourceException {
        List<CustomerItemVO> customerItemVOList = CustomerItemDAO.findAll();
        assertEquals(4, customerItemVOList.size());
        assertTrue(customerItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-A-1", "customer-A-2", "item-A-1", "item-A-2")))
        );
        assertTrue(customerItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-A-1", "customer-A-2", "item-B-1", "item-B-2")))
        );
        assertTrue(customerItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-B-1", "customer-B-2", "item-B-1", "item-B-2")))
        );
        assertTrue(customerItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-B-1", "customer-B-2", "item-B-1", "item-B-2")))
        );
    }

    @Test
    @Order(4)
    public void findByFk() throws DataSourceException, EntityNotFoundException {
        CustomerPK customerPK = new CustomerPK("customer-A-1", "customer-A-2");
        CustomerVO customerVO1 = CustomerDAO.load(customerPK);
        List<CustomerItemVO> customer1ItemVOList = customerVO1.getCustomerItemVOByFk_customer();
        assertEquals(2, customer1ItemVOList.size());
        assertTrue(customer1ItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-A-1", "customer-A-2", "item-A-1", "item-A-2")))
        );
        assertTrue(customer1ItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-A-1", "customer-A-2", "item-B-1", "item-B-2")))
        );

        CustomerPK customerPK2 = new CustomerPK("customer-B-1", "customer-B-2");
        CustomerVO customerVO2 = CustomerDAO.load(customerPK2);
        List<CustomerItemVO> customer2ItemVOList = customerVO2.getCustomerItemVOByFk_customer();
        assertEquals(2, customer2ItemVOList.size());
        assertTrue(customer2ItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-B-1", "customer-B-2", "item-B-1", "item-B-2")))
        );
        assertTrue(customer2ItemVOList.contains(
                new CustomerItemVO(new CustomerItemPK("customer-B-1", "customer-B-2", "item-C-1", "item-C-2")))
        );
    }

    @Test
    @Order(5)
    public void getAssociatedItem() throws DataSourceException, EntityNotFoundException {
        ItemVO itemVO1 = new ItemVO(new ItemPK("item-A-1", "item-A-2"));
        itemVO1.setDescription("item-desc-1");

        ItemVO itemVO2 = new ItemVO(new ItemPK("item-B-1", "item-B-2"));
        itemVO2.setDescription("item-desc-2");

        ItemVO itemVO3 = new ItemVO(new ItemPK("item-C-1", "item-C-2"));
        itemVO3.setDescription("item-desc-3");

        CustomerVO customerVO1 = CustomerDAO.load(new CustomerPK("customer-A-1", "customer-A-2"));
        List<ItemVO> associatedItem1VOList = CustomerItemDAO.getAssociatedItemVO(customerVO1);
        assertEquals(2, associatedItem1VOList.size());

        assertTrue(associatedItem1VOList.contains(itemVO1));
        assertTrue(associatedItem1VOList.contains(itemVO2));

        CustomerVO customerVO2 = CustomerDAO.load(new CustomerPK("customer-B-1", "customer-B-2"));
        List<ItemVO> associatedItem2VOList = CustomerItemDAO.getAssociatedItemVO(customerVO2);
        assertEquals(2, associatedItem2VOList.size());
        assertTrue(associatedItem2VOList.contains(itemVO2));
        assertTrue(associatedItem2VOList.contains(itemVO3));
    }

    @Test
    @Order(6)
    public void getAssociatedCustomer() throws DataSourceException, EntityNotFoundException {
        CustomerVO customerVO1 = new CustomerVO(new CustomerPK("customer-A-1", "customer-A-2"));
        customerVO1.setName("customer-name-1");

        CustomerVO customerVO2 = new CustomerVO(new CustomerPK("customer-B-1", "customer-B-2"));
        customerVO2.setName("customer-name-2");

        ItemVO itemVO1 = ItemDAO.load(new ItemPK("item-A-1", "item-A-2"));
        List<CustomerVO> associatedCustomerVOList = CustomerItemDAO.getAssociatedCustomerVO(itemVO1);
        assertEquals(1, associatedCustomerVOList.size());
        assertTrue(associatedCustomerVOList.contains(customerVO1));

        ItemVO itemVO2 = ItemDAO.load(new ItemPK("item-B-1", "item-B-2"));
        List<CustomerVO> associatedCustomerVOList2 = CustomerItemDAO.getAssociatedCustomerVO(itemVO2);
        assertEquals(2, associatedCustomerVOList2.size());
        assertTrue(associatedCustomerVOList2.contains(customerVO1));
        assertTrue(associatedCustomerVOList2.contains(customerVO2));

        ItemVO itemVO3 = ItemDAO.load(new ItemPK("item-C-1", "item-C-2"));
        List<CustomerVO> associatedCustomerVOList3 = CustomerItemDAO.getAssociatedCustomerVO(itemVO3);
        assertEquals(1, associatedCustomerVOList3.size());
        assertTrue(associatedCustomerVOList3.contains(customerVO2));
    }

}
