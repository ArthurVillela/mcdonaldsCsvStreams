package br.com.letscode.java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Aplicacao {

    private List<MenuItem> menuItems;
    public static void main(String[] args) {
        Aplicacao app = new Aplicacao();
        app.testeLeituraDeArquivoCsv();
        //hamburguer com mais caloria
        System.out.println("hamburguer com mais caloria: ");
        app.findHamburguerMaisCalorico("Beef & Pork");
        app.findHamburguerMaisCalorico("Chicken & Fish");

    }

    private void findHamburguerMaisCalorico(String category) {
        this.menuItems
                .stream()
                .filter(item -> item.getCategory().equals(category))
                .filter(item -> !item.getName().contains("piece"))
                .max(Comparator.comparingInt(MenuItem::getCalories))
                .ifPresent(System.out::println);
    }

    private void testeLeituraDeArquivoCsv(){
        String filePath = getFilePathFromResourceAsStream("mcdonalds-menu.csv");
        try(Stream<String> lines = Files.lines(Path.of(filePath))){
            this.menuItems = lines
                    .skip(1)
                    .map(MenuItem::fromLine)
                    .toList();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getFilePathFromResourceAsStream(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getFile());
        return file.getPath();
    }

}
