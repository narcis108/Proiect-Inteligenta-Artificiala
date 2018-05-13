package main.java.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jess.JessException;
import main.java.integration.jess.JessAdapter;
import main.java.model.Caracter;
import main.java.parser.CsvParser;
import main.java.parser.GenericParser;
import main.java.transformer.ImageChanger;
import main.java.transformer.IntrebareTransformer;
import main.java.transformer.StringUtilsTransformer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static final String CSV_PATH = "C:\\Users\\Narcis\\Desktop\\ProiectInteligentaArtificialaNaruto\\src\\main\\resources\\bc.csv";

    private JessAdapter adapter;
    private Field currentField;

    private File readFile = new File(CSV_PATH);
    private GenericParser parser = new CsvParser(readFile);
    private List<Caracter> listaCaractere = parser.parse();
    private IntrebareTransformer listaIntrebari = new IntrebareTransformer();
    private StringUtilsTransformer transformer = new StringUtilsTransformer();
    private ImageChanger imageChanger = new ImageChanger();
    private int index = 1;

    @FXML
    private ChoiceBox<String> caracteristica;
    @FXML
    private Button nextButton;
    @FXML
    private Label intrebareLabel;

    @FXML
    private VBox introducere;

    @FXML
    private AnchorPane parent;

    @FXML
    private TableView<String> tabelRezultate;

    @FXML
    private Label rezultateLabel;


    public MainController() throws JessException {
        adapter = new JessAdapter();
    }

    @FXML
    private void initialize() throws JessException, IllegalAccessException {
        startJoc();
    }

    @FXML
    private void startJoc() throws JessException, IllegalAccessException {
        loadChoiceBoxValues();
        introducere.setVisible(false);
        nextButton.setVisible(true);
        caracteristica.setVisible(true);
        tabelRezultate.setVisible(false);
    }

    @FXML
    public void loadChoiceBoxValues() throws IllegalAccessException, JessException {
        if (index > 10) {
            afiseazaRaspuns();
            nextButton.setDisable(true);
            return;
        }

        intrebareLabel.setText(listaIntrebari.getListaIntrebari().get(index));

        Caracter caracter = new Caracter();
        currentField = caracter.getClass().getDeclaredFields()[index++];
        currentField.setAccessible(true);

        caracteristica.getItems().clear();

        for (Caracter caracterIterator : listaCaractere) {
            String proprietateCurenta = (String) currentField.get(caracterIterator);
            transformer.addIfNotInPropertiesList(proprietateCurenta);
        }

        caracteristica.setItems(FXCollections.observableArrayList(transformer.getProperties()));
        caracteristica.getItems().add("Nu conteaza");
        caracteristica.getSelectionModel().selectFirst();
    }

    public void changeQuestion() throws JessException, IllegalAccessException {
        imageChanger.changeImage(index, parent);
        if (caracteristica.getValue() == "Nu conteaza") {
            adapter.adaugaRegulaNoua(currentField.getName(),
                    caracteristica.getValue().toString(),
                    transformer.getArrayListAsString(transformer.getProperties()));
        } else {
            adapter.adaugaRegulaNoua(currentField.getName(),
                    caracteristica.getValue().toString(),
                    null);
        }
        transformer.clearProperties();
        loadChoiceBoxValues();
    }

    private void afiseazaRaspuns() throws JessException {
        initializeazaColoaneTabel();
        ArrayList<String> listaRezultate = adapter.adaugaRegulaAfisare();
    }


    private void initializeazaColoaneTabel() {
        Caracter caracter = new Caracter();
        caracter.setNume("Itachi");
        Class<?> clasaCaracter = caracter.getClass();
        for (Field field : clasaCaracter.getDeclaredFields()) {
            field.setAccessible(true);
            TableColumn tableColumn = new TableColumn(field.getName().toUpperCase());
            tableColumn.setCellValueFactory(new PropertyValueFactory(field.getName()));
            tabelRezultate.getColumns().add(tableColumn);
        }
    }
}

