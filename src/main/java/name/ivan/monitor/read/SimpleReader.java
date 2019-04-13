package name.ivan.monitor.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleReader implements Reader {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleReader.class);
    private final Path path;

    public SimpleReader(Path path) {
        this.path = path;
    }

    @Override
    public List<String> read() {
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            list = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.warn("Can`t read file from path: {}", path, e.fillInStackTrace());
            e.printStackTrace();
        }

        return list;
    }
}
