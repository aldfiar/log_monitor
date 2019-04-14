package name.ivan.reader.read;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventEventReaderTest extends ParentEventReaderTest {
    private EventReader eventReader;

    @BeforeMethod
    public void setUp() {
        eventReader = new EventReader(reporter, reader, storage);
        eventReader.setPredicate((s -> true));
    }

    @Test
    public void testEventReader() {
        eventReader.monitor();
    }
}