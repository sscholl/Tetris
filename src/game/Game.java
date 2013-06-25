/*! \mainpage Blockgame PRG4 
 * \author Dominik Breu, Nils Moh und Simon Scholl
*
* 
* \section GZE Gedanken zum Entwurf
* 
* Die Spielsteine werden in Grundsteine (basicstones) zerlegt, da die Entfernung von Linien sonst zu aufwändig wäre.
* Der aktuelle Spielstein und das Spielfeld mit bereits gefallenen Steinen werden getrennt gespeichert, 
* um die Kollisionserkennung zu erleichtern. Dazu wird einfach für alle Grundsteine des Spielsteins getestet ob die vorgesehenen Positionen noch frei sind.
* Das Spielfeld speichert auch einen zwei Grundsteine breiten Rand, um Rotation und Kollisionen ohne Arrayzugriffsprobleme zu lösen.
*
* Bei der internen Definition des Spielsteins stellt sich die Frage nach der Darstellung als Matrix der belegten Positionen oder als
* Liste der einzelnen Grundsteine. 
* Witr haben uns für eine Liste von Grundsteinen entschieden, weil sie eine problemlose Nutzung einer Rotationsmatrix erlaubt und die Einzeldefinition der gedrehten Steine 
* überflüssig macht.
*
*Realisiert wurden die Spielsteine über eine Vererbung, d.h. Es gibt einen Komplexstein mit den notwendigen Methoden und Kindsteine welche jeweils 
*eine eigene konkrete Form haben.
*
* \section BED Bedienung
*
* Das Spiel startet automatisch. Mit den Pfeiltasten wird das Spiel gesteuert.
* 
* Die Pfeil-nach-links-Taste verschiebt den Spielstein um eine Position nach links.
* 
* Die Pfeil-nach-unten-Taste erhöht die Fallgeschwindigkeit des Spielsteins.
* 
* Die Pfeil-nach-oben-Taste dreht den Spielstein um 90 Grad mit dem Uhrzeigersinn.
* 
* Die Taste F2 startet ein neues Spiel, falls das letzte beendet wurde. Den selben Effekt hat die Auswahl 
* des Menüpunktes "New" im Untermenü "Game".
* 
* Das Spiel wird beendet durch Klicken auf den X-Button in der oberen rechten Ecke.  
* 
* \section SPS Spielsteine
* 
* Die Spielsteine entsprechen den klassischen Tetrissteinen (vgl. http://de.wikipedia.org/wiki/Tetris ). 
* Zusätzlich gibt es noch einen Plus-förmigen Stein, einen planetenförmigen Stein mit Mond zur Demonstration der Rotationsfunktion 
* und einen quadratischen disjunkten Stein zur Demonstration der Flexibilität unserer Steinimplementierung.
* Jeder Grundstein kann dabei eine eigene Farbe haben.  
*
* 
* \section KOM Kompatibilitätshinweis
* 
* Aus nicht nachvollziehbaren Gründen funktioniert die Darstellung der Highscoreliste mit dem Sun JDK 1.7 nicht immer. Mit Version 1.6 funktioniert die Darstellung 
* hingegen tadellos unter Windows und Linux. 
* 


\section FUN Warum sind bestimmte Funktionalitäten enthalten?
In erster Linie wurden die gestellten Anforderungen erfüllt. Darueber hinaus wurde eine musikalsche Untermalung eingebaut, weil sie essentiell für die Spielatmosphäre ist.
Die flexible Spielsteindarstellung mit einzelnen Grundsteinfarben erlaubt große Freiheiten in der künstlerischen Gestaltung.

\section RAUS Auf welche Funktionalitäten haben sie warum verzichtet?
Auf die Implementierung eines Netzwerkmodus bzw. einer Mehrspielerunterstützung haben wir verzichtet, 
weil die nötige Entwicklungszeit nicht verfügbar war und der Spielspass in keinem günstigen Verhältnis zum Aufwand steht.
		
\section AUS Welche Erweiterungsmöglichkeiten sehen Sie?
Eine benutzersteuerbare Musikauswahl wäre relativ leicht umzusetzen, weil die Abspielfunktionalität bereits vorhanden ist.
Ein grafischer Editor zum Erstellen eigener Spielsteine würde die Anpassbarkeit für den Benutzer fördern und die Flexibilität der Steindarstellung 
mehr zur Geltung bringen.  

\section VER Verbesserungen beim nächsten Projekt 
*
* Bei einer Wiederholung des Projektes würden wir die zeitraubenden Versuchs die Canvasklasse aus der Übung zu verwenden weglassen. 
* Sie unterstützt in der vorliegenden Form nur eine Darstellungsfläche und scheitert bei der Anzeige eines zusätzlichen Vorschaufensters.  
* 

*/


package game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


import world.Spielfeld;
import controller.Logic;

import gui.AdvancedGui;

/**
 * Übergreifende Klasse, die das MVC-Pattern verdrahtet. Ggf. würde man hier einzelne Komponenten wie das Frontend auswechseln. 
 *
 */
public class Game extends Thread implements KeyListener, ActionListener
{
    private static Game currentThread;  //<! zeiger auf das aktuelle spiel
    private static Spielfeld m_GameField; //<!zeiger auf das spielfeld
    private static Logic m_Logic; //<! zeiger auf die logik
    private static AdvancedGui m_Gui; //<! zeiger auf die Gui des spieles
    private boolean m_IsEnded = false; //<! Flag ob das spiel noch läuft
    private boolean m_StoneFallDown = false; //<Flag ob der stein schnell fallen soll oder nicht


/**
 *  Konstruktor für das Hauptklasse des Spiels
 * @param spf Zeiger auf das spielfeld \sa m_Gamefiled
 * @param log Zeiger auf die Logik \sa ma_Logic
 * @param gu  Zeiger auf die GUI \sa m_Gui
 */
    public Game(Spielfeld spf, Logic log, AdvancedGui gu)
    {
        m_GameField = spf;
        m_Logic = log;
        m_Gui = gu;
    }

    /**
     * Die überladene run-Methode aus Thread um unseren eigen Thread zu erzeugen
     */
    public void run()
    {
        System.out.println("start running");
        m_StoneFallDown = false;
        m_Gui.zeichnen();
        m_Gui.zeichnen_preview();
        m_Logic.newgame();
        m_IsEnded = false;
        while(!IsEnded())
        {
            PerformStep();
        }
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    m_Gui.highscoredialog();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("stop running");
        m_Logic.nolongerrunning();
    }

    
    /**
     * Funktion die den stein um eine Position fallen lässt und dazwischen wartet 
     */
    private synchronized void PerformStep()
    {
        try
        {
            for(int Runned = 0; Runned < GetStepWaitTime(); ++Runned)
            {
                wait(1);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        if(m_Logic.down())
        {
            SetStoneFalledDown();
            if(!m_Logic.newStone())
                m_IsEnded = true;
        }
        UpdateGameField();
    }

    /**
     * Funktion die die Wartezeit zwischen den einzelnen Schritten abruft, beachtet ggf. schnelles Fallen
     * @return Wwartezeit zwischen den einzeln Steps, beachtet Schnelles Fallen und das aktuelle Level
     */
    public int GetStepWaitTime()
    {
        if(m_StoneFallDown)
            return 50;
        else
            return Config.Getadjustedstepwaittime(m_GameField.Level);
    }

    /**
     * Funktion die den Status des Spiels abruft 
     * @return ob das Spiel beendet ist, false wenn das Spiel noch läuft
     */
    private boolean IsEnded()
    {
        return m_IsEnded;
    }

    /**
     * Funktion die die Fallgeschwindigkeit der Steine von normal auf schnell umstellt
     */
    private synchronized void ActionDown()
    {
        m_StoneFallDown = true;
    }

    /**
     * Funktion die einträgt, dass der Stein nicht mehr fällt und er somit das untere Ende des Spielfeld erreicht hat
     */
    public void SetStoneFalledDown()
    {
        m_StoneFallDown = false;
    }

    
    /**
     *  Hauptfunktion, die alle Einstellungen trifft und ein Spiel startet
     * @param args
     */
    public static void main(String[] args)
    {
        m_GameField = new Spielfeld(Config.GetFieldWidth(), Config.GetFieldHeight());
      
        m_Logic = new Logic(m_GameField);
        m_Gui = new AdvancedGui(m_GameField, m_Logic, null, null);
        m_Gui.init();
        currentThread = new Game(m_GameField, m_Logic, m_Gui);
        m_Gui.addKeyListener(currentThread);
        m_Gui.m_item.addActionListener(currentThread);
        if(m_Logic.mayrunAndSet())
        {
            currentThread.start();
        }
        Game.play();
    }

    /**
     * Zuordnung der Reaktion auf einzelne Tasten
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                ActionDown();
                break;
            case KeyEvent.VK_LEFT:
                m_Logic.left();
                break;
            case KeyEvent.VK_RIGHT:
                m_Logic.right();
                break;
            case KeyEvent.VK_UP:
                m_Logic.rotate();
                break;
            case KeyEvent.VK_SPACE:
                m_Logic.rotate();
                break;
            case KeyEvent.VK_F2:
            {
                if(m_Logic.mayrunAndSet())
                {
                    newGameforce();
                }
                break;
            }
            default:
                break;
        }
        UpdateGameField();
    }

    /**
     * Funktion die das Spielfeld aktualisiert 
     */
    public void UpdateGameField()
    {
        m_Gui.zeichnen();
        if(m_GameField.nextstone != null)
        {
            m_Gui.zeichnen_preview();
        }
        if(m_GameField.recolorlines(Color.BLACK) > 0)
        {
            m_Gui.zeichnen();
            m_GameField.setScore(m_GameField.getScore() + m_GameField.removefulllines() * m_GameField.getNettobreite());
            m_Gui.zeichnen();
        }
    }

    
    /**
     * Funktion die ein neues Spiel startet, insbesondere eine neue Gameinstanz erstellt, die Keylistener neu verdrahtet und den Thread startet
     */
    public void newGameforce()
    {
        m_Gui.removeKeyListener(currentThread);
        currentThread = new Game(m_GameField, m_Logic, m_Gui);
        m_Gui.addKeyListener(currentThread);
        currentThread.start();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }


    private static File soundFile; //<! unsere Sounddatei
    private static Clip clip; //<! interne Javarepräsentation der Sounddatei
    private static AudioInputStream soundIn; //<! Eingabestream um die Sounddatei zu verarbeiten
    private static DataLine.Info info;  //<! Informationen über die Sounddatei um interne Variablen zu füllen 

    /**
     * Funktion die den Hintergrundsound abspielt
     */
    private static void play()
    {
        try
        {
            soundFile = new File(System.getProperty("user.dir") + "/src/game/sound.wav");
            soundIn = AudioSystem.getAudioInputStream(soundFile);
        }
        catch(Exception e)
        {
            soundFile = new File("game/sound.wav");
            try
            {
                soundIn = AudioSystem.getAudioInputStream(soundFile);
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        try
        {
            info = new DataLine.Info(Clip.class, soundIn.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(soundIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Funktion die dem Menüpunkt New im Menü Game eine Funktionalität ermöglicht
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("New"))
        {
            if(m_Logic.mayrunAndSet())
            {
                newGameforce();
            }
        }
    }

}
