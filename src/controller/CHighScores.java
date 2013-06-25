package controller;

import java.util.Vector;
import java.io.*;

/** Repräsentiert die vorhandenen Highscores
 *  @brief enthält einen Vektor mit den Einträgen 
 *
 *
 */
@SuppressWarnings("serial")
public class CHighScores implements Serializable
{
    private int                     MAXENTRIES;                              //<! ist nicht konstant, weil evtl. serialisierte Spielstände mit anderem Eintrag gelesen werden
    private int                     numentries;
    private int                     lowestscore;
    private Vector<CHighScoreEntry> myvector    = new Vector<CHighScoreEntry>();

    public Vector<CHighScoreEntry> getHighScoreVector()
    {
        return myvector;
    }

    public CHighScores()
    {
        lowestscore = 0;
        MAXENTRIES = 10;
        numentries = 0;
        enterScore("Max Mustermann" , 10);
    }
    /*
        public void debug()
        {
            System.out.println("CHighScores Debug called:");
            System.out.println(MAXENTRIES);
            System.out.println(numentries);
            System.out.println(lowestscore);
        }
    */
    /**
     * gibt die kleinste Punktzahl zurück, die für einen Highscoreeintrag qualifiziert
     * @return kleinste Punktzahl
     */
    
    public int getLowestScore()
    {
        return lowestscore;
    }

    /** Trägt Datensatz in die Highscoreliste ein, falls die Daten eine Platzierung ergeben 
     * 
     * @param s         Name des Spielers oder der Spielerin
     * @param newscore  Punktzahl
     */
    public void enterScore(String s, int newscore)
    {
        if((newscore >= lowestscore) && (s != null))
        {
            int idx = 0;
            while(idx < numentries)
            {
                if(myvector.elementAt(idx).getScore() <= newscore)
                    break;
                idx++;
            }
            //System.out.println("idx is " + idx);
            myvector.insertElementAt(new CHighScoreEntry(s, newscore) , idx);
            if((myvector.size()) > MAXENTRIES)
            {
                myvector.setSize(MAXENTRIES);
            }
            else
            {
                numentries++;
            }
            if(numentries == MAXENTRIES)
                lowestscore = myvector.lastElement().getScore(); // empty vector should not happen after insertion!
        }
        //debug();
    } // end addScore

}