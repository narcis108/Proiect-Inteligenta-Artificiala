package main.java.transformer;

import main.java.properties.IntrebareConstants;

import java.util.ArrayList;

public class IntrebareTransformer {
    private ArrayList<String> listaIntrebari = new ArrayList<>();

    public IntrebareTransformer() {
        listaIntrebari.add("");
        listaIntrebari.add(IntrebareConstants.SAT);
        listaIntrebari.add(IntrebareConstants.RANK);
        listaIntrebari.add(IntrebareConstants.CLAN);
        listaIntrebari.add(IntrebareConstants.BESTIE);
        listaIntrebari.add(IntrebareConstants.TIP_OCHI);
        listaIntrebari.add(IntrebareConstants.SEX);
        listaIntrebari.add(IntrebareConstants.DEBUT);
        listaIntrebari.add(IntrebareConstants.ELEMENT_DOMINANT);
        listaIntrebari.add(IntrebareConstants.ABILITATE_INASCUTA);
        listaIntrebari.add(IntrebareConstants.TRAIESTE);
    }

    public ArrayList<String> getListaIntrebari() {
        return listaIntrebari;
    }
}
