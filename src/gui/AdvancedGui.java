package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.Rectangle;

import java.awt.event.ActionListener;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.SpringLayout;

import world.Spielfeld;
import controller.*; // Logic
/**
 * Implementierung des Viewobjektes nach MVC Pattern. 
 * Enthält alle Elemente die dafür notwendig sind, eine solche View zu erstellen.  
 *
 */

public class AdvancedGui extends JFrame
{

    private static final long serialVersionUID = 1L; //!< muss halt so 
    private Tetrisgamepannel leinwand;  //!< Das spielfeld auf dem die setine gezeichnet werden linkes element
    private Tetrisgamepannel vorschaubild; //!< Das voirschaubild des nächstensteins rechts obben mitte
    private JPanel sidebar; //!< vater panel für \sa vorschaulbild  und für die level und higscore anzeige
    private JPanel contentPane; //!< vater pannel für alle JPanels und elemente innerhalb von AdvancedGui
    protected Spielfeld mygamefield; //!< Verweiß auf die worldkomponente innherhalb des MVC patern
    protected Logic mylogic; //!< Verweiß auf die Controler komponte innerhalb des MVC pattern
    private JLabel m_lvl; //!< level anzeige
    private JLabel m_curlvl; //!< aktueler level
    private JLabel m_score; //!< punkte anzeige
    private JLabel m_curscore; //!< aktuelle punktzahl

    private Graphics2D mainGraphics; //!< zeichenobjekt für \sa leinwand
    private Graphics2D previewGraphics; //!< zeichenobjekt für \sa vorschaubild

    private Image previewImg; //!< zeichenfläche für \sa mainGrahics 
    private Image mainImg; //!< zeichenfläche für \sa previewGraphics 

    private static int ratiox = 20; //!< größe des steins in x richtung
    private static int ratioy = 20; //!< größe des steins in y richtung
    private static int spielfeldbreite = game.Config.s_FieldWidth; //!< breite des spielfeldes 
    private static int spielfeldhoehe = spielfeldbreite * 2; //!< höhe des Spielfeldes
    private static int fensterbreite = (spielfeldbreite * ratiox)+302; //!< breite des fensters
    private static int fensterhoehe = (spielfeldhoehe * ratioy) +25; //!< höhe des fesnters
    @SuppressWarnings("rawtypes")
    private JList list = new JList(); //!< liste der highscore auf datei und aktuel higscores
    private JMenu menu; //!< Menü für unser spiel
    private JMenuBar m_bar;  //!< Menüleiste für unser Spiel
    public JMenuItem m_item; //!< Menüeintrag in \sa menu
    private  SpringLayout layout; //!< Das Layout für unser Sideannel
    private Font newlabelfont; //!< Der font für unser sidepannel
    private CHighScores hightemp; //!< die higscoreliste für unser Spiel

    @SuppressWarnings("unchecked")
    /**
     * Erstellt ein JFrame der das Tetrisspiel als Fenster im Betriebsystem repräsentiert.
     * @param _mygamefield  Verweis auf Model im MVC-Pattern
     * @param _mylogic      Verweise auf Control im MVC-Pattern
     * @param _KeyListener   Anzuhaengender Listener, damit GUI-Ereignisse den Spielthread erreichen koennen, darf null sein
     * @param _ActionListner Anzuhaengender Listener, damit GUI-Ereignisse den Spielthread erreichen koennen, darf null sein
     */
    public AdvancedGui(Spielfeld _mygamefield, Logic _mylogic,
                       KeyListener _KeyListener, ActionListener _ActionListner)
    {
        mygamefield = _mygamefield;  
        mylogic = _mylogic;        
        layout = new SpringLayout();

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setPreferredSize(new Dimension(fensterbreite , fensterhoehe));
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.DARK_GRAY);
        
        leinwand = new Tetrisgamepannel();
        leinwand.setPreferredSize(new Dimension(spielfeldbreite*ratiox,spielfeldhoehe*ratioy));
        leinwand.setBackground(Color.white);

