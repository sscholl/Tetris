package stone;

import java.awt.Color;



/**
 * Disjunkter Stein aus 4 Grundsteinen, die die Ecken eines Quadrats bilden 
 * 
 */

public class NStone extends Complexstone
{
    /**
     * Der Konstruktor für einen Stein aus 4 voneinander getrennten, quadratisch angeordneten Elementen
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */

	public NStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        //position.add(new Basicstone(new Color(123,124,125), xpos,   ypos-2));
        position.add(new Basicstone(new Color(153,  050,    204).brighter(), xpos - 1,   ypos - 1));
        position.add(new Basicstone(new Color(148 , 000 ,   211).darker(), xpos + 1,   ypos - 1));
        //position.add(new Basicstone(new Color(123,124,125), xpos, ypos));
        position.add(new Basicstone(new Color(138   , 043 ,  226).darker(), xpos - 1, ypos + 1));
        position.add(new Basicstone(new Color(160   , 032 ,  240).brighter(), xpos + 1, ypos + 1));
    }




}
