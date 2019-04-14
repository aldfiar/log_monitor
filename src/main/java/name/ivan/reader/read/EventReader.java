package name.ivan.reader.read;

import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.store.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 *
 */
public class EventReader {
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
        LOGGER.info("Change reader: {}", reader);
        this.reader = reader;
    }

    public void setReporter(Reporter reporter) {
        LOGGER.info("Change reporter: {}", reporter);
        this.reporter = reporter;
    }

    void setPredicate(Predicate<String> predicate) {
        this.eventPredicate = predicate;
    }

    public void monitor() {

        if (eventPredicate == null) {
            throw new IllegalStateException("Set predicate first");
        }

        List<String> read = reader.read();

        read.forEach((s) -> {
            String key = storage.add(s);
            if (eventPredicate.test(s)) {
                List<String> events = storage.getEvents(key);
                reporter.generateReport(events);
            }
        });
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EventReader.class.getSimpleName() + "[", "]")
                .add("reporter=" + reporter)
                .add("reader=" + reader)
                .add("storage=" + storage)
                .add("eventPredicate=" + eventPredicate)
                .toString();
    }
}
