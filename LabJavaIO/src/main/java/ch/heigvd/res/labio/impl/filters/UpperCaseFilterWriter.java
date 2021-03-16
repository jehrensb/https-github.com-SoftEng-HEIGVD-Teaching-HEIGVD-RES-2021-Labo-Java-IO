package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Delphine Scherler
 */
public class UpperCaseFilterWriter extends FilterWriter {

  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String convert = str.toUpperCase();
    super.write(convert, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] convert = new char[cbuf.length];
    for(int i = 0; i < cbuf.length; i++) {
      convert[i] = Character.toUpperCase(cbuf[i]);
    }
    write(convert, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    int convert = Character.toUpperCase(c);
    super.write(convert);
  }

}
