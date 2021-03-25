package ch.heigvd.res.labio.impl;

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
    String[] resp = new String[2];
    // Search for any line separator
    int splitAtRN = lines.indexOf("\r\n");
    int splitAtR = lines.indexOf("\r");
    int splitAtN = lines.indexOf("\n");

    int splitAt = -1;
    if (splitAtRN != -1){
      splitAt = splitAtRN + 2;
    } else if (splitAtR != -1) {
      splitAt = splitAtR + 1;
    } else if (splitAtN != -1) {
      splitAt = splitAtN + 1;
    }

    // Proceed to split if a separator was found
    if(splitAt == -1){
      resp[0] = "";
      resp[1] = lines;
    } else {
      resp[0]= lines.substring(0, splitAt);
      resp[1] = lines.substring(splitAt);
    }

    return resp;
  }

}
