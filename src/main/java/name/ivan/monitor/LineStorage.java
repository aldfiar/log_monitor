package name.ivan.monitor;

import name.ivan.monitor.find.KeyFinder;
import name.ivan.monitor.report.Reporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineStorage {
    private final KeyFinder<String> finder;
    private Map<String, List<String>> map = new HashMap<>();

    public LineStorage(KeyFinder<String> finder) {
        this.finder = finder;
    }

    public String add(String line) {
        String key = finder.find(line);
        map.computeIfAbsent(key, t -> new ArrayList<>());
        List<String> strings = map.get(key);
        strings.add(line);
        return key;
    }

    public void report(String key, Reporter reporter) {
        reporter.generateReport(map.get(key));
    }

}
