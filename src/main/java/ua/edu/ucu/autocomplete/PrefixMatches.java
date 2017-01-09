package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;

import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;


/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;


    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        //Returns quantity of words, which were added
        int count = 0;
        for (String line: strings) {
            String[] str = line.split(" ");
            for (String word: str) {
                if (word.length() > 2) {
                    trie.add(new Tuple(word, word.length()));
                    count++;
                }
            }
        }
        return count;

    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if(pref.length() >=2){
            return trie.wordsWithPrefix(pref);
        }
        else{
            return null;
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        ArrayList<String> finalList;
        // all words with such prefix will be stored in allWordsThisPrefix
        Iterable<String> allWordsThisPrefix = trie.wordsWithPrefix(pref);

        finalList = new ArrayList<String>();
        for (String word : allWordsThisPrefix) {
            if (k + pref.length() > word.length()) {
                finalList.add(word);
            }
        }
        return finalList;

    }

    public int size() {
        return trie.size();
    }
}
