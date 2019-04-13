package name.ivan.monitor.find;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SessionKeyTest {
    private String key = "[one]";
    private String testString = String.format("11.04.1999 %s What`s happened", key);

    @Test
    public void testSessionFind() {
        SessionKey sessionKey = new SessionKey();
        String keyFromFinder = sessionKey.find(testString);
        assertEquals(keyFromFinder, key);
    }

}