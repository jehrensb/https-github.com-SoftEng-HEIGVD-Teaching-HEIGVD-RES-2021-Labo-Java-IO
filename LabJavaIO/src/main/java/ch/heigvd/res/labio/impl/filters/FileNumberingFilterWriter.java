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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int nbLines = 0;
    private int last;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    private void addLine() throws IOException {
        String head = ++nbLines + "\t";
        super.write(head, 0, head.length());
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        if (str.isEmpty())
            return;

        if (nbLines == 0)
            addLine();

        // While there are several lines
        String[] result = Utils.getNextLine(str.substring(off, off + len));
        while (!result[0].isEmpty()) {
            super.write(result[0], 0, result[0].length());
            addLine();
            result = Utils.getNextLine(result[1]);
        }

        // Add last line
        super.write(result[1], 0, result[1].length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        if (nbLines == 0)
            addLine();

        super.write(c);

        if(last == Utils.EOL_MACOS9.charAt(0) || c == Utils.EOL_UNIX.charAt(0))
            addLine();

        last = c;
    }

}
