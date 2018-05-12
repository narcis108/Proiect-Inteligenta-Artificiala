package main.java.parser;

import main.java.model.GenericModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser implements GenericParser {

    private File parsedFile;

    public CsvParser(File parsedFile) {
        this.parsedFile = parsedFile;
    }

    @Override
    public List<GenericModel> parse() {
        List<GenericModel> caractere = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(parsedFile));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                GenericModel genericModel = new GenericModel();
                ArrayList<String> caracteristici = getLineAsArrayList(line);
                setFields(genericModel, caracteristici);
                caractere.add(genericModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caractere;
    }


    @SuppressWarnings("ThrowablePrintedToSystemOut")
    private void setFields(GenericModel genericModel, ArrayList<String> caracteristici) {
        try {
            Class<?> clasa = genericModel.getClass();
            int index = 0;
            for (Field field : clasa.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(genericModel, caracteristici.get(index++));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private ArrayList<String> getLineAsArrayList(String line) {
        return new ArrayList<>(Arrays.asList(line.split(",")));
    }
}
