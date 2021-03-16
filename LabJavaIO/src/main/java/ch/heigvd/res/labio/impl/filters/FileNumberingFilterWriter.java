package ch.heigvd.res.labio.impl.filters;

import ch.heigvd.res.labio.impl.Utils;

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
 * @author Delphine Scherler
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int lineNo = 0;
  private int lastChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  /**
   * Small method to write a line with the line number and a tab.
   */
  private void writeLine() throws IOException {
    String s = ++lineNo + "\t";
    super.write(s, 0, s.length());
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String[] strOut = Utils.getNextLine(str.substring(off, off + len));

    if(lineNo == 0){
      writeLine();
    }

    while(!strOut[0].isEmpty()){
      super.write(strOut[0], 0, strOut[0].length());
      writeLine();
      strOut = Utils.getNextLine(strOut[1]);
    }

    super.write(strOut[1], 0, strOut[1].length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < len; i++){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    if(lineNo == 0){
      writeLine();
    }

    if((lastChar == '\r' && c != '\n') || lastChar == '\n'){
      writeLine();
    }

    super.write(c);

    lastChar = c;
  }

}
