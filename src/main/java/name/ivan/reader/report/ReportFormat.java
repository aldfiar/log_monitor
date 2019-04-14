package name.ivan.reader.report;

import java.util.StringJoiner;

public class ReportFormat {
    private final String separator;
    private final int limit;

    public ReportFormat(String separator, int limit) {
        this.separator = separator;
        this.limit = limit;
    }

    public String getSeparator() {
        return separator;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReportFormat.class.getSimpleName() + "[", "]")
                .add("separator='" + separator + "'")
                .add("limit=" + limit)
                .toString();
    }
}
