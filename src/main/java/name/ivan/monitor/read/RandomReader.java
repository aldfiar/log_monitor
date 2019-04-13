package name.ivan.monitor.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class RandomReader implements Reader {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomReader.class);
    private static final long sleepTime = 500;
    private static final int tries = 5;
    private final RandomAccessFile file;
    private long pointer;

    public RandomReader(File file) throws FileNotFoundException {
        this.file = new RandomAccessFile(file, "r");
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

}
