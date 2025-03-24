package br.ada.tech.desenvolva1321;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        var content = Files.readString(
                Paths.get("./files-examples.iml")
        );
//        System.out.println(content);

        var file = Paths.get("./aula.txt");
        Files.writeString(file, "A aula de hoje foi nota 10");

        var rootDir = Paths.get(".");
        list(rootDir);
    }

    private static void list(Path path) throws IOException {
        var fileName = path.getFileName().toString();
        if (fileName.endsWith(".java")) {
            System.out.println(path);
            var content = Files.readString(path);
            System.out.println(content);
        }
        if (Files.isDirectory(path)) {
            try {
                Files.list(path)
                        .forEach(file -> {
                            try {
                                list(file);
                            }catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
