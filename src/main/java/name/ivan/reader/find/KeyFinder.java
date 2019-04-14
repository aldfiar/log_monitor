package name.ivan.reader.find;

/**
 * @param <T> type of key value
 * @param <Z> type of data, where we want to find key
 */
public interface KeyFinder<T, Z> {
    T find(Z value);
}
