package name.ivan.reader;

import name.ivan.reader.file.Reader;
import name.ivan.reader.file.SimpleReader;
import name.ivan.reader.read.ErrorEventReader;
import name.ivan.reader.read.EventReader;
import name.ivan.reader.report.ReportFormat;
import name.ivan.reader.report.impl.SimpleReporter;
import name.ivan.reader.report.print.ConsolePrinter;
import name.ivan.reader.report.print.Printer;
import name.ivan.reader.store.SessionEventStorage;
import name.ivan.reader.store.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * Before execution, system property must be set (-Dpath=path_to_log_file)
     */
    public static void main(String[] args) {
        String pathToFile = System.getProperty("path");
        Path path = Paths.get(pathToFile);
        Reader reader = new SimpleReader(path);

        ReportFormat reportFormat = new ReportFormat("----", 4);
        Printer printer = new ConsolePrinter();

        SimpleReporter simpleReporter = new SimpleReporter(reportFormat, printer);

        Storage<String, String> storage = new SessionEventStorage();

        EventReader eventReader = new ErrorEventReader(simpleReporter, reader, storage);
        eventReader.process();
    }
}
