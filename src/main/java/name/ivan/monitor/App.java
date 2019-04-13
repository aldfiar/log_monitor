package name.ivan.monitor;

import name.ivan.monitor.find.SessionKey;
import name.ivan.monitor.read.RandomReader;
import name.ivan.monitor.report.ReportFormat;
import name.ivan.monitor.report.impl.SimpleReporter;
import name.ivan.monitor.report.print.ConsolePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Path path = Paths.get("/home/darthmantis/Documents/sample.log");

        ReportFormat reportFormat = new ReportFormat("----", 4);

        ConsolePrinter consolePrinter = new ConsolePrinter();

        SimpleReporter simpleReporter = new SimpleReporter(reportFormat, consolePrinter);

        RandomReader reader = null;

        try {
            reader = new RandomReader(path.toFile());
        } catch (FileNotFoundException e) {
            LOGGER.warn("File: {} not found", path, e.fillInStackTrace());
            throw new IllegalStateException("Wrong path");
        }

        LineStorage lineStorage = new LineStorage(new SessionKey());

        EventMonitor eventMonitor = new EventMonitor(simpleReporter, reader, lineStorage);
        eventMonitor.addCondition(Conditions.isError());
        eventMonitor.monitor();
    }
}
