import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByone = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        // test for the method has only one parameter:word
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("abc"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("abcba"));

        // test for the method has two parameters: word, cc
        assertTrue(palindrome.isPalindrome("", offByone));
        assertTrue(palindrome.isPalindrome("a", offByone));
        assertTrue(palindrome.isPalindrome("ab", offByone));
        assertFalse(palindrome.isPalindrome("am", offByone));
        assertTrue(palindrome.isPalindrome("abcdcb", offByone));
        assertFalse(palindrome.isPalindrome("abcdrb", offByone));
        assertTrue(palindrome.isPalindrome("abcedcb", offByone));
        assertFalse(palindrome.isPalindrome("awcedcb", offByone));
    }
}
