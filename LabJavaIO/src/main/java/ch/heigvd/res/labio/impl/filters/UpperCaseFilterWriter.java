package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * filtre pour mettre les lettres en capital
 * @author Olivier Liechti
 * @author Corentin Zeller
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      String bufferAsString = new String(cbuf);
      super.write(bufferAsString.toUpperCase(), off, len);
  }

  @Override
  public void write(int c) throws IOException {
      super.write(Character.toUpperCase(c));
  }

}
