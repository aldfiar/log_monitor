package name.ivan.reader.read;

import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.store.AbstractStorage;
import name.ivan.reader.store.Storage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class ParentEventReaderTest {
    List<String> list;
    String line = "ERROR. Hello World";
    Reporter reporter;
    Reader reader;
    Storage<String, String> storage;

    @BeforeMethod
    public void setComponent() {
        list = new ArrayList<>();
        list.add(line);

        reporter = stringList -> Assert.assertTrue(stringList.contains(line));

        reader = () -> list;

        storage = new TestStore();
    }

    private class TestStore extends AbstractStorage<String, String> {
        TestStore() {
            super((string) -> "");
        }

        @Override
        public List<String> getEvents(String key) {
            return list;
        }

        @Override
        public String add(String line) {
            return "";
        }
    }
}
