package name.ivan.reader.read;

import name.ivan.reader.file.Reader;
import name.ivan.reader.report.Reporter;

import java.util.function.Predicate;

public interface LogMonitor {
    void setReader(Reader reader);

    void setReporter(Reporter reporter);

    void setPredicate(Predicate<String> predicate);

    void process();
}
