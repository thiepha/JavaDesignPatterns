package designpattern;

// Flyweight: save memory (like zipping), use index instead of real data to reduce size

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// normal way: consumed a lot of memory
class FormattedTextFL {
    private final String plainText;
    private final boolean[] capitalize;

    public FormattedTextFL(String plainText) {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public boolean capitalize(int start, int end) {
        if (start < 0 || start > end || end >= capitalize.length) {
            return false;
        }
        for (int i = start; i <= end; i++) {
            capitalize[i] = true;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = capitalize[i] ? Character.toUpperCase(plainText.charAt(i))
                    : plainText.charAt(i);
            sb.append(c);
        }

        return sb.toString();
    }
}

// Flyweight pattern
class BetterFormattedTextFL {
    private final String plainText;
    private final List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedTextFL(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end) {
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            for (TextRange range : formatting) {
                if (range.cover(i) && range.capitalize) {
                    c = Character.toUpperCase(c);
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public class TextRange {
        private final int start, end;
        public boolean capitalize, italic, underline; //....

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean cover(int idx) {
            return idx >= start && idx <= end;
        }
    }
}

// another example
class Sentence
{
    private final String plainText;
    List<WordToken> wordTokens = new ArrayList<>();

    public Sentence(String plainText)
    {
        this.plainText = plainText;
    }

    public WordToken getWord(int index)
    {
        WordToken wordToken = new WordToken(index);
        wordTokens.add(wordToken);
        return wordToken;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (c == ' ') {
                idx++;
            } else {
                for (WordToken wordToken : wordTokens) {
                    if (idx == wordToken.getIdx() && wordToken.capitalize) {
                        c = Character.toUpperCase(c);
                    }
                }
            }
            sb.append(c);
        }

        return sb.toString();
    }

    class WordToken
    {
        private final int idx;
        public boolean capitalize;

        public WordToken(int idx) {
            this.idx = idx;
        }

        public int getIdx() {
            return idx;
        }
    }
}

class Sentence1 {
    private final String[] words;
    private final HashMap<Integer, WordToken> tokens;

    public Sentence1(String plainText) {
        words = plainText.split(" ");
        tokens = new HashMap<>();
    }

    public WordToken getToken(int idx) {
        WordToken token = new WordToken();
        tokens.put(idx, token);
        return token;
    }

    @Override
    public String toString() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (tokens.containsKey(i) && tokens.get(i).capitalize) {
                word = word.toUpperCase();
            }
            list.add(word);
        }

        return String.join(" ", list);
    }

    class WordToken {
        public boolean capitalize;
    }
}

public class FlyweightTextFormat {
    public static void main(String[] args) {
        String s = "Make the America Great Again";
        FormattedTextFL ft = new FormattedTextFL(s);
        ft.capitalize(9, 15);
        ft.capitalize(17, 21);
        System.out.println(ft);

        // Flyweight
        BetterFormattedTextFL bft = new BetterFormattedTextFL(s);
        BetterFormattedTextFL.TextRange range = bft.getRange(0, 3);
        range.capitalize = true;
        bft.getRange(9, 15).capitalize = true;
        bft.getRange(17, 21).capitalize = true;
        System.out.println(bft);

        Sentence sentence = new Sentence(s);
        sentence.getWord(2).capitalize = true;
        sentence.getWord(4).capitalize = true;
        System.out.println(sentence);

        Sentence1 sentence1 = new Sentence1(s);
        sentence1.getToken(2).capitalize = true;
        sentence1.getToken(4).capitalize = true;
        System.out.println(sentence1);
    }
}
