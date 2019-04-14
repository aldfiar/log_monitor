package name.ivan.reader.read;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ErrorEventReaderTest extends ParentEventReaderTest {
    private ErrorEventReader eventReader;

    @BeforeMethod
    public void setUp() {
        eventReader = new ErrorEventReader(reporter, reader, storage);
    }

    @Test
    public void testEventReader() {
        eventReader.monitor();
    }
}