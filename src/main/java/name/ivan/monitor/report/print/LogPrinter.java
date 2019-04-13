package name.ivan.monitor.report.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LogPrinter implements Printer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogPrinter.class);

    @Override
    public void print(List<String> values) {
        values.forEach(LOGGER::info);
    }
}
