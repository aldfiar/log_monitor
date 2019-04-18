package name.ivan.reader.report.impl;

import name.ivan.reader.report.ReportFormat;
import name.ivan.reader.report.Reporter;
import name.ivan.reader.report.print.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Generate with conditions from {@link ReportFormat}
 * Print formatted report via {@link Printer}
 */
public class SimpleReporter implements Reporter {
    private ReportFormat reportFormat;
    private Printer printer;

    public SimpleReporter(ReportFormat reportFormat, Printer printer) {
        this.reportFormat = reportFormat;
        this.printer = printer;
    }

    @Override
    public void generateReport(List<String> stringList) {
        int count = stringList.size();
        int linesForSession = reportFormat.getLimit();

        List<String> collect;
        if (linesForSession > count) {
            collect = new ArrayList<>(stringList);
        } else {
            collect = stringList.stream()
                    .skip(count - linesForSession)
                    .collect(Collectors.toList());
        }

        collect.add(reportFormat.getSeparator());

        printer.print(collect);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleReporter.class.getSimpleName() + "[", "]")
                .add("reportFormat=" + reportFormat)
                .add("printer=" + printer)
                .toString();
    }
}
