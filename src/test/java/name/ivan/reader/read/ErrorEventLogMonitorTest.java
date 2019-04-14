package name.ivan.reader.read;

import org.testng.annotations.BeforeMethod;

public class ErrorEventLogMonitorTest extends ParentEventLogMonitorTest {

    @BeforeMethod
    public void setUp() {
        monitor = new ErrorEventLogMonitor(reporter, reader, storage);
    }

}