package name.ivan.monitor.report.impl;

import name.ivan.monitor.report.ReportFormat;
import name.ivan.monitor.report.Reporter;
import name.ivan.monitor.report.print.Printer;

import java.util.List;
import java.util.stream.Collectors;

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
            collect = stringList;
        } else {
            collect = stringList.stream()
                    .skip(count - linesForSession)
                    .collect(Collectors.toList());
        }

        collect.add(reportFormat.getSeparator());

        printer.print(collect);
    }
}
