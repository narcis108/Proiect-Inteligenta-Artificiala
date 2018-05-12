package main.java.transformer;

import java.util.ArrayList;

public class StringUtilsTransformer {
    private ArrayList<String> properties = new ArrayList<>();

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void addIfNotInPropertiesList(String prop) {
        if (!properties.contains(prop)) {
            properties.add(prop);
        }
    }

    public void clearProperties() {
        properties.clear();
    }

    public String getArrayListAsString(ArrayList<String> list) {
        StringBuilder result = new StringBuilder();
        for (String currentElement : list) {
            result.append(" ");
            result.append(currentElement);
        }
        return result.toString();
    }


}
