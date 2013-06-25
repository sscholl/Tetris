package stone;

import java.awt.Color;



/**
 * Stein in Form eines Quadrates 2x2
 * 
 */

public class OStone extends Complexstone
{
    /**
     * Der Konstruktor für einen quadratischen 2x2 großen Stein 
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
	

    public OStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(255, 165, 0); // dark yellow
        position.add(new Basicstone(stone, xpos, ypos));
        position.add(new Basicstone(stone, xpos + 1, ypos));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos, ypos - 1));
        position.add(new Basicstone(stone, xpos + 1, ypos - 1));
    }

}
