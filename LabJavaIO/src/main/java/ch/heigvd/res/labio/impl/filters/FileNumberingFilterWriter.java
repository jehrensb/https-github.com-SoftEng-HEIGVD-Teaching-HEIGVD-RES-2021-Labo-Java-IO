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
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNB = 0;
  private int lastChar;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  private void addLine() throws IOException{
    out.write(++lineNB + "\t");
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if (lineNB == 0){
      addLine();
    }
    if(lastChar == '\r' && c != '\n'){ // Vérifie que ce n'est pas un \r\n
      addLine();
    }

    out.write(c);
    lastChar = c;
    if(lastChar == '\n'){
      addLine();
    }
    // Attention ! Si le tout dernier char envoyé est un \r, on devrait ajouter un numéro de ligne
    // Je n'ai pas trouvé comment résoudre ce problème, il faudrait savoir que c'est la fin du texte...
  }

}
