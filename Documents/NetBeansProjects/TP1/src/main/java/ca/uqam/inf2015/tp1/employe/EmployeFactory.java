package ca.uqam.inf2015.tp1.employe;

import ca.uqam.inf2015.tp1.application.AppConfig;
import ca.uqam.inf2015.tp1.exceptions.MissingDataInJSONFileException;
import ca.uqam.inf2015.tp1.feuilleDeTemps.FeuilleDeTemps;
import ca.uqam.inf2015.tp1.feuilleDeTemps.ProjectFactory;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONException;

public abstract class EmployeFactory {
    private static JSONObject elementDeRacine;
    
   
    
    
    
    public static List<Employe> construireEmployeAPartirDeFichierJson(String filePath)
                                                        throws MissingDataInJSONFileException, IOException {
        List<Employe> employes;
        initialisation(filePath);
        employes = analyserFichierJson();

        return employes;
    }

    private static void initialisation(String filePath) throws FileNotFoundException, IOException, 
                                                               MissingDataInJSONFileException {
        try {
            elementDeRacine = JSONObject.fromObject(IOUtils.toString(new FileInputStream(filePath),
                    StandardCharsets.UTF_8));
        } catch (JSONException JSONe) {
            throw new MissingDataInJSONFileException();
        }
    }

    private static List<Employe> analyserFichierJson() throws IOException, MissingDataInJSONFileException {
        List<Employe> employes = new ArrayList<>();

        employes.add(
            construireEmploye(elementDeRacine.getInt(AppConfig.getParametreRetournerUnString("CLE_NO_EMPLOYE"))));

        return employes;
    }

    private static Employe construireEmploye(int noEmploye) throws MissingDataInJSONFileException, IOException {
        Employe        employe   = new Employe(noEmploye);
        FeuilleDeTemps timeSheet = new FeuilleDeTemps();

        timeSheet.setJours(ProjectFactory.construireProjetAPartirDeFichierJson(elementDeRacine));
        employe.setFeuilleDeTemps(timeSheet);

        return employe;
    }
}


