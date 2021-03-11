package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int nb_lines = 0;
  private int prevChar = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    while(len-- > 0){
      write(str.charAt(off++));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    while(len-- > 0){
      write(cbuf[off++]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // First 1\t
    if (nb_lines == 0) {
      nb_lines++;
      out.write(String.valueOf(nb_lines));
      out.write('\t');
    }
    // ASCII 13 -> \r ASCII 10 -> \n
    if (c == 13) {
        prevChar = c;
        out.write(c);
    } else if (c == 10) {
      out.write(c);
      nb_lines++;
      out.write(String.valueOf(nb_lines));
      out.write('\t');
      prevChar = 0;
    } else {
      if (prevChar != 0) {
        nb_lines++;
        out.write(String.valueOf(nb_lines));
        out.write('\t');
        prevChar = 0;
      }
      out.write(c);
    }
  }
}
