package name.ivan.reader;

import name.ivan.reader.file.Reader;
import name.ivan.reader.file.SimpleReader;
import name.ivan.reader.read.EventLogMonitor;
import name.ivan.reader.report.ReportFormat;
import name.ivan.reader.report.impl.SimpleReporter;
import name.ivan.reader.report.print.FilePrinter;
import name.ivan.reader.report.print.Printer;
import name.ivan.reader.store.SessionEventStorage;
import name.ivan.reader.store.Storage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AppTest {
    private static final String LOG_NAME = "sample.log";
    private static final String RESULT = "report.txt";
    private Path appResult = Paths.get("./app_result.txt");
    private Path source;
    private Path knownResult;
    private EventLogMonitor eventLogMonitor;


    @BeforeMethod
    public void setUp() throws IOException {
        appResult.toFile().createNewFile();

        String logFilePath = getTestResource(LOG_NAME);
        source = Paths.get(logFilePath);
        Reader reader = new SimpleReader(source);

        String resultFilePath = getTestResource(RESULT);
        knownResult = Paths.get(resultFilePath);

        Printer printer = new FilePrinter(appResult);
        ReportFormat reportFormat = new ReportFormat("----", 4);
        SimpleReporter simpleReporter = new SimpleReporter(reportFormat, printer);

        Storage<String, String> storage = new SessionEventStorage();
        eventLogMonitor = new EventLogMonitor(simpleReporter, reader, storage);
    }

    @Test
    public void testApp() throws IOException {
        App app = new App(eventLogMonitor);
        app.run();
        BufferedReader reader = Files.newBufferedReader(appResult);
        List<String> appWorkResult = reader.lines().collect(Collectors.toList());

        BufferedReader anotherReader = Files.newBufferedReader(knownResult);
        List<String> expectedResult = anotherReader.lines().collect(Collectors.toList());

        reader.close();
        anotherReader.close();

        Assert.assertEquals(expectedResult.size(), appWorkResult.size());
    }

    @AfterMethod
    public void tearDown() {
        appResult.toFile().delete();
    }

    private String getTestResource(String name) {
        return Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader()
                .getResource(name))
                .getPath();
    }
}