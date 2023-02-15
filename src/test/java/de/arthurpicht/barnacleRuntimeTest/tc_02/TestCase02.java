package de.arthurpicht.barnacleRuntimeTest.tc_02;

import de.arthurpicht.barnacleGeneratorTest.connectionManager.DataSourceException;
import de.arthurpicht.barnacleGeneratorTest.connectionManager.EntityNotFoundException;
import de.arthurpicht.barnacleGeneratorTest.tc_02.persistence.dao.PersonCompositeDAO;
import de.arthurpicht.barnacleGeneratorTest.tc_02.persistence.vo.PersonCompositePK;
import de.arthurpicht.barnacleGeneratorTest.tc_02.persistence.vo.PersonCompositeVO;
import de.arthurpicht.barnacleRuntimeTest.CleanUp;
import de.arthurpicht.barnacleRuntimeTest.SchemaDeploy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase02 {

    private static final String testCaseId = "tc_02";

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
    public void create() throws DataSourceException {
        PersonCompositePK personCompositePK = new PersonCompositePK("Joe", "Dummy");
        PersonCompositeVO personCompositeVO = new PersonCompositeVO(personCompositePK);
        personCompositeVO.setAge(42);

        PersonCompositeDAO.create(personCompositeVO);
    }

    @Test
    @Order(3)
    public void read() throws DataSourceException, EntityNotFoundException {
        PersonCompositePK personCompositePK = new PersonCompositePK("Joe", "Dummy");
        PersonCompositeVO personCompositeVO = PersonCompositeDAO.load(personCompositePK);

        assertEquals("Joe", personCompositeVO.getFirstName());
        assertEquals("Dummy", personCompositeVO.getLastName());
        assertEquals(42, personCompositeVO.getAge());
    }

    @Test
    @Order(4)
    public void update() throws DataSourceException, EntityNotFoundException {
        PersonCompositePK personCompositePK = new PersonCompositePK("Joe", "Dummy");
        PersonCompositeVO personCompositeVO = PersonCompositeDAO.load(personCompositePK);

        personCompositeVO.setAge(16);
        PersonCompositeDAO.update(personCompositeVO);

        PersonCompositeVO personCompositeVOReloaded = PersonCompositeDAO.load(personCompositePK);
        assertEquals("Joe", personCompositeVOReloaded.getFirstName());
        assertEquals("Dummy", personCompositeVOReloaded.getLastName());
        assertEquals(16, personCompositeVOReloaded.getAge());
    }

    @Test
    @Order(5)
    public void delete() throws DataSourceException {
        PersonCompositePK personCompositePK = new PersonCompositePK("Joe", "Dummy");
        PersonCompositeDAO.delete(personCompositePK);
        Assertions.assertThrows(EntityNotFoundException.class, () -> PersonCompositeDAO.load(personCompositePK));
    }

}
