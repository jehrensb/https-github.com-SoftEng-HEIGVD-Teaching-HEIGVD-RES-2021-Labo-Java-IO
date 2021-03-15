package ch.heigvd.res.labio.impl;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to extract
     * the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the argument does not
     * contain any line separator, then the first element is an empty string.
     */

    private static final String[] LINE_SEPARATORS = {"\n", "\r", "\r\n"};


    public static String[] getNextLine(String lines) {
        String[] result = {"", lines};
        Arrays.stream(LINE_SEPARATORS)
                .filter(lines::contains)
                .mapToInt(s -> lines.indexOf(s) + s.length())
                .forEachOrdered(index -> {
                    result[0] = lines.substring(0, index);
                    result[1] = lines.substring(index);
                });
        return result;
    }

}
