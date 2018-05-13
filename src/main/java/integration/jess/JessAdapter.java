package main.java.integration.jess;

import jess.Fact;
import jess.JessException;
import jess.Rete;

import java.util.ArrayList;
import java.util.Iterator;

public class JessAdapter {
    private static final String INITIAL_FACTS_FILE_PATH = "main/resources/initial-facts.clp";
    private static final String GLOBAL_VARIABLES_FILE_PATH = "main/resources/global-variables.clp";
    private static final String REGULA_AFISARE_FILE_PATH = "main/resources/regula-afisare.clp";
    private static final Integer RESULT_FACT_INDEX = 67;

    private Rete engine;

    public JessAdapter() throws JessException {
        engine = new Rete();
        incarcaFapteInitiale();
        defineGlobalVariables();
    }

    private void defineGlobalVariables() throws JessException {
        engine.batch(GLOBAL_VARIABLES_FILE_PATH);
        System.out.println("Am incarcat variabilele globale!");
    }

    private void incarcaFapteInitiale() throws JessException {
        engine.batch(INITIAL_FACTS_FILE_PATH);
        engine.reset();
        engine.eval("(facts)");
        System.out.println("Faptele initiale au fost incarcate cu succes!");
    }

    public void adaugaRegulaNoua(String numeRegula, String value, String allValues) throws JessException {
        String pattern = String.format("( defrule %s\n" +
                        "=>\n" +
                        " (bind ?*%s* (create$ %s))\n" +
                        ")",
                numeRegula, numeRegula, allValues != null ? allValues : value);
        engine.eval(pattern);
        System.out.println(pattern);
        engine.run();
    }

    public ArrayList<String> adaugaRegulaAfisare() throws JessException {
        engine.batch(REGULA_AFISARE_FILE_PATH);
        engine.run();
        return getListaRezultate();
    }

    private ArrayList<String> getListaRezultate() throws JessException {
        ArrayList<String> listaRezultate = new ArrayList<>();
        int factIndex = 0;
        boolean found = false;
        Iterator iterator = engine.listFacts();
        while (iterator.hasNext()) {
            iterator.next();
            factIndex++;
            if (factIndex >= RESULT_FACT_INDEX) {
                found = true;
                Fact rezultat = engine.findFactByID(factIndex - 1);
                listaRezultate.add(rezultat.toString().
                        substring(16, rezultat.toString().lastIndexOf(')')));
                System.out.println(rezultat.toString().
                        substring(16, rezultat.toString().lastIndexOf(')')));
            }
        }
        if (!found) {
            System.out.println("N-am gasit nimic, bossule!");
        }
        return listaRezultate;
    }
}
