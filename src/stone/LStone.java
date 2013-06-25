package stone;

import java.awt.Color;


/**
 * Stein in L-Form
 *  
 */

public class LStone extends Complexstone
{
    /**
     * Der Konstruktor für einen L-förmigen Stein aus 3 senkrechten Grundsteinen und einem rechts unten daneben
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
	

    public LStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        position.add(new Basicstone(new Color(255, 215, 000), xpos,   ypos - 1));
        position.add(new Basicstone(new Color(255, 215, 000), xpos,   ypos));
        position.add(new Basicstone(new Color(255, 215, 000), xpos,   ypos + 1));
        position.add(new Basicstone(new Color(255, 215, 000), xpos + 1, ypos + 1));
    }

}
