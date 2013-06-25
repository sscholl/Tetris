package controller;

import java.io.*;

/**
* Manager zum Lesen und Schreiben des ganzen CHighScores-Objektes
* based on http://www2.math.uni-wuppertal.de/~axel/skripte/oop/oop27_6.html
* 
*/

public class JHighscoreFileIO
{

	/** 
	 * Schreibt ein CHighScores-Objekt in die Datei highscores.ser
	 * @param Highscoresobjekt, welches auf die Festplatte geschrieben werden soll
	 */
    public static synchronized void toDisk(CHighScores chs)
    {
        try
        {
            new ObjectOutputStream(
                new FileOutputStream("highscores.ser")).writeObject(chs);
        }
        catch(Exception e)
        {
             System.out.println("Could not write highscore file.");
        }
    }


    /** 
	 * liest ein CHighScores-Objekt aus der Datei highscores.ser
	 * @return Highscoresobjekt, welches von der Festplatte gelesen wurde oder null
	 */
    public static synchronized CHighScores fromDisk()
    {
        CHighScores chs_new = null;
        try
        {
            chs_new = (CHighScores) new ObjectInputStream(
                          new FileInputStream("highscores.ser")).readObject();
            //chs_new.debug();
        }
        catch(Exception e)
        {
            System.out.println("No highscore file found.");
        }
        return chs_new;
    }

}