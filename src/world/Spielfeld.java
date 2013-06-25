package world;

import java.awt.Color;


import stone.Basicstone;
import stone.Complexstone;

/*
 * Model des MVC-Patterns. Enthält das Spielfeld als Grundsteinarray sowie den aktuellen komplexen Spielstein. 
 */

public class Spielfeld
{

    /**
     * Höhe des Soielfelds mit Kollisionserkennungsrahmen in Grundsteinen
     */
    private int hoehe;
    /**
     * Höhe des Spielfelds ohne Kollissionserkennungsrahmen in Grundsteinen
     */
    private int nettohoehe;
    /**
     * Breite des Spielfelds mit Rahmen
     */
    private int breite;
    /**
     * Breite des Spielfelds ohne Rahmen
     */
    private int nettobreite;
    /**
     * Hintergrundarray um die einzelnen Steine nachdem sie runtergefallen sind zu speichern und um den Kollisionserkennungsrand zu realisieren
     */
    public Basicstone[][] mystonearray ;
    /**
     * Momentaner Spielstein der sich aus Einzelsteinen zusammensetzt
     */
    public Complexstone cstone ;   // null if no stone is currently falling
    /*
    * Spielstein der als nächster an die Reihe kommt
    */
    public Complexstone nextstone; // the next stone to be used, currently in preview window
    /**
     * Spielstand in Punkten
     */
    public int score;

    public int Level;             //<! aktuelles Level
    private int linestillnxtlvl;  //<! Linien pro Level

    /**
     *
     * @return liefert den Punktestand
     */

    public int getScore()
    {
        return score;
    }
    public void setScore(int score)
    {
        this.score = score;
    }
    /**
     * Liefert die Höhe des Spielfelds ohne Rand in Grundsteinen zurück 
     * @return Höhe ohne Rand
     */

    public int getNettohoehe()
    {
        return nettohoehe;
    }
    /**
     *  Liefert die Spielfelds ohne Rand in Grundsteinen
     * @return Breite ohne Rand
     */
    public int getNettobreite()
    {
        return nettobreite;
    }
    
    /**
     * Liefer den Grundstein an der übergeben Position zurück
     * @param x X Position
     * @param y Y position
     * @return Grundstein an der Position x,y bzw. null
     */
    public Basicstone getstoneatpos(int x , int y)
    {
        return mystonearray[x + 2][y + 2];
    }
    
    /**
     * Setzt an die Position einen Basistein
     * @param x X position
     * @param y Y position
     * @param b Der Stein, welcher in das Hintergrundarray geschrieben werden soll
     */
    public void setstoneatposRaw(int x , int y, Basicstone b)
    {
        mystonearray[x][y] = b;;
    }

    /**
     * Findet volle Linien  
     * @param linecolor Wunschfarbe für die vollen Linien
     */
    public int recolorlines(Color linecolor)
    {
        int count = 0;
        for(int i = 2; i < 2 + nettohoehe; i++)
        {
            boolean isfullline = true;
            for(int j = 2; j < 2 + nettobreite; j++)
            {
                isfullline &= (mystonearray[j][i] != null);
            }
            if(isfullline)
            {
                count++;
                for(int j = 2; j < 2 + nettobreite; j++)
                {
                    mystonearray[j][i].setMy_color(linecolor);
                    mystonearray[j][i].ispartoffullline = true;
                }
            }
        }
        return count;
    }

    /**
     * Löscht die vollen Linien aus dem Hintergrundarray
     * @return Anzahl der gelöschten Linien
     */
    public int removefulllines()
    {
        int counter = 0;
        for(int i = 2; i < 2 + nettohoehe; i++)
        {
            if((mystonearray[2][i] != null) && (mystonearray[2][i].ispartoffullline))
            {
                moveblockdown(i);
                counter++;
            }
        }
        score += counter * nettobreite * Level;
        linestillnxtlvl -= counter;
        if(linestillnxtlvl <= 1)
        {
            linestillnxtlvl = game.Config.s_linesperlevel;
            Level++;
        }
        return counter;
    }