        vorschaubild = new Tetrisgamepannel();
        vorschaubild.setPreferredSize(new Dimension(ratiox * 7, ratioy * 7));
        vorschaubild.setBackground(Color.GREEN);

        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(300, fensterhoehe));
        sidebar.setLayout(layout);
        sidebar.setBackground(Color.DARK_GRAY);

        m_lvl = new JLabel();
        m_lvl.setText("Level");
        m_lvl.setPreferredSize(new Dimension(100, 30));
        m_lvl.setForeground(Color.white);
        newlabelfont = new Font(m_lvl.getFont().getName(), m_lvl.getFont().getStyle(), 22);
        m_lvl.setFont(newlabelfont);
       
        m_curlvl = new JLabel();
        m_curlvl.setText("#");
        m_curlvl.setPreferredSize(new Dimension(100, 30));
        m_curlvl.setForeground(Color.white);
        m_curlvl.setFont(newlabelfont);
        
        m_score = new JLabel();
        m_score.setText("Score");
        m_score.setPreferredSize(new Dimension(100, 30));
        m_score.setForeground(Color.white);
        m_score.setFont(newlabelfont);
        
        m_curscore = new JLabel();
        m_curscore.setText("#");
        m_curscore.setForeground(Color.white);
        m_curscore.setPreferredSize(new Dimension(100, 30));
        m_curscore.setFont(newlabelfont);
        
        layout.putConstraint(SpringLayout.WEST, vorschaubild, 80, SpringLayout.WEST, sidebar);
        layout.putConstraint(SpringLayout.NORTH, vorschaubild, 80,SpringLayout.NORTH, sidebar);
        
        layout.putConstraint(SpringLayout.NORTH, m_lvl, 300, SpringLayout.NORTH, sidebar);
        layout.putConstraint(SpringLayout.WEST, m_lvl, 10, SpringLayout.WEST, sidebar);
        
        layout.putConstraint(SpringLayout.NORTH, m_curlvl, 300, SpringLayout.NORTH, sidebar);
        layout.putConstraint(SpringLayout.WEST, m_curlvl, 1, SpringLayout.EAST, m_lvl);
        
        layout.putConstraint(SpringLayout.WEST, m_score, 10, SpringLayout.WEST, sidebar);
        layout.putConstraint(SpringLayout.NORTH, m_score, 20, SpringLayout.SOUTH, m_lvl);
        
        layout.putConstraint(SpringLayout.NORTH, m_curscore, 20, SpringLayout.SOUTH, m_lvl);
        layout.putConstraint(SpringLayout.WEST, m_curscore, 1, SpringLayout.EAST, m_score);
        
        m_bar = new JMenuBar();
        menu = new JMenu("Game");
        m_item = new JMenuItem("New");
       
        m_bar.add(menu);
        menu.add(m_item);
         
        hightemp = JHighscoreFileIO.fromDisk();
        if(hightemp != null)
        {
            mylogic.highscores = hightemp;

        }

        list.setListData(mylogic.highscores.getHighScoreVector());
        list.setPreferredSize(new Dimension(150, 400));
        
        contentPane.add(leinwand, BorderLayout.LINE_START);
        contentPane.add(sidebar, BorderLayout.LINE_END);
        contentPane.add(m_bar, BorderLayout.PAGE_START);
        
        sidebar.add(vorschaubild);
        sidebar.add(m_lvl);
        
        sidebar.add(m_curlvl);
        sidebar.add(m_score);
        sidebar.add(m_curscore);
        
        if(_KeyListener != null)
        {
            addKeyListener(_KeyListener);
        }
 
        if(_ActionListner != null)
        {
            m_item.addActionListener(_ActionListner);
        }
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Blockgame PRG4.0");
        setResizable(false);
        setVisible(true);
        pack();
    }
    
    /**
     * Diese Funktion dient dazu alle Grafiken und Bilder zu erzeugen, die wir brauchen um das Spiel auf den Bildschirm bringen zu können.
     * Dies kann nicht im Konstruktor passieren, da die objekte schon alle fertig erstellt sein müssen. 
     */
    
    public void init()
    {
        previewImg = vorschaubild.createImage(fensterbreite, fensterhoehe);
        previewGraphics = (Graphics2D) previewImg.getGraphics();
        mainImg = leinwand.createImage(fensterbreite, fensterhoehe);
        mainGraphics = (Graphics2D) mainImg.getGraphics();
        leinwand.suitableImg = mainImg;
        vorschaubild.suitableImg = previewImg;
    }

    /**
    * Synchronisierte Funktion um das Spielfeld und den momentanen Spielstein auf unsere Spielfeldzeichenflaeche zu schreiben
    */
    public synchronized void zeichnen()
    {
        for(int i = 0; i < mygamefield.getNettohoehe(); i++)
        {
            for(int j = 0; j < mygamefield.getNettobreite(); j++)
            {
                Rectangle recht = new Rectangle(j * ratiox, i * ratioy,
                                                ratiox - 2, ratioy - 2);
                mainGraphics.setColor(Color.DARK_GRAY);
                Color paintcolor = Color.white;
                if(mygamefield.mystonearray[j + 2][i + 2] != null)
                {
                    paintcolor = ((mygamefield.mystonearray[j + 2][i + 2]
                                   .getMy_color()));
                }
                mainGraphics.setColor(paintcolor);
                mainGraphics.fill(recht);
                mainGraphics.draw(recht);
            }
            if(mygamefield.cstone != null)
            {
                for(int k = 0; k < mygamefield.cstone.position.size(); k++)
                {
                    if(mygamefield.cstone.position.get(k).isdirty)
                    {
                        mainGraphics.setColor(mygamefield.cstone.position
                                              .get(k).getMy_color());
                        int x = mygamefield.cstone.position.get(k).xpos - 2;
                        int y = mygamefield.cstone.position.get(k).ypos - 2;
                        Rectangle recht = new Rectangle(x * ratiox, y * ratioy, ratiox - 2, ratioy - 2);
                        mainGraphics.fill(recht);
                        mainGraphics.setColor(Color.black);
                        mainGraphics.draw(recht);
                    }
                }
            }
        }
        m_curlvl.setText("" + mygamefield.Level);
        m_curscore.setText("" + mygamefield.score);
    }


    /**
     * Synchronisierte Funktion, die den folgenden Spielstein auf unsere Zeichenflaeche (Vorschaufeld) zeichnet
     */
    public synchronized void zeichnen_preview()
    {
        Rectangle recht = new Rectangle(0, 0, ratiox * 7, ratioy * 7);
        previewGraphics.setColor(Color.LIGHT_GRAY);
        previewGraphics.fill(recht);
        if(mygamefield.nextstone == null)
            return;
        int xoffset = mygamefield.nextstone.xpos;
        int yoffset = mygamefield.nextstone.ypos;
        for(int k = 0; k < mygamefield.nextstone.position.size(); k++)
        {
            previewGraphics.setColor(mygamefield.nextstone.position.get(k).getMy_color());
            int x = mygamefield.nextstone.position.get(k).xpos - xoffset + 3;
            int y = mygamefield.nextstone.position.get(k).ypos - yoffset + 3;
            recht = new Rectangle(x * ratiox, y * ratioy, ratiox - 2, ratioy - 2);
            previewGraphics.fill(recht);
        }
    }
    
    /**
     * Synchronisierte Funktion um nach Spielende die Highscores anzuzeigen und bei Erreichen der Highscoreliste den Spielernamen abzufragen
     */
    @SuppressWarnings("unchecked")
	public synchronized void highscoredialog()
    {
        if(mylogic.highscores.getLowestScore() <= mygamefield.getScore())
        {
            String name = JOptionPane.showInputDialog(this.getContentPane(), "You got " + mygamefield.getScore() + " points! Please enter your name: ", "You got a new highscore!", JOptionPane.INFORMATION_MESSAGE);
            mylogic.highscores.enterScore(name, mygamefield.getScore());
        }
        else
        {
            JOptionPane.showMessageDialog(this.getContentPane(), "You got no new highscore, try another time", "Highscore", JOptionPane.INFORMATION_MESSAGE);
        }
        list.setListData(mylogic.highscores.getHighScoreVector());
        JOptionPane.showMessageDialog(this.getContentPane(), list, "Highscores", JOptionPane.PLAIN_MESSAGE);
        //System.out.println("now trying to write highscore file");
        JHighscoreFileIO.toDisk(mylogic.highscores);
    }


    /**
     * Eigene Zeichenfläche mit eigenem Buffer um mehrere unabhaengige Zeichenflächen zu unterstützen
     */
    private class Tetrisgamepannel extends JPanel
    {

        private Image suitableImg; //!<  Der Zeichenpuffer speziell für diese Instanz

        private static final long serialVersionUID = 1L; //!< damit alle funktionen eines jpanel implementiert sind

        /**
         * Funktion von JPanel - überladen damit wir eigene Grafiken zeichen können 
         */
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(suitableImg, 0, 0, null);
            repaint();
        }

    }

}
