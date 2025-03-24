package br.ada.tech.desenvolva1321;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        File filesExamplesIml = new File("./files-examples.iml");

        var content = Files.readString(
                Paths.get("./files-examples.iml")
        );
        System.out.println(content);

        var file = Files.createFile(Paths.get("./aula.txt"));
        Files.writeString(file, "A aula de hoje foi nota 10");
    }

}
