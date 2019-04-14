package name.ivan.reader.read;

import name.ivan.reader.Conditions;
import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.store.Storage;

public class ErrorEventReader extends EventReader {
    public ErrorEventReader(Reporter reporter, Reader reader, Storage storage) {
        super(reporter, reader, storage);
        super.eventPredicate = Conditions.isError();
    }
}