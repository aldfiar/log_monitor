package name.ivan.reader.read;

import name.ivan.reader.Conditions;
import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.store.Storage;

import java.util.StringJoiner;

public class ErrorEventLogMonitor extends EventLogMonitor {
    public ErrorEventLogMonitor(Reporter reporter, Reader reader, Storage storage) {
        super(reporter, reader, storage);
        super.eventPredicate = Conditions.isError();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorEventLogMonitor.class.getSimpleName() + "[", "]")
                .add("reporter=" + reporter)
                .toString();
    }
}