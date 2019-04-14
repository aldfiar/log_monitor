package name.ivan.reader.file;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class WaitingReaderTest extends ParentReaderTest {
    @BeforeMethod
    public void createReader() throws FileNotFoundException {
        reader = new WaitingReader(file);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void testWrongFile() throws FileNotFoundException {
        reader = new WaitingReader(nonExistingFile);
        List<String> lines = reader.read();
        Assert.assertEquals(lines.size(), 0);
    }

}