package name.ivan.reader.store;

import name.ivan.reader.find.KeyFinder;

import java.util.*;

/**
 * @param <T> type of key
 * @param <Z> type of stored data
 *            <p>
 *            Store information, associated with key
 *            Key extracts from data with help of {@link KeyFinder}
 */
public abstract class AbstractStorage<T, Z> implements Storage<T, Z> {
    private final KeyFinder<T, Z> finder;
    private Map<T, List<Z>> map = new HashMap<>();

    public AbstractStorage(KeyFinder<T, Z> finder) {
        this.finder = finder;
    }

    public List<Z> getEvents(String key) {
        return map.get(key);
    }

    public T add(Z line) {
        T key = finder.find(line);
        map.computeIfAbsent(key, t -> new ArrayList<>());
        List<Z> strings = map.get(key);
        strings.add(line);
        return key;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractStorage.class.getSimpleName() + "[", "]")
                .add("finder=" + finder)
                .add("map=" + map)
                .toString();
    }
}
