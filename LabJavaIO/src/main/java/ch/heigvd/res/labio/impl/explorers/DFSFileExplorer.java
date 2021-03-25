package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    if(rootDirectory == null){
      return;
    }

    visitor.visit(rootDirectory); // On visite le dossier en cours
    // Pour chaque element du dossier
    if (rootDirectory.listFiles() != null) {
      File[] listOfChildren = rootDirectory.listFiles();
      Arrays.sort(listOfChildren);
      for (File f : listOfChildren) {
        if (f.isDirectory()) {
          // Si c'est un dossier, on l'explore
          explore(f, visitor);
        } else {
          // Sinon c'est un fichier -> on le visite
          visitor.visit(f);
        }
      }
    }
  }

}
