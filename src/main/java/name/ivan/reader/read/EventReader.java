package name.ivan.reader.read;

import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.store.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public abstract class EventReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventReader.class);
    Reporter reporter;
    Reader reader;
    Storage<String, String> storage;
    Predicate<String> eventPredicate;

    public EventReader(Reporter reporter, Reader reader, Storage storage) {
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

    public void monitor() {

        if (eventPredicate == null) {
            throw new IllegalStateException("Set predicate first");
        }

        List<String> read = reader.read();

        read.forEach((s) -> {
            String key = storage.add(s);
            if (eventPredicate.test(s)) {
                LOGGER.debug("Find event. Generate report");

                List<String> events = storage.getEvents(key);
                reporter.generateReport(events);
            }
        });
    }

}
