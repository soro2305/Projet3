package com.joueur;

import com.menu.Parametres;
import org.apache.log4j.Logger;


/**
 * <b>La classe Ordi représente le joueur incarner par l'ordinateur.</b>
 * <p>Le joueur Ordi peut :</p>
 * <ul>
 *    <li>Générer une combinaison a trouvé par l'utilisateur</li>
 *    <li>Proposer une solution initiale</li>
 *    <li>Proposer une solution à partir des indications donner par l'utilisateur</li>
 *    <li>Comparer sa combinaison est celle de l'utilisateur, afin de fournir des indications -+= pour guider l'adversaire</li>
 * </ul>
 *
 * @author Amet Soro
 * @version 1.0
 */
public class Ordi extends Player {
    /**
     * Création d'objets du type Paramètres.  Afin d'importer le nombre d'unités défini dans le fichier properties.
     */
    Parametres settingsObj = new Parametres();

    /**
     * Génère un code aléatoire String entre les bornes minimums et maximales définies en param.
     *
     * @param min Ce param devient la borne minimum du générateur Random de chiffre.
     * @param max Ce param devient la borne maximum du générateur Random de chiffre.
     * @return Nouveau chiffre String Random générer.
     */
    public String generCodeString(String min, String max) {

        int minI = Integer.parseInt(min);
        int maxI = Integer.parseInt(max);

        int stockReponse = genere.nextInt((maxI - minI) + 1) + minI; //Classe Random utiliser pour définir les bornes max min de la combinaison
        String newCode = Integer.toString(stockReponse); //Conversion de la combi en string
        while (newCode.length() < 4) {
            newCode = "0" + newCode;
        }
        return newCode; //Retour combinaison String

    }

    /**
     * Génère un code aléatoire int entre les bornes minimums et maximales définies en param.
     *
     * @param min Ce param devient la borne minimum du générateur Random de chiffre.
     * @param max Ce param devient la borne maximum du générateur Random de chiffre.
     * @return Nouveau chiffre int Random générer.
     */
    public int generCodeInt(int min, int max) {
        int newsUnit = genere.nextInt((max - min) + 1) + min; //Classe Random utiliser pour définir les bornes max min de la combinaison

        return newsUnit; //Retour combinaison

    }

    /**
     * Permet à l'ordi de générer une combinaison  à partir des indications de sa précédente combinaison.
     * <p>Les quatre arguments sont divisés en unités puis converties en int :</p>
     * <ul>
     *     <li>codeUser devient unitCodeUser</li>
     *     <li>codeOrdi1 devient unitCodeOrdi</li>
     *     <li>min1 devient unitCodeMin</li>
     *     <li>max1 devient unitCodeMax</li>
     *     </ul>
     *     <p>Ensuite unitcodeuser et comparés à unitcodeordi de façon a ce que si : </p>
     *     <ul>
     *         <li>unitCodeUser est plus petit que unitCodeOrdi donc le nouveau chiffre généré soit compris entre unitCodeMin et unitCodeOrdi</li>
     *         <li>unitCodeUser est plus grand que unitCodeOrdi donc le nouveau chiffre généré soit compris entre unitCodeOrdi et unitCodeMax</li>
     *         <li>unitCodeUser est égale à unitCodeOrdi donc le chiffre généré reste inchangé</li>
     *     </ul>
     *     <p>Les nouveaux minimums et maximums sont stocker.</p>
     *     <p>Les nouvelles valeurs sont converties en String puis retourner sous forme d'Array de String.</p>
     *
     * @param codeOrdi1 Combinaison générée par l'ordi.
     * @param codeUser  Combinaison générée par l'user.
     * @param min1      Précédente borne minimum.
     * @param max1      Précédente borne maximum.
     * @return Array Tableau comprenant nouvelle combinaison ordi ainsi que nouvelle borne Min et Max.
     */
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
                        int unitCodeMin = 0;
                        int unitCodeMax = 0;
                        int unitCodeUser = 0;
                        int unitCodeOrdi = 0;
                        try {
                            unitCodeMin = Character.getNumericValue(min1.charAt(i));
                            unitCodeMax = Character.getNumericValue(max1.charAt(i));
                            unitCodeUser = Character.getNumericValue(codeUser.charAt(i));
                            unitCodeOrdi = Character.getNumericValue(codeOrdi1.charAt(i));
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println("");
                            return null;
                        }
                        //Conditions pour comparer si les futur unités combi Ordi doivent êtres "-+="
                        if (unitCodeUser < unitCodeOrdi) {

                            if (unitCodeUser == 0) {
                                unitPlusBas = generCodeInt((unitCodeMin), (unitCodeOrdi));//Borne min dernière min+1, borne max ordi-1 pr évité les doublons
                            } else
                                unitPlusBas = generCodeInt((unitCodeMin + 1), (unitCodeOrdi - 1));//Borne min dernière min+1, borne max ordi-1 pr évité les doublons
                            stockCode[i] = unitPlusBas;//Stocke unit new code
                            previousMax[i] = unitCodeOrdi;//Stocke news unit Max
                            previousMin[i] = unitCodeMin;//Stocke news unit Min
                        }
                        //Boucle génére nouvelle unité tant que plus basse que précédente
                        if (unitCodeUser > unitCodeOrdi) {
                            if (unitCodeUser == 9) {
                                unitPlusHaut = generCodeInt((unitCodeOrdi), (unitCodeMax));//Borne min dernière min+1, borne max ordi-1 pr évité les doublons
                            } else
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

    /**
     * Converti un Array de String en String.
     *
     * @param strArray Array de string a convertir en string.
     * @return Nouveau String issue de l'Array de String.
     */
    public static String ArrayToString(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]);
        }

        return stringBuilder.toString();

    }

}