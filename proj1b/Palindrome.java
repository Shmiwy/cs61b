public class Palindrome {
    /** Given a String, return a Deque with characters appear
     *  in the same order as in the String */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**  return true if the given word is a palindrome, and false */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        OffByN offBy0 = new OffByN(0);
        return isisPalindromeHelper(deque, offBy0);
    }

    /**  return true if the given word is a palindrome, and false */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isisPalindromeHelper(deque, cc);
    }

    /**  the helper method of the isPalindrome using recursion */
    private boolean isisPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() <= 1) {
            return true;
        } else {
            char last = deque.removeLast();
            char first = deque.removeFirst();
            if (!cc.equalChars(last, first)) {
                return false;
            } else {
                return isisPalindromeHelper(deque, cc);
            }
        }
    }

}
