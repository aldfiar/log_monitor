package name.ivan.reader.report.impl;

import name.ivan.reader.report.ReportFormat;
import name.ivan.reader.report.print.Printer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleReporterTest {
    SimpleReporter reporter;
    int size;
    private String separator = "-";
    private int limit = 4;
    private List<String> list;
    private String line = "line";
    private ReportFormat reportFormat;
    private Printer printer;

    @BeforeMethod
    public void setUp() {
        reportFormat = new ReportFormat(separator, limit);

        printer = lines -> {
            Assert.assertTrue(lines.contains(separator));
            Assert.assertEquals(lines.size(), limit + 1);
        };
    }

    @Test
    public void testListLargerThanLimit() {
        size = 8;
        list = Stream.generate(() -> line).limit(size).collect(Collectors.toList());
        reporter = new SimpleReporter(reportFormat, printer);
        reporter.generateReport(list);
    }

    @Test
    public void testListSmallerThanLimit() {
        size = 2;

        printer = lines -> {
            Assert.assertTrue(lines.contains(separator));
            Assert.assertEquals(lines.size(), size + 1);
        };

        list = Stream.generate(() -> line).limit(size).collect(Collectors.toList());
        reporter = new SimpleReporter(reportFormat, printer);
        reporter.generateReport(list);
    }
}