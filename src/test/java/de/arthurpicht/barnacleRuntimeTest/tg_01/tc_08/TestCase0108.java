package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_08;

import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_08.persistence.vo.EntityCloneableVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase0108 {

    @Test
    public void cloneTest() {
        EntityCloneableVO entityCloneableVO = new EntityCloneableVO("id");
        entityCloneableVO.setFirstName("Joe");
        entityCloneableVO.setLastName("Dummy");

        EntityCloneableVO entityCloneableVOCloned = (EntityCloneableVO) entityCloneableVO.clone();

        assertEquals("id", entityCloneableVOCloned.getId());
        assertEquals("Joe", entityCloneableVOCloned.getFirstName());
        assertEquals("Dummy", entityCloneableVOCloned.getLastName());
        assertNotSame(entityCloneableVO, entityCloneableVOCloned);
    }

}
