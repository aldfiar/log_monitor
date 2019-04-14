package name.ivan.reader.find;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SessionKeyFinderTest {
    private String key = "[one]";
    private String testString = String.format("11.04.1999 %s What`s happened", key);

    @Test
    public void testSessionFind() {
        SessionKeyFinder sessionKeyFinder = new SessionKeyFinder();
        String keyFromFinder = sessionKeyFinder.find(testString);
        assertEquals(keyFromFinder, key);
    }

}