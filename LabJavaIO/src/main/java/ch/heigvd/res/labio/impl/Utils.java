package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String UNIX = "\n";
  private static final String MACOS = "\r";
  private static final String WINDOWS = "\r\n";

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

      if (lines.contains(UNIX))
          separator = UNIX;
      else if (lines.contains(MACOS))
          separator = MACOS;
      else if (lines.contains(WINDOWS))
          separator = WINDOWS;

      if(separator != null){
          int index = lines.indexOf(separator) + separator.length();
          result[0] = lines.substring(0,index);
          result[1] = lines.substring(index);
      }

      return result;
  }

}
