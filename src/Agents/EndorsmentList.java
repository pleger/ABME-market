package Agents;

public class EndorsmentList {
    //PL: las constantes deben ser mayusculas!!
    public static final int altaSeguridad=1;
    public static final int altaVariedad=2;
    public static final int altaConfianzaVen=3;
    public static final int altaCalidad=4;
    public static final int altaVeracidad=5;
    public static final int bajaSeguridad=6;
    public static final int bajaVariedad=7;
    public static final int bajaConfianzaVen=8;
    public static final int bajaCalidad=9;
    public static final int bajaVeracidad=10;

    /*relation between numbers above and endorsments list: an element in endorsment list MUST BE
    in position number-1 eg. altaSeguridad is number 1 and "alta seguridad" is in pos 1-1=0 of list;
    * */
    private static String [] endorsments={"Alta Seguridad","Alta Variedad","Alta Confianza Vendedor",
            "Alta Calidad","Alta Veracidad","Baja Seguridad","Baja Variedad","Baja Confianza Vendedor",
            "Baja Calidad","Baja Veracidad"};

    /**
     * Receives number of an endorsment and
     * @return "name" of an endorsment
     */
    public static String getEndorsment(int endorsmentNumber){
        return  endorsments[endorsmentNumber-1];
    }
    public static int searchEndorsmentNumber(String endorsment){
        int i;
        for (i=0;i<endorsments.length;i++){
            if(endorsments[i].equals(endorsment)) break;
        }
        return i+1;
    }

}
