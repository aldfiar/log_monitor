package name.ivan.reader.find;

public class SessionKeyFinder implements KeyFinder<String, String> {
    @Override
    public String find(String value) {
        String leftSeparator = "[";
        int leftIndex = value.indexOf(leftSeparator);
        String rightSeparator = "]";
        int rightIndex = value.indexOf(rightSeparator);
        return value.substring(leftIndex, rightIndex + 1);
    }
}
