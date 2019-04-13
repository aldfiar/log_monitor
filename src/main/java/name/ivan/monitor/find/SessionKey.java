package name.ivan.monitor.find;

public class SessionKey implements KeyFinder<String> {
    private static final String leftSeparator = "[";
    private static final String rightSeparator = "]";

    @Override
    public String find(String value) {
        int leftIndex = value.indexOf(leftSeparator);
        int rightIndex = value.indexOf(rightSeparator);
        return value.substring(leftIndex, rightIndex + 1);
    }
}
