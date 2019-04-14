package name.ivan.reader.store;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AbstractStorageTest {
    private static String key = "key";
    private Storage<String, String> storage;

    @BeforeMethod
    public void setUp() {
        storage = new StorageTest();
    }

    @Test
    public void testAdd() {
        String value = "value";
        storage.add(value);
        List<String> events = storage.getEvents(key);
        Assert.assertEquals(value, events.get(0));
    }

    private class StorageTest extends AbstractStorage<String, String> {

        public StorageTest() {
            super((s) -> key);
        }
    }
}