    /**
     *  Kopiert den Rest des Hintergrundarray y Zeilen nach unten
     * @param Zeilenzahl die nach unten kopiert werden sollen
     */
    public void moveblockdown(int ytoerased)
    {
        for(int i = ytoerased ; i > 2; i--)
        {
            for(int j = 2; j < 2 + nettobreite; j++)
            {
                mystonearray[j][i] = mystonearray[j][i - 1];
            }
        }
    }
    /**
     * Konstuktor für das Spielfeld
     * @param nettobreite Breite des spielfeldes ohne Rahmen in Grundsteinen
     * @param nettohoehe Höhe des Spielfeldes ohne Rahmen in Grundsteinen
     */

    public Spielfeld(int nettobreite , int nettohoehe)
    {
        this.nettobreite = nettobreite;
        this.nettohoehe = nettohoehe;
        this.breite = nettobreite + 4;
        this.hoehe = nettohoehe + 4;
        this.score = 0;
        this.Level = 1;
        this.linestillnxtlvl = game.Config.s_linesperlevel;
        mystonearray = new Basicstone[breite][hoehe];
        for(int i = 0; i < breite; i++)
        {
            mystonearray[i][hoehe - 2] = new Basicstone(new Color(255, 0, 0), i, hoehe - 2);
        };
        for(int i = 0; i < breite; i++)
        {
            mystonearray[i][hoehe - 1] = new Basicstone(new Color(255, 0, 0), i, hoehe - 1);
        };
        for(int i = 0; i < hoehe; i++)
        {
            mystonearray[0][i] = new Basicstone(new Color(254, 0, 0), 0, 1);
        };
        for(int i = 0; i < hoehe; i++)
        {
            mystonearray[1][i] = new Basicstone(new Color(254, 0, 0), 1, i);
        };
        for(int i = 0; i < hoehe; i++)
        {
            mystonearray[breite - 2][i] = new Basicstone(new Color(254, 0, 0), breite - 2, i);
        };
        for(int i = 0; i < hoehe; i++)
        {
            mystonearray[breite - 1][i] = new Basicstone(new Color(254, 0, 0), breite - 1, i);
        };
    }

    /**
     * Ausgabemethode für die Spielfeldlkasse zu Testzwecken
     */
    @Override
    public String toString()
    {
        StringBuilder resultstring = new StringBuilder();
        for(int i = 0; i < hoehe; i++)
        {
            for(int j = 0; j < breite; j++)
            {
                if(mystonearray[j][i] == null && cstone == null)
                {
                    resultstring.append("___ ");
                    // resultstring.append((cstone.getComplexstonepartatxandypos(j, i) >= 0)? cstone.getComplexstonepartatxandypos(j, i) : "___ ") ;
                }
                else if(mystonearray[j][i] == null)
                {
                    // resultstring.append("___ ");
                    resultstring.append((cstone.getComplexstonepartatxandypos(j, i) >= 0) ? cstone.getComplexstonepartatxandypos(j, i) + " " : "___ ") ;
                }
                else
                {
                    resultstring.append(mystonearray[j][i].getMy_color().getRed());
                    resultstring.append(' ');
                }
            }
            resultstring.append('\n');
        }
        resultstring.append(score);
        return resultstring.toString();
    }

    /**
     * Prüft ob ein Stein an die gegebene Position kopiert werden könnte, d.h. ob der Platz im Array fest ist
     * @param cs Stein der bewegt werden soll
     * @return true falls der Stein an die inhärente Position passt, sonst false
     */
    public boolean maymove(Complexstone cs)
    {
        boolean works = true;
        for(int z = 0; z < cs.position.size(); z++)
        {
            works = works && (null ==    mystonearray[cs.position.elementAt(z).xpos][cs.position.elementAt(z).ypos]) ;
        }
        //System.out.println(works);
        return works;
    }

   /**
    * Löscht alle Steine(Grundsteine und Complexstones) aus dem spielfeld so das ein neues Spiel in dem Spielfeld laufen kann. 
    */  
    public  void reinit()
    {
        this.score = 0;
        this.nextstone = null;
        for(int i = 2 ; i < nettohoehe + 2; i++)
        {
            for(int j = 2 ; j < 2 + nettobreite; j++)
            {
                mystonearray[j][i] = null;
            }
        }
    }



}
