package ch.heigvd.res.labio.impl;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.logging.Logger;

/**
 *
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
  public static String[] getNextLine(String lines) {
        final String  SEPARATOR_WINDOWS = "\r\n";
        final String  SEPARATOR_MAC_OLD = "\r";
        final String  SEPARATOR_UNIX = "\n";
        String currentSeparator;

        if(lines.contains(SEPARATOR_WINDOWS)) {
            currentSeparator = SEPARATOR_WINDOWS;
        }
        else if(lines.contains(SEPARATOR_MAC_OLD)) {
            currentSeparator = SEPARATOR_MAC_OLD;}
        else {
            currentSeparator = SEPARATOR_UNIX;
        }

        int positionSecondLine = lines.indexOf(currentSeparator) + currentSeparator.length();
        String[] result = new String[2];
        result[0] = lines.substring(0, positionSecondLine);
        result[1] = lines.substring(positionSecondLine);

        return result;
  }

}
