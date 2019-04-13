package name.ivan.monitor.read;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class RandomReaderTest {
    private final String name = "sample.log";
    private Path file;

    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        String resourceByName = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(name))
                .getPath();
        file = Paths.get(resourceByName);
    }

    @Test
    public void testReadLine() throws InterruptedException, FileNotFoundException {
        RandomReader randomAccessFileReader = new RandomReader(file.toFile());
        for (int i = 0; i < 5; i++) {
            System.out.println(randomAccessFileReader.read());
        }
    }
}