package name.ivan.reader.file;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SimpleReaderTest extends ParentReaderTest {

    @BeforeMethod
    public void createReader() {
        reader = new SimpleReader(file);
    }

    @Test
    public void testWrongFile() {
        reader = new SimpleReader(nonExistingFile);
        List<String> lines = reader.read();
        Assert.assertEquals(lines.size(), 0);
    }


}