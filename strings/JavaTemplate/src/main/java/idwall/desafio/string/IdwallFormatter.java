package idwall.desafio.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    private final boolean justified;

    public IdwallFormatter(int limit, boolean justified) {
        this.limit = limit;
        this.justified = justified;
    }

    public boolean isJustified() {
        return justified;
    }

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
        StringBuilder textFormat = new StringBuilder();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            String treatedWord;
            boolean lineContainsLineBreak = word.contains("\n");

            if (lineContainsLineBreak) {
                treatedWord = word.substring(0, word.indexOf("\n"));

                if (line.length() + treatedWord.length() < limit) {
                    line.append(" ").append(treatedWord);
                    justify(line);
                }

                int breakLinesQuantity = StringUtils.countMatches(word, "\n");

                for (int i = 0; i < breakLinesQuantity; i++)
                    line.append("\n");

                textFormat.append(line);
                line = new StringBuilder();
                treatedWord = word.substring(word.lastIndexOf("\n") + 1);
            } else {
                treatedWord = word;
            }

            if (line.length() + treatedWord.length() >= limit) {

                justify(line);

                line = new StringBuilder(line);

                line.append("\n");
                textFormat.append(line);


                line = new StringBuilder(word + " ");
            } else {
                if (line.toString().equals(""))
                    line.append(treatedWord);
                else
                    line.append(" ").append(treatedWord);
            }
        }

        return textFormat.toString();
    }

    private void justify(StringBuilder line) {
        if (!isJustified())
            return;

        try {
            int missingChars = limit - line.length();
            int lastIndexOf = 0;

            for (int i = 0; i < missingChars; i++) {
                if (lastIndexOf >= line.length()) {
                    lastIndexOf = 0;
                }

                lastIndexOf = line.indexOf(" ", lastIndexOf);
                line.insert(lastIndexOf, " ");
                lastIndexOf += 2;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
