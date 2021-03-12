package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Locale;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    super.write(str.toUpperCase(Locale.ROOT),off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
