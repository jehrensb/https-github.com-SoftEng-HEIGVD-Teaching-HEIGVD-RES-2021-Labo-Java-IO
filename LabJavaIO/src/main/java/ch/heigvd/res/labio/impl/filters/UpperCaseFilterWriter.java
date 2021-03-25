package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import static java.lang.Character.toUpperCase;

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
    String output = str.substring(0, off) +
            str.substring(off, off + len).toUpperCase(Locale.ROOT) +
            str.substring(off + len);
    out.write(output, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++){
      cbuf[i] = toUpperCase(cbuf[i]);
    }
    out.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    out.write(toUpperCase(c));
  }

}
