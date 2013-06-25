package stone;

import java.awt.Color;



/**
 * Stein in Form eines S
 * 
 */

public class SStone extends Complexstone
{
    /**
     * Der Konstruktor für einen S-förmigen Stein aus 2 waagerechten Grundsteinen und 2 waagerechten Grundsteinen eine Position nach links versetzt in dern nächsten Zeile
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
	     
    public SStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(102, 205, 170);
        position.add(new Basicstone(stone, xpos,   ypos));
        position.add(new Basicstone(stone, xpos + 1,   ypos));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos,   ypos + 1));
        position.add(new Basicstone(stone, xpos - 1, ypos + 1));
    }

}
