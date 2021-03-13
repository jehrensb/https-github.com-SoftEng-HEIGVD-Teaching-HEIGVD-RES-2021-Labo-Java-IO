package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());
    public static final String EOL_UNIX = "\n";
    public static final String EOL_MACOS9 = "\r";
    public static final String EOL_WINDOWS = "\r\n";

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
        String separator = null;

        // EOL char detection
        if (lines.contains(EOL_UNIX))
            separator = EOL_UNIX;
        else if (lines.contains(EOL_MACOS9))
            separator = EOL_MACOS9;
        else if (lines.contains(EOL_WINDOWS))
            separator = EOL_WINDOWS;

        // String split
        if (separator != null) {
            int index = lines.indexOf(separator) + separator.length();
            result[0] = lines.substring(0, index);
            result[1] = lines.substring(index);
        }

        return result;
    }

}
