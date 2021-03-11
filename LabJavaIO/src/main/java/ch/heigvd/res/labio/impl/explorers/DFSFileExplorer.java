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
    // get the list of all files and directories present in Root Directory
    File[] listOfFilesAndDirectory = rootDirectory.listFiles();
    if(listOfFilesAndDirectory != null){
      Arrays.sort(listOfFilesAndDirectory);
    }
    visitor.visit(rootDirectory);
    // Make sure rootDirectory is a directory
    if (listOfFilesAndDirectory != null)
    {
      // Files first, dir after
      for (File file : listOfFilesAndDirectory)
      {
        if (file.isDirectory()) {
          explore(file, visitor);
        }else{
          visitor.visit(file);
        }
      }
    }
  }

}
