package name.ivan.reader.report;

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
}
