package de.arthurpicht.barnacleRuntimeTest.tg_01.tc_09;

import de.arthurpicht.barnacleGeneratorTest.tg_01.tc_09.persistence.vo.EntitySerializableVO;
import de.arthurpicht.utils.io.serialize.GenericObjectSerializer;
import de.arthurpicht.utils.io.serialize.SerializerException;
import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase0109 {

    @Test
    public void testSerializable() throws IOException, SerializerException {
        EntitySerializableVO entitySerializableVO = new EntitySerializableVO("id");
        entitySerializableVO.setFirstName("Joe");
        entitySerializableVO.setLastName("Dummy");

        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(".");

        File serFile = new File(tempDir.asFile(), "tc_09.ser");
        GenericObjectSerializer.serialize(entitySerializableVO, serFile);
        EntitySerializableVO entityDeserialized = GenericObjectSerializer.deserialize(serFile);

        assertEquals("id", entityDeserialized.getId());
        assertEquals("Joe", entityDeserialized.getFirstName());
        assertEquals("Dummy", entityDeserialized.getLastName());
    }

}
