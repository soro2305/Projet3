package com.joueur;

import com.menu.Parametres;
import org.apache.log4j.Logger;

//Classe User regroupe les fonctionnalités propres à l'Ordi, générateur de combinaison, conversion, comparaison
public class Ordi extends Player {
    //Initialisation du logger pour player
    private static Logger logger = Logger.getLogger(Ordi.class);
    //Création d'objets settings pr importé les paramètres utiles
    Parametres settingsObj = new Parametres();

    //Méthode qui génère un code aléatoire String
    public String generCodeString(String min, String max) {
        int minI = Integer.parseInt(min);
        int maxI = Integer.parseInt(max);
        int stockReponse = genere.nextInt((maxI - minI) + 1) + minI; //Classe Random utiliser pour définir les bornes max min de la combinaison
        String newCode = Integer.toString(stockReponse); //Conversion de la combi en string
        return newCode; //Retour combinaison String
    }

    // Génère nouvelles unités int
    public int generCodeInt(int min, int max) {
        int newsUnit = genere.nextInt((max - min) + 1) + min; //Classe Random utiliser pour définir les bornes max min de la combinaison
        return newsUnit; //Retour combinaison
    }

    public String[] newCodeOrdi(String codeUser, String codeOrdi1, String min1, String max1) {
        //Initialisation variables
        int unitPlusHaut;
        int unitPlusBas;
        int nombreUnit = settingsObj.getNombreUnit();//Définit la taille des combinaisons selon les paramètres
        //Array pr stocker current en int et combi final en String
        int[] previousMin = new int[nombreUnit];//Stocke ancien code Min int
        int[] previousMax = new int[nombreUnit];//Stocke ancien code Max int
        int[] stockCode = new int[nombreUnit];//Stocke news code int
        //Array pr stocker les réponses ( range min max + news combi ) en String
        String newCode[] = new String[nombreUnit];//Stocke news code int
        String newMax[] = new String[nombreUnit];//Stocke new code Max en String
        String newMin[] = new String[nombreUnit];//Stocke new code Min en String
        //Reponse final format String
        String newCodeString = "";
        String newCodeMax = "";
        String newCodeMin = "";
        //Array pr retourné les réponses : range ,min, max + news combi
        String[] retour = new String[3];
        //Boucles pour séparés caractères des codes Ordi,User,Min,Max
        for (int i = 0; i < codeOrdi1.length(); i++) {
            for (int i2 = 0; i < codeUser.length(); i++) {
                for (int i3 = 0; i < min1.length(); i++) {
                    for (int i4 = 0; i < max1.length(); i++) {
//                       //Conversion et stockage de ses caractères dans des var int
                        int unitCodeMin = Character.getNumericValue(min1.charAt(i));
                        int unitCodeMax = Character.getNumericValue(max1.charAt(i));
                        int unitCodeUser = Character.getNumericValue(codeUser.charAt(i));
                        int unitCodeOrdi = Character.getNumericValue(codeOrdi1.charAt(i));
                        //Conditions pour comparer si les futur unités combi Ordi doivent êtres "-+="
                        if (unitCodeUser < unitCodeOrdi) {
                            //Méthode random génére news units.
                            unitPlusBas = generCodeInt((unitCodeMin + 1), (unitCodeOrdi - 1));//Borne min dernière min+1, borne max ordi-1 pr évité les doublons
                            stockCode[i] = unitPlusBas;//Stocke unit new code
                            previousMax[i] = unitCodeOrdi;//Stocke news unit Max
                            previousMin[i] = unitCodeMin;//Stocke news unit Min
                        }
                        //Boucle génére nouvelle unité tant que plus basse que précédente
                        if (unitCodeUser > unitCodeOrdi) {
                            //Méthode random génére news units.
                            unitPlusHaut = generCodeInt((unitCodeOrdi + 1), (unitCodeMax - 1));//Borne min ordi+1, borne max dernière max-1 pr évité les doublons
                            stockCode[i] = unitPlusHaut;//Stocke unit new code
                            previousMin[i] = unitCodeOrdi;//Stocke news unit Min
                            previousMax[i] = unitCodeMax;//Stocke news unit Max
                        }
                        //Génére unité égales
                        if (unitCodeUser == unitCodeOrdi) {
                            stockCode[i] = unitCodeOrdi;
                            previousMax[i] = unitCodeOrdi;
                            previousMin[i] = unitCodeOrdi;
                        }

                    }
                }
            }
        }

        //   Transforme int arrays en string arrays
        for (int j = 0; j < stockCode.length; j++) {
            newCode[j] = String.valueOf(stockCode[j]);
            newMax[j] = String.valueOf(previousMax[j]);
            newMin[j] = String.valueOf(previousMin[j]);

        }
        //Transforme arrays String en String simple
        newCodeString = ArrayToString(newCode);
        newCodeMax = ArrayToString(newMax);
        newCodeMin = ArrayToString(newMin);
        //Attributions contenu retour ->new combi + range max + range min
        retour[0] = newCodeString;
        retour[1] = newCodeMax;
        retour[2] = newCodeMin;
        //retour réponses en String array
        return retour;
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