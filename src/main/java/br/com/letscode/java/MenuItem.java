package br.com.letscode.java;


import lombok.Value;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


@Value
public class MenuItem {
    String category;
    String name;
    String servingSize;
    Integer calories;
    Double totalFat;
    Integer sodium;
    Integer carbohydrates;
    Integer protein;


    public static MenuItem fromLine(String line) {
        String[] split = line.split(",(?=\\S)");
        return new MenuItem(
                split[0],
                split[1].replace("\"", ""),
                split[2],
                parseInt(split[3]),
                parseDouble(split[5]),
                parseInt(split[12]),
                parseInt(split[14]),
                parseInt(split[19])
                );
    }

}

