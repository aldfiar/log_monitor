package name.ivan.reader.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class WaitingReader implements Reader {
    private static final Logger LOGGER = LoggerFactory.getLogger(WaitingReader.class);
    private static final long sleepTime = 100;
    private static final int tries = 5;
    private final RandomAccessFile file;
    private long pointer;

    public WaitingReader(Path path) throws FileNotFoundException {
        this.file = new RandomAccessFile(path.toFile(), "r");
    }

    public List<String> read() {
        List<String> list = new ArrayList<>();

        try {
            int t = tries;
            while (file.length() >= pointer) {

                if (pointer != 0L) {
                    file.seek(pointer);
                }

                String value = file.readLine();

                pointer = file.getFilePointer();

                if (value == null || value.isEmpty()) {

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        LOGGER.debug("Interrupted while sleep", e.fillInStackTrace());
                    }

                    t--;
                    if (t <= 0) {
                        break;
                    }

                } else {
                    list.add(value);
                }
            }

            file.close();
        } catch (IOException e) {
            LOGGER.error("File: {} read problem", file, e.fillInStackTrace());
        }

        return list;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WaitingReader.class.getSimpleName() + "[", "]")
                .add("file=" + file)
                .add("pointer=" + pointer)
                .toString();
    }
}
