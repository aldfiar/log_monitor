package name.ivan.reader.read;

import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.store.AbstractStorage;
import name.ivan.reader.store.Storage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class ParentEventLogMonitorTest {
    EventLogMonitor monitor;
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

    @Test
    public void testMonitor() {
        monitor.process();
    }

    @Test
    public void readerChange() {
        Reader reader = ArrayList::new;
        monitor.setReader(reader);
        Assert.assertEquals(monitor.reader, reader);
    }

    @Test
    public void reporterChange() {
        Reporter reporter = list -> list.isEmpty();
        monitor.setReporter(reporter);
        Assert.assertEquals(monitor.reporter, reporter);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testPredicateNotSet() {
        monitor.setPredicate(null);
        monitor.process();
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
