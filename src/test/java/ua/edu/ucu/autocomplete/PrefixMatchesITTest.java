
package ua.edu.ucu.autocomplete;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testSize() {
        int result = pm.size();
        int expected = 5;
        assertEquals(expected, result);
    }
    @Test
    public void testSizeAfterChange(){
        pm.delete("abce");
        int result = pm.size();
        int expected = 4;
        assertEquals(expected, result);
    }

    @Test
    public void testFalseContains() {
        boolean result = pm.contains("ADVCF");
        boolean expected = false;
        assertEquals(result, expected);
    }

    @Test
    public void testTrueContains() {
        boolean result = pm.contains("abc");
        boolean expected = true;
        assertEquals(result, expected);
    }


    @Test
    public void testDelete() {
        boolean result = pm.delete("abcdef");
        boolean expected = true;
        assertEquals(result, expected);
    }

    @Test
    public void testNotDelete() {
        boolean res = pm.delete("jklo");
        boolean expRes = false;
        assertEquals(res, expRes);
    }

    @Test
    public void testWordsWithShortPrefix() {
        String pref = "a";
        Iterable<String> result = pm.wordsWithPrefix(pref);
        assertEquals(result, null);
    }
    @Test
    public void testWordsWithPrefix() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};
        assertThat(result, containsInAnyOrder(expResult));
    }
}
