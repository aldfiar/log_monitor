package name.ivan.reader.report;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReportFormatTest {
    private String separator = "-";
    private int limit = 3;

    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void testReportFormat() {
        ReportFormat reportFormat = new ReportFormat(separator, limit);
        Assert.assertEquals(reportFormat.getSeparator(), separator);
        Assert.assertEquals(reportFormat.getLimit(), limit);
    }
}