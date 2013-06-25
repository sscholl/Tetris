package stone;

import java.awt.Color;



/**
 * Stein in Form eines Pluszeichens aus insgesamt 5 Grundsteinen
 * 
 */

public class PlusStone extends Complexstone
{
    /**
     * Der Konstruktor für einen plusförmigen Stein aus 3 senkrechten Grundsteinen und jeweils links und rechts in der Mitte einem daneben
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
	
    public PlusStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(238, 221, 130);
        position.add(new Basicstone(stone, xpos,   ypos - 1));
        position.add(new Basicstone(stone, xpos,   ypos + 1));
        position.add(new Basicstone(stone, xpos,   ypos));
        position.add(new Basicstone(stone, xpos + 1, ypos));
        position.add(new Basicstone(stone, xpos - 1, ypos));
    }

}
