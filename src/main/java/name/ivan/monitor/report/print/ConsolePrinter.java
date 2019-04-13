package name.ivan.monitor.report.print;

import java.util.List;

public class ConsolePrinter implements Printer {
    @Override
    public void print(List<String> values) {
        values.forEach(System.out::println);
    }
}
