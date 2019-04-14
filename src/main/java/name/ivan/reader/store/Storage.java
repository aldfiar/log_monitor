package name.ivan.reader.store;

import java.util.List;

/**
 * @param <T> key type
 * @param <Z> value type
 */
public interface Storage<T, Z> {
    Z add(String line);

    List<Z> getEvents(T key);
}
