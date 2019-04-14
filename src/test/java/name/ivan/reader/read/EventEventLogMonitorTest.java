package name.ivan.reader.read;

import org.testng.annotations.BeforeMethod;

public class EventEventLogMonitorTest extends ParentEventLogMonitorTest {

    @BeforeMethod
    public void setUp() {
        monitor = new EventLogMonitor(reporter, reader, storage);
        monitor.setPredicate((s -> true));
    }

}