package ch.heigvd.res.labio.impl;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());
    public static final char EOL_UNIX = '\n';
    public static final char EOL_MACOS9 = '\r';
    public static final String EOL_WINDOWS = "\r\n";
    private static final String[] LINE_SEPARATORS = {String.valueOf(EOL_UNIX), String.valueOf(EOL_MACOS9), EOL_WINDOWS};

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to extract
     * the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the argument does not
     * contain any line separator, then the first element is an empty string.
     */
    public static String[] getNextLine(String lines) {
        String[] result = {"", lines};
        Arrays.stream(LINE_SEPARATORS).forEach(s -> {
            if (lines.contains(s)) {
                int index = lines.indexOf(s) + s.length();
                result[0] = lines.substring(0, index);
                result[1] = lines.substring(index);
            }
        });
        return result;
    }

}
