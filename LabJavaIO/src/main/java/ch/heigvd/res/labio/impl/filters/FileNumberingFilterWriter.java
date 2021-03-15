package ch.heigvd.res.labio.impl.filters;

import ch.heigvd.res.labio.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNr = 1;
    private boolean macLine = true;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        while (len-- > 0)
            write(str.charAt(off++));
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        while (len-- > 0)
            write(cbuf[off++]);
    }

    @Override
    public void write(int c) throws IOException {
        switch (c) {
            case '\n':
                super.write(c);
                out.write(String.format("%d\t", lineNr++));
                macLine = false;
                return;
            case '\r':
                macLine = true;
                break;
            default:
                if (macLine) {
                    out.write(lineNr++ + "\t");
                }
                macLine = false;
        }
        super.write(c);
    }

}
