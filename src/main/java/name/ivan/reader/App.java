package name.ivan.reader;

import name.ivan.reader.file.Reader;
import name.ivan.reader.file.SimpleReader;
import name.ivan.reader.read.EventLogMonitor;
import name.ivan.reader.read.LogMonitor;
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
    private LogMonitor monitor;

    App(LogMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Before execution, system property must be set (-Dpath=path_to_log_file)
     */
    public static void main(String[] args) {
        String pathToFile = System.getProperty("path");
        LOGGER.info("Path to log file: {}", pathToFile);
        Path path = Paths.get(pathToFile);

        LogMonitor monitor = writingToConsoleMonitor(path);

        App app = new App(monitor);
        app.run();
    }

    private static LogMonitor writingToConsoleMonitor(Path path) {
        ReportFormat reportFormat = new ReportFormat("----", 4);
        Printer printer = new ConsolePrinter();
        SimpleReporter simpleReporter = new SimpleReporter(reportFormat, printer);

        Reader reader = new SimpleReader(path);

        Storage<String, String> storage = new SessionEventStorage();

        return new EventLogMonitor(simpleReporter, reader, storage);
    }

    void run() {
        monitor.setPredicate(Conditions.isError());
        monitor.process();
    }
}
