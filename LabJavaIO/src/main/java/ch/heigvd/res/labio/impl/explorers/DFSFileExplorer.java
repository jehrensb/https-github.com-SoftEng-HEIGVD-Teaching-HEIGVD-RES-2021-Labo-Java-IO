package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    private static final Logger LOG = Logger.getLogger(DFSFileExplorer.class.getName());

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        if (rootDirectory == null || visitor == null) {
            throw new IllegalArgumentException("rootDirectory or visitor is null");
        }
        else {
            visitor.visit(rootDirectory);
            try {
                Files.list(Paths.get(rootDirectory.getPath()))
                        .sorted(Comparator.comparing(Path::getFileName))
                        .map(Path::toFile)
                        .forEach(file -> {
                            if (file.isDirectory())
                                explore(file, visitor);
                            else
                                visitor.visit(file);
                        });
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.getMessage());
            }
        }
    }

}
