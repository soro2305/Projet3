package com.joueur;

public class Ordi extends Player { //Méthode qui génère un code aléatoire String
    public String generCodeString(int min, int max) {
        int stockReponse = genere.nextInt((max - min) + 1) + min; //Classe Random utiliser pour définir les bornes max min de la combinaison
        String newCode = Integer.toString(stockReponse); //Conversion de la combi en string
        return newCode; //Retour combinaison String
    }

    //Génére news unit int
    public int generCodeInt(int min, int max) {
        int newsUnit = genere.nextInt((max - min) + 1) + min; //Classe Random utiliser pour définir les bornes max min de la combinaison
        return newsUnit; //Retour combinaison
    }

    //Méthode qui permet à l'utilisateur d'entré une combinaison
    public String defineCodeUser() {
        System.out.println("Veuillez définir votre combinaison");
        return saisieUser.nextLine();// Retourne saisie du Scanner
    }

    public String []newCodeOrdi(String codeUser, String codeOrdi1, String min1 , String max1 ) {
     //   System.out.println("\n( Arriver Methode ) \n code max : "+max1+ " \n code min : "+min1+ "\n code ordi precedant : "+codeOrdi1);

        //Initialisation Objets variables
        Player playerObj = new Player();
        int unitPlusHaut = 0;
        int unitPlusBas = 0;
        int unitEgale;
        int nombreUnit = 4;
        //Array pr stocker current en int et combi final en String
        int[] previousMin = new int[4];//Stocke ancien code Min
        int[] previousMax = new int[4];//Stocke ancien code Max
        int[] stockCode = new int[nombreUnit];//Stocke news code int


        String [] retour = new String[3];
        String newCode[] = new String[stockCode.length];//Stocke news code int
        String newMax[] = new String[stockCode.length];
        String newMin[] = new String[stockCode.length];

        //Reponse final format String
        String newCodeString = "";
        String  newCodeMax = "";
        String newCodeMin  = "";
        //Deux boucles pour séparés caractères des code Ordi et User
        for (int i = 0; i < codeOrdi1.length(); i++) {
            for (int i2 = 0; i < codeUser.length(); i++) {
                for (int i3 = 0; i < min1.length(); i++) {
                    for (int i4 = 0; i < max1.length(); i++) {
//                        //Stockage de ses caractères dans des var int
                        int unitCodeMin = Character.getNumericValue(min1.charAt(i));
                        int unitCodeMax = Character.getNumericValue(max1.charAt(i));
                        int unitCodeUser = Character.getNumericValue(codeUser.charAt(i));
                        int unitCodeOrdi = Character.getNumericValue(codeOrdi1.charAt(i));
                        //Conditions pour comparer si les futur unités combi Ordi doivent êtres "-+="
                        if (unitCodeUser < unitCodeOrdi) {
                            //Méthode random génére news units.Borne max ordi -1 pr évité les doublons
                            unitPlusBas = playerObj.generCodeInt((unitCodeMin+1), (unitCodeOrdi-1));
                          //  System.out.println("Bornes generateurs "+unitCodeMin + ", "+ unitCodeOrdi+ " ==== "+ unitPlusBas);
                            stockCode[i] = unitPlusBas;//Stocke news code
                            previousMax[i] = unitCodeOrdi;
                            previousMin[i] = unitCodeMin;
                            //  System.out.println(previousMin[i]);

                        }
                        //Boucle génére nouvelle unité tant que plus basse que précédente
                        if (unitCodeUser > unitCodeOrdi) {
                            //Méthode random génére news units.Borne min ordi +1 pr évité les doublons
                            unitPlusHaut = playerObj.generCodeInt((unitCodeOrdi+1), (unitCodeMax-1));
                         //   System.out.println("Bornes generateurs "+unitCodeOrdi + ", "+ unitCodeMax+" ==== " +unitPlusHaut);

                            stockCode[i] = unitPlusHaut;
                            previousMin[i] = unitCodeOrdi;

                            previousMax[i] = unitCodeMax;            //        System.out.println(previousMin[i]);

                        }
                        //Génére unité égales
                        if (unitCodeUser == unitCodeOrdi) {
                            //  previousCode[i] = unitCodeOrdi;
                          //  unitEgale = playerObj.generCodeInt(unitCodeOrdi, unitCodeOrdi);

                            stockCode[i] = unitCodeOrdi;
                          previousMax[i]= unitCodeOrdi;
                          previousMin[i]= unitCodeOrdi;
                          //  System.out.println("Bornes generateurs "+unitCodeOrdi + ", "+ unitCodeOrdi+" ==== " +unitCodeOrdi);

                        }

                   }}
            }
        }

        //   Transforme int array en string array
        for (int j = 0; j < stockCode.length; j++) {
            newCode[j] = String.valueOf(stockCode[j]);
            newMax[j] = String.valueOf(previousMax[j]);
            newMin[j] = String.valueOf(previousMin[j]);

        }
        //Transforme array String en String
        newCodeString = Player.ArrayToString(newCode);
          newCodeMax = Player.ArrayToString(newMax);
         newCodeMin  = Player.ArrayToString(newMin);
         retour [0] = newCodeString;
         retour [1] = newCodeMax;
         retour [2] = newCodeMin;
      //  System.out.println("\n  ( Fin Methode ) \n code max : "+newCodeMax+ " \n code min : "+newCodeMin+"\n new code ordi : "+newCodeString);
        //retour nouvelle combinaison en String
        return retour;
    }

