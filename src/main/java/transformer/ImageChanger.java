package main.java.transformer;

import javafx.scene.layout.AnchorPane;

import java.util.Arrays;
import java.util.List;

public class ImageChanger {

    private List<String> categorie = Arrays.asList("", "rank", "clan", "biju", "ochi", "sex", "debut",
            "element-dominant", "abilitate-inascuta", "traieste",
            "traieste");

    public void changeImage(int index, AnchorPane parent)

    {
        parent.getStylesheets().clear();
        parent.getStylesheets().add(getCssFilePath(index - 1));
    }

    private String getCssFilePath(int index) {
        return String.format("main/resources/css/%s.css", categorie.get(index));
    }
}
