package name.ivan.reader.store;

import name.ivan.reader.find.SessionKeyFinder;

public class SessionEventStorage extends AbstractStorage<String, String> {

    public SessionEventStorage() {
        super(new SessionKeyFinder());
    }
}