    public String new1(String codeTempo, String codeOrdi1, String codeUser) {
        //Initialisation Objets variables
        Player playerObj = new Player();
        int unitPlusHaut = 0;
        int unitPlusBas = 0;
        int nombreUnit = 4;
        //Array pr stocker current en int et combi final en String
        int[] previousCode = new int[nombreUnit];//Stocke ancien code int
        int[] stockCode = new int[nombreUnit];//Stocke news code int
        String newCode[] = new String[stockCode.length];//Stocke news code int
        //Reponse final format String
        String newCodeString = "";
        //Deux boucles pour séparés caractères des code Ordi et User
        for (int i = 0; i < codeTempo.length(); i++) {
            for (int i2 = 0; i < codeOrdi1.length(); i++) {
                for (int i3 = 0; i < codeUser.length(); i++) {

                    //Stockage de ses caractères dans des var int
                    int unitCodeOrdi = Character.getNumericValue(codeOrdi1.charAt(i));
                    int unitCodeTempo = Character.getNumericValue(codeTempo.charAt(i));
                    int unitCodeUser = Character.getNumericValue(codeTempo.charAt(i));

                    //Conditions pour comparer si les futur unités combi Ordi doivent êtres "-+="
                    if (unitCodeUser < unitCodeOrdi) {
                        //Méthode random génére news units.Borne max ordi -1 pr évité les doublons
                        unitPlusBas = playerObj.generCodeInt(0, (unitCodeOrdi - 1));
                        if (unitPlusBas < unitCodeTempo) unitPlusBas = playerObj.generCodeInt(0, (unitPlusBas - 1));

                        stockCode[i] = unitPlusBas;//Stocke news code
                    }
                    if (unitCodeUser > unitCodeOrdi) {
                        //Méthode random génére news units.Borne min ordi +1 pr évité les doublons
                        unitPlusHaut = playerObj.generCodeInt((unitCodeOrdi + 1), 9);
                        if (unitPlusHaut > unitCodeTempo)
                            unitPlusHaut = playerObj.generCodeInt((unitPlusHaut + 1), unitCodeUser);


                        stockCode[i] = unitPlusHaut;
                    }
                    //Génére unité égales
                    if (unitCodeTempo == unitCodeOrdi) {
                        stockCode[i] = playerObj.generCodeInt(unitCodeTempo, unitCodeTempo);
                    }
                }
            }
        }
        //   Transforme int array en string array
        for (int j = 0; j < stockCode.length; j++) {
            newCode[j] = String.valueOf(stockCode[j]);
        }
        //Transforme array String en String
        newCodeString = Player.ArrayToString(newCode);
        //retour nouvelle combinaison en String
        return newCodeString;
    }

    //Méthode qui converti un Array de String en String
    public static String ArrayToString(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]);
        }
        return stringBuilder.toString();
    }
}