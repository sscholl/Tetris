package controller;

import java.util.Random;

import stone.Complexstone;
import stone.IStone;
import stone.LStone;
import stone.JStone;
import stone.MoonStone;
import stone.NStone;
import stone.PlusStone;
import stone.OStone;
import stone.SStone;
import stone.ZStone;
import stone.TStone;
import world.Spielfeld;

/**
 * Logikklasse ist Controllerkomponente aus dem MVC-Pattern. Sie kennt das Spielfeld und stellt Funktionen zur geprüften Verschiebung der Steine zur Verfügung.  
 * 
 */

public class Logic
{
    public CHighScores  highscores    = new CHighScores();

    /**
     * Flag ob gerade ein Spiel stattfindet, verhindert mehrfaches Starten eines Spiels
     */
    private boolean gamerunning = false;

    /**
     * Variable um das Spielfeld kontroliert zu speichern
     */
    Spielfeld spielfeld;
    /**
     * Zufallsgenerator dient zum Erzeugen der Zufallszahlem für die {@link #newStone()} methode zu erzeugen
     */
    Random rnd;
    /**
     * Konstruktor für die Logicklasse des spieles
     * @param myspielfeld spiefeld für das der Controler die spielzüge kontrolieren soll
     */
    public Logic(Spielfeld myspielfeld)
    {
        spielfeld = myspielfeld;
        rnd = new Random();
    }


    /**
    * threadsichere Abfragemethode ob ein Spiel stattfindet
    * falls nicht wird das Flag gesetzt
    */
    public synchronized boolean mayrunAndSet()
    {
        if(gamerunning) return false;
        else
        {
            gamerunning = true;
            return true;
        }
    }

    /**
     * threadsichere Rücksetzmethode wenn das Spiel endet
     */
    public synchronized void nolongerrunning()
    {
        gamerunning = false;
    }


    /**
     * Methode zum erzeugen eines neuen Spielsteines. Die Form wird zufällig ausgewählt.
     * 
     * @return true falls neuer stein angelegt werden kann false falls nicht möglich
     *      */
    public boolean newStone()
    {
        if(spielfeld.nextstone == null)
        {
            spielfeld.nextstone = makeStone(rnd.nextInt(10));
        }
        Complexstone cs = makeStone(rnd.nextInt(10));
        if(spielfeld.maymove(spielfeld.nextstone))
        {
            spielfeld.cstone = spielfeld.nextstone;
            spielfeld.nextstone = cs;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
    *  erzeugt eine der Steinformen, namentlich die mit der uebergebenen Nummer oder die der Modulonummer
    *  falls der Parameter zu gross ist
    *  @param rndst Nummer des gewünschten Steins im Bereich 0..9
    *  @return neu angelegter Stein der angeforderten Form
    */
    private Complexstone makeStone(int rndst)
    {
        Complexstone cs = null ;
        rndst %= 10;
        switch(rndst)
        {
            case 0: cs = new LStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 1: cs = new SStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 2: cs = new NStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 3: cs = new MoonStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 4: cs = new IStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 5: cs = new OStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 6: cs = new TStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 7: cs = new JStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 8: cs = new ZStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
            case 9: cs = new PlusStone(2 + (spielfeld.getNettobreite() / 2), 4); break;
                //      case 4: cs = new LStone( 2+(spielfeld.getNettobreite()/2), 4); break;
            default:
                System.out.println("switch went wrong");
                break;
        }
        return cs;
    }


    /**
     * Methode um den Stein nach unten zu bewegen. Wenn er ganz unten angekommen ist, wird der Stein zerstört und in den Hintergrund kopiert.
     * @return true kann stein nach unten bewegen false kann stein nicht bewegen
     */
    public boolean down()  // true if stone hit the ground
    {
        if(spielfeld.cstone == null) return false;
        Complexstone tempcstone = new Complexstone(spielfeld.cstone);
        tempcstone.down();
        if(spielfeld.maymove(tempcstone))
        {
            spielfeld.cstone = tempcstone;
        }
        else  // Endposition
        {
            for(int i = 0; i < spielfeld.cstone.position.size(); i++)
            {
                int x = spielfeld.cstone.position.elementAt(i).xpos;
                int y = spielfeld.cstone.position.elementAt(i).ypos;
                spielfeld.cstone.position.elementAt(i).isdirty = false;
                spielfeld.setstoneatposRaw(x, y, spielfeld.cstone.position.elementAt(i));
            }
            //spielfeld.cstone = null;
            //newStone();
            return true;
        }
        return false;
    }
    /**
     * Bewegt Stein nach links, falls dort freier Platz ist
     */
    public void left()
    {
        if(spielfeld.cstone == null) return ;
        Complexstone tempcstone = new Complexstone(spielfeld.cstone);
        tempcstone.left();
        if(spielfeld.maymove(tempcstone))
        {
            spielfeld.cstone = tempcstone;
        }
    }
    /**
     * Bewegt Stein nach rechts, falls da freier Platz ist
     */
    public void right()
    {
        if(spielfeld.cstone == null) return ;
        Complexstone tempcstone = new Complexstone(spielfeld.cstone);
        tempcstone.right();
        if(spielfeld.maymove(tempcstone))
        {
            spielfeld.cstone = tempcstone;
        }
    }
    /**
     * Rotiert den Stein um seine Rotationsachse, falls da freier Platz ist
     */
    public void rotate()
    {
        if(spielfeld.cstone == null) return ;
        Complexstone tempcstone = new Complexstone(spielfeld.cstone);
        tempcstone.rotate();
        if(spielfeld.maymove(tempcstone))
        {
            spielfeld.cstone = tempcstone;
        }
    }
    
    /**
     * Reinitialisiert existierendes Spielfeld
     */
    public void newgame()
    {
        spielfeld.reinit();
        newStone();
    }

}
