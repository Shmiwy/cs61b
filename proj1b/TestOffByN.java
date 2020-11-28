import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static Palindrome palindrome = new Palindrome();
    @Test
    public void testEqualChars() {
        OffByN offBy0 = new OffByN(0);
        OffByN offBy1 = new OffByN(1);
        OffByN offBy2 = new OffByN(2);
        //test when N = 0;
        assertTrue(palindrome.isPalindrome("", offBy0));
        assertTrue(palindrome.isPalindrome("a", offBy0));
        assertTrue(palindrome.isPalindrome("aba", offBy0));
        assertFalse(palindrome.isPalindrome("abd", offBy0));
        //test when N = 1;
        assertTrue(palindrome.isPalindrome("", offBy1));
        assertTrue(palindrome.isPalindrome("a", offBy1));
        assertTrue(palindrome.isPalindrome("abb", offBy1));
        assertFalse(palindrome.isPalindrome("abo", offBy1));
        //test when N = 1;
        assertTrue(palindrome.isPalindrome("", offBy2));
        assertTrue(palindrome.isPalindrome("a", offBy2));
        assertTrue(palindrome.isPalindrome("abc", offBy2));
        assertFalse(palindrome.isPalindrome("abmn", offBy2));
    }
}
