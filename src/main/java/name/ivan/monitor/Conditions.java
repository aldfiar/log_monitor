package name.ivan.monitor;

import java.util.function.Predicate;

public class Conditions {

    public static Predicate<String> isError() {
        return s -> s.contains("ERROR");
    }
}
