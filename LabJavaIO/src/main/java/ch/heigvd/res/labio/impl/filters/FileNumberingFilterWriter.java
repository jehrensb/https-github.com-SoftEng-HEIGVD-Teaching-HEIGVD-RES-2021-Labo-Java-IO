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
  private static int nb_lines = 0;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    while(len-- > 0){
      write(str.charAt(off++));
    }
    nb_lines = 0;
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    while(len-- > 0){
      write(cbuf[off++]);
    }
    nb_lines = 0;
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    if(nb_lines == 0){
      out.write(nb_lines);
      out.write(' ');
      nb_lines++;
    }
    // ASCII 13 -> \r ASCII 10 -> \n
    if(c == 13 || c == 10){
      out.write(c);
      nb_lines++;
      out.write(nb_lines);
      out.write(' ');
    }else{
      out.write(c);
    }
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
