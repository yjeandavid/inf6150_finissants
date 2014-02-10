package ca.uqam.inf2015.tp1.employe;

import ca.uqam.inf2015.tp1.application.AppConfig;
import ca.uqam.inf2015.tp1.feuilleDeTemps.FeuilleDeTemps;

public class Employe {
	
//	protected int noEmploye;
//    protected int minHeureDeBureauParJour;
//    protected int minHeureDeBureauParSemaine;
//    protected int heureDeBureauParSemaine;
//    protected int heureTeleTravailParSemaine;
//    protected int plafondHeureBureauSemaine;
//    protected FeuilleDeTemps[] feuillesDeTemps;
//    protected String message;
//    
//    public abstract void traiterFeuillesDeTemps();
//    
//    public String getMessage()
//    {
//        return message.substring(0, (message.length()-3));
//    }
	
	private char typeEmploye;
	private int noEmploye;
	private FeuilleDeTemps timeSheet = null;
	private double heuresDeBureauParSemaine = 0.0;
        private double heuresTeleTravailParSemaine = 0.0;
        
	public Employe(int noEmploye)
	{
		this.noEmploye = noEmploye;
		setTypeEmploye();
	}
	
        public String validerFeuilleDeTemps()
        {
            String messageValidation = "";
            
            switch (typeEmploye)
            {
                case AppConfig.EMPLOYE_ADMINISTRATION:
                                messageValidation = validerSemaineTypeAdmin();
                                break;
                case AppConfig.EMPLOYE_NORMAL:
                                messageValidation = validerSemaineTypeNormal();
                                break;
            }
            if (!messageValidation.isEmpty())
            {
                messageValidation = messageValidation
                                    .substring(0, messageValidation.length()-1);
            }
            return messageValidation;
        }
        
        public String validerSemaineTypeAdmin()
        {
            String messageValidation = "";
            
            messageValidation += validerJoursOuvrablesAdmin() + ',';
            messageValidation += validerHeuresTravailBureauParSemaine(
                    AppConfig.MINIMUM_MINUTES_BUREAU_ADMIN_SEMAINE,
                    AppConfig.MAXIMUM_MINUTES_BUREAU_EMPLOYE_SEMAINE) + ',';
            messageValidation += validerHeuresTeleTravailParSemaine(
                    AppConfig.MAXIMUM_MINUTES_TELE_TRAVAIL_ADMIN_SEMAINE) + ',';
            
            return messageValidation;
        }
        
        public String validerSemaineTypeNormal()
        {
            String messageValidation = "";
            
            messageValidation += validerJoursOuvrablesNormal() + ',';
            messageValidation += validerHeuresTravailBureauParSemaine(
                    AppConfig.MINIMUM_MINUTES_BUREAU_NORMAL_SEMAINE,
                    AppConfig.MAXIMUM_MINUTES_BUREAU_EMPLOYE_SEMAINE) + ',';
            
            return messageValidation;
        }
        
        public String validerJoursOuvrablesAdmin()
        {   
            return timeSheet.validerJoursOuvrables(
                    AppConfig.MINIMUM_MINUTES_BUREAU_ADMIN_PAR_JOUR);
        }
        
        public String validerJoursOuvrablesNormal()
        {
            return timeSheet.validerJoursOuvrables(
                    AppConfig.MINIMUM_MINUTES_BUREAU_NORMAL_PAR_JOUR);
        }
        
        public String validerHeuresTravailBureauParSemaine(
                int minimumHeuresDeBureauParSemaine,
                int maximumHeuresDeBureauParSemaine)
        {
            String messageValidation = "";
            
            if (heuresDeBureauParSemaine < minimumHeuresDeBureauParSemaine)
            {
                messageValidation += AppConfig.MSG_HEURES_MINIMUM_SEMAINE_BUREAU
                                        + ',';
            } else if (heuresDeBureauParSemaine >
                    maximumHeuresDeBureauParSemaine)
            {
                messageValidation += AppConfig.MSG_HEURES_MAXIMAL_BUREAU + ',';
            }
            
            return messageValidation;
        }
        
        public String validerHeuresTeleTravailParSemaine(
                int maximumHeuresTeleTravailParSemaine)
        {
            String messageValidation = "";
            
            if (heuresTeleTravailParSemaine > 
                                            maximumHeuresTeleTravailParSemaine)
            {
                messageValidation += AppConfig.MSG_HEURES_MAXIMAL_TELE_TRAVAIL
                                        + ',';
            }
            
            return messageValidation;
        }
        
	public int getNoEmplye()
	{
		return noEmploye;
	}
	
	public void setNoEmploye(int noEmploye)
	{
		this.noEmploye = noEmploye;
	}
	
	public FeuilleDeTemps getFeuilleDeTemps()
	{
		return timeSheet;
	}
	
	public void setfFeuilleDeTemps(FeuilleDeTemps timeSheet)
	{
		this.timeSheet = timeSheet;
	}
        
        public void setHeuresDeBureauParSemaine()
        {
            heuresDeBureauParSemaine = timeSheet
                                            .calculerHeuresBureauParSemaine();
        }
        
        public void setHeuresDeTeleTravailParSemaine()
        {
            heuresTeleTravailParSemaine = timeSheet
                                         .calculerHeuresTeleTravailParSemaine();
        }
	
	public char getTypeEmploye()
	{
		return typeEmploye;
	}
	
	private void setTypeEmploye()
	{
            if (noEmploye >= AppConfig.CODE_REF_TYPE_EMPLOYE) {
                typeEmploye = AppConfig.EMPLOYE_NORMAL;
            }
            else {
                typeEmploye = AppConfig.EMPLOYE_ADMINISTRATION;
            }
	}
}