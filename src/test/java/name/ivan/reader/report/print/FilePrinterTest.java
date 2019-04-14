package name.ivan.reader.report.print;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePrinterTest {

    private Path path;
    private String sentense = "Hello world";

    @BeforeMethod
    public void setUp() throws IOException {
        path = Paths.get("./test_file.txt");
        path.toFile().createNewFile();
    }

    @Test
    public void testWriter() throws IOException {
        FilePrinter printer = new FilePrinter(path);

        List<String> list = new ArrayList<>();
        list.add(sentense);

        printer.print(list);

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();

        Assert.assertEquals(line, sentense);
    }


    @AfterMethod
    public void tearDown() {
        path.toFile().delete();
    }


}