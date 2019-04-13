package name.ivan.monitor;

import name.ivan.monitor.read.Reader;
import name.ivan.monitor.report.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class EventMonitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventMonitor.class);
    private Reporter reporter;
    private Reader reader;
    private LineStorage storage;
    private Predicate<String> eventPredicate;

    public EventMonitor(Reporter reporter, Reader reader, LineStorage storage) {
        this.reporter = reporter;
        this.reader = reader;
        this.storage = storage;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public void addCondition(Predicate<String> predicate) {
        eventPredicate = predicate;
    }

    public void monitor() {

        List<String> read = reader.read();

        read.forEach((s) -> {
            String key = storage.add(s);
            if (eventPredicate.test(s)) {
                storage.report(key, reporter);
            }
        });
    }

}
