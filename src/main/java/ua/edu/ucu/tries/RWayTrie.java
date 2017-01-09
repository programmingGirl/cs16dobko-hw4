package ua.edu.ucu.tries;


public class RWayTrie implements Trie {

    private Node root = new Node();
    private int size;

    @Override
    public void add(Tuple t) {
        char[] charsList = t.term.toLowerCase().toCharArray();
        int index;

        Node thisNode = root;    // beginning
        for (int i = 0; i < charsList.length; ++i) {
            index = (int) charsList[i] - 97;                    // array value of current char
            if (thisNode.nextWords[index] == null)
                thisNode.nextWords[index] = new Node();
            thisNode = thisNode.nextWords[index];
        }
        thisNode.value = t.weight;
    }

    @Override
    public boolean contains(String word) {
        return false;

    }

    @Override
    public boolean delete(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        return this.size;

    }

    private class Node {
        Node[] nextWords;
        int value = 0;                 // a word's weight
        Node() {
            nextWords = new Node[26];
        }
    }
}
