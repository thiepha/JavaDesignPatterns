public class Trie {
    private static final int CHAR_NUM = 26;
    static class Node {
        Node[] children;
        boolean exist;

        Node () {
            children = new Node[CHAR_NUM];
            exist = false;
            for (int i = 0; i < CHAR_NUM; i++) {
                children[i] = null;
            }
        }
    }

    private Node root;

    Trie() {
        root = new Node();
    }

    void add(String s) {
        if (s == null) return;
        Node run = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (run.children[idx] == null) {
                run.children[idx] = new Node();
            }
            run = run.children[idx];
        }
        run.exist = true;
    }

    boolean find(String s) {
        if (s == null) return false;
        Node run = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (run.children[idx] == null) return false;
            run = run.children[idx];
        }
        return (run != null && run.exist);
    }

    boolean deleteHelper(Node n, char[] arr, int idx) {
        if (n == null) return false;
        int charIdx = arr[idx] - 'a';
        Node child = n.children[charIdx];
        if (idx == arr.length - 1) {
            if (child == null) return false;
            else {
                boolean hasOther = false;
                for (int i = 0; i < CHAR_NUM; i++) {
                    if ((i != charIdx) && (child.children[i] != null)) {
                        hasOther = true;
                        break;
                    }
                }
                if (!hasOther) {
                    n.children[charIdx] = null;
                    return true;
                } else {
                    child.exist = false;
                    return false;
                }
            }
        } else {
            if (!deleteHelper(child, arr, idx + 1)) {
                return false;
            } else {
                n.children[arr[idx + 1] - 'a'] = null;
                boolean hasOther = false;
                for (int i = 0; i < CHAR_NUM; i++) {
                    if (n.children[i] != null) {
                        hasOther = true;
                        break;
                    }
                }
                if (hasOther) return false;
                else return true;
            }
        }
    }

    void delete(String s) {
        deleteHelper(root, s.toCharArray(), 0);
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("the");
        trie.add("then");
        trie.add("them");
        trie.add("and");
        trie.add("fast");

        System.out.println(trie.find("then"));
        System.out.println(trie.find("no"));
        System.out.println(trie.find("themo"));
        System.out.println(trie.find("and"));

        System.out.println("After deleting:");
        trie.delete("the");
        System.out.println(trie.find("then"));
        System.out.println(trie.find("the"));
        trie.delete("and");
        System.out.println(trie.find("and"));
        trie.delete("no");
    }
}
