package stone;

import java.awt.Color;

/**
* einzelner Grundstein (Grundquadrat) des Spielfeldes
*/
public class Basicstone
{

    /**
     * Farbe des Grundsteins
     */
    private Color my_color = new Color(255, 255, 255) ;
    /**
     * Position auf der X ebene des spielfelds
     */
    public int xpos = 0;
    /**
     *  Position auf der Y ebene des spielfelds
     */
    public int ypos = 0;
    /**
     * Flag ob der Stein teil einer vollen Linie ist
     */
    public boolean ispartoffullline;
    /**
     * Flag ob der Stein "dirty" ist
     */
    public boolean isdirty;

    /**
     * Konstuktor des atomaren Steins der Grundlage für alle Steine ist
     * @param my_color farbe des steins
     * @param _xpos x position
     * @param _ypos y position
     */
    public Basicstone(Color my_color, int _xpos, int _ypos)
    {
        setMy_color(my_color);
        ispartoffullline = false;
        xpos = _xpos;
        ypos = _ypos;
        isdirty = true;
    }
    /**
     * Kopierkonstruktor für den Grundstein
     * @param existierender Grundstein der zu kopieren ist
     */

    public Basicstone(Basicstone element)
    {
        my_color = element.my_color;
        xpos = element.xpos;
        ypos = element.ypos;
        ispartoffullline = false;
    }
    /**
     * Gibt die Farbe des Grundsteins zurück
     * @return Farbe des steins
     */

    public Color getMy_color()
    {
        if(my_color == null)
        {
            return Color.blue;
        }
        return my_color;
    }
    /**
     * Setzt die Farbe des Grundsteins
     * @param my_color zu setzende Farbe des Steins
     */
    public void setMy_color(Color my_color)
    {
        this.my_color = my_color;
    }

    /**
     * Gibt zurück, ob ein Stein an der gegebenen Position vorhanden ist
     * @param x X Position
     * @param y Y Position
     * @return false falls der Grundstein nicht an diese Stelle liegt, true false der Grundstein die gegebenen Koordinaten belegt
     */
    public boolean isat(int x , int y)
    {
        return (x == xpos && y == ypos) ? true : false;
    }


}
