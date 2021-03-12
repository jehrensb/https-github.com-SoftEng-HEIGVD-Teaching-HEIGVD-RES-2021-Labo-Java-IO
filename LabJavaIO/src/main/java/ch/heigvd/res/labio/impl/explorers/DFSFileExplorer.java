package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
        // Visit current folder
        visitor.visit(rootDirectory);

        // Retrieve files and folders
        File[] files = rootDirectory.listFiles();

        // Make sure that there is no IOError and that the rootdirectory is a folder
        if (files != null)

            // Browse each file in alphabetical order
            Arrays.stream(rootDirectory.listFiles())
                    .sorted()
                    .forEach(file -> {
                        if (file.isDirectory())
                            explore(file, visitor); // recursively call exploration
                        else
                            visitor.visit(file);
                    });
    }
}
