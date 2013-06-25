package stone;

import java.util.Vector;

/**
 * Die Basisklasse für einen komplexen Spielstein . Dieser setzt sich aus mehreren Grundsteinen {@link Basicstone} zusammen, 
 * welche im Vector {@link #position} gespeichet werden.
 * Konkrete Spielsteinformen erben davon.
 */

public class Complexstone
{
    /**
     * Konstrukter für eine Spielstein
     * @param xpos_ X-Position des Spielsteins
     * @param ypos_ Y-Position des Spielsteins
     */
    public Complexstone(int xpos_, int ypos_)
    {
        xpos = xpos_;
        ypos = ypos_;
        hmru = -1;
        position = new Vector<Basicstone>();
    }
    /**
     * Kopier Konsturktor für den Spielstein der aus {@link Basicstone} zusammen gestzt wird
     * @param cstone Stein der zu Kopieren ist
     */
    public Complexstone(Complexstone cstone)
    {
        xpos = cstone.xpos;
        ypos = cstone.ypos;
        position = new Vector<Basicstone>();
        for(int i = 0; i < cstone.position.size(); i++)
        {
            position.add(new Basicstone(cstone.position.get(i)));
            position.get(i).isdirty = cstone.position.get(i).isdirty;
        }
    }
    /**
     * Speicher für die Grundsteine die den Spielstein bilden
     */
    public Vector<Basicstone> position;
    /**
     * X-Position des Steins
     */
    public int xpos;
    /**
     *  Y- Position des Steins
     */
    public int ypos;
    /**
     * Maximale Größe des Steins nach oben vom Drehpunkt aus
     */
    private int hmru;
    /**
     * Methode die die maximaleGröße vom Drehpunkt nach oben berechnet
     * @return Größe nach oben vom Drehpunkt aus
     */
    public int howManyRectanglesUp()
    {
        if(hmru < 0)
        {
            for(int i = 0; i < position.size(); i++)
            {
                if((position.elementAt(i)).ypos > hmru) hmru = (position.elementAt(i)).ypos ;
            }
        }
        return hmru;
    }

    /**
     *  Liefert die Rotkomponente des Steins an der übergebenen Position
     * @param x X-Position
     * @param y Y-position
     * @return liefert Rotkomponente des Grundsteins oder -1 falls da keiner an der gegeben Stelle steht
     */
    public int getComplexstonepartatxandypos(int x, int y)
    {
        int erg = -1;
        for(int z = 0; z < position.size(); z++)
        {
            if(position.elementAt(z).isat(x, y))
            {
                erg = position.elementAt(z).getMy_color().getRed();
            }
        }
        return erg;
    }

    /**
     * Methode um den Stein ungeprüft um eine Position nach unten zu bewegen
     */
    public void down()
    {
        for(int z = 0; z < position.size(); z++)
        {
            (position.elementAt(z)).ypos += 1;
        }
        this.ypos += 1;
    }
    /**
     * Methode um den Stein ungeprüft eine Position nach oben zu bewegen
     */
    public void up()
    {
        for(int z = 0; z < position.size(); z++)
        {
            (position.elementAt(z)).ypos -= 1;
        }
        this.ypos -= 1;
    }
    /**
     * Methode um den Stein ungeprüft um eine Position nach links zu bewegen
     */
    public void left()
    {
        for(int z = 0; z < position.size(); z++)
        {
            (position.elementAt(z)).xpos -= 1;
        }
        this.xpos -= 1;
    }
    /**
     * Methode um den Stein ungeprüft um eine Position nach rechts zu bewegen
     */
    public void right()
    {
        for(int z = 0; z < position.size(); z++)
        {
            (position.elementAt(z)).xpos += 1;
        }
        this.xpos += 1;
    }
    /**
     * Methode um den Stein um 90 Grad um seine rotationsachse zu drehen
     */
    public void rotate()
    {
        for(int z = 0; z < position.size(); z++)
        {
            int x0 = (position.elementAt(z)).xpos - this.xpos;
            int y0 = (position.elementAt(z)).ypos - this.ypos;
            (position.elementAt(z)).xpos = -y0 + xpos;
            (position.elementAt(z)).ypos =  x0 + ypos;
        }
    }

    /**
     * Generiert einen String aus dem Complexstein zu Testzwecken
     */
    @Override
    public String toString()
    {
        StringBuilder resultstring = new StringBuilder();
        resultstring.append("ComplexStone with ");
        //for(int i = 0; i < position.size(); i++)
        //{
        resultstring.append(position.size());
        //}
        resultstring.append(" Basicstones");
        return resultstring.toString();
    }


}
