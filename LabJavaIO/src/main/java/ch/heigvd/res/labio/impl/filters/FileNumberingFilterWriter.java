package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
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

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  private int countLines = 0;
  private boolean isMac = false; //Util for MAC

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    if(str != null){
      for(int i = 0; i < len; i++){
        write(str.charAt(off+i));
      }
    }

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    if(cbuf != null){
      for(int i = 0; i < len; i++){
        write(cbuf[off + i]);
      }
    }


  }

  @Override
  public void write(int c) throws IOException {

    // \n Unix
    // \r MacOs
    // \r\n Windows

    if(countLines == 0)  out.write(++countLines + "\t");

    if(isMac){
      if(c != '\n')  out.write(++countLines + "\t");
      isMac = false;
    }

    out.write(c);

    if(c == '\n') out.write(++countLines + "\t");
    if(c == '\r') isMac = true;

  }




}
