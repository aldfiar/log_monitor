package name.ivan.monitor.report.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FilePrinter implements Printer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilePrinter.class);
    private final Path path;

    public FilePrinter(Path path) {
        this.path = path;
    }

    @Override
    public void print(List<String> values) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            values.forEach((s) -> {
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (IOException e) {
                    LOGGER.warn("Error while write attempt", e.fillInStackTrace());
                }
            });
        } catch (IOException e) {
            LOGGER.warn("Write error for path:{}", path, e.fillInStackTrace());
        }
    }
}
