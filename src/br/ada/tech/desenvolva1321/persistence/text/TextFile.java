package br.ada.tech.desenvolva1321.persistence.text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

public class TextFile {

    protected static final String CONTENT_SEPARATOR = ";";
    protected static final String EXTENSION = ".txt";
    protected String rootFolder;

    public TextFile(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    protected Optional<Path> getFile(String fileName) {
        try {
            return Optional.of(Paths.get(rootFolder, fileName + EXTENSION));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public Path createFile(String fileName) {
        try {
            var path = Paths.get(rootFolder, fileName + EXTENSION);
            if (!Files.exists(path)) {
                Files.createDirectories(Paths.get(rootFolder));
                path = Files.createFile(path);
            }
            return path;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    protected Stream<Path> filesInRootFolder() {
        try {
            return Files.list(Paths.get(rootFolder));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void write(Path file, String content) {
        try {
            Files.writeString(file, content, StandardOpenOption.WRITE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String readContent(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
