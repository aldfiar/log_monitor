package name.ivan.reader.file;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public abstract class ParentEventReaderTest {
    private static final String LOG_NAME = "sample.log";
    Reader reader;
    Path file;
    Path nonExistingFile;

    @BeforeClass
    public void setUp() {
        String resourceByName = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(LOG_NAME))
                .getPath();
        file = Paths.get(resourceByName);
        nonExistingFile = Paths.get("/wrong/path");
    }

    @Test
    public void testReadLine() {
        List<String> lines = reader.read();
        Assert.assertNotEquals(lines.size(), 0);
    }


}
