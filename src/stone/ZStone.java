package stone;

import java.awt.Color;


/**
 * Stein in Form eines Z
 * 
 */

public class ZStone extends Complexstone
{
    
	 /**
     * Der Konstruktor für einen Z-förmigen Stein aus 2 waagerechten Grundsteinen und 2 waagerechten Grundsteinen eine Position nach rechts versetzt in der nächsten Zeile
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */

    public ZStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(139, 37, 0); // dark red
        position.add(new Basicstone(stone, xpos,   ypos));
        position.add(new Basicstone(stone, xpos - 1,   ypos));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos,   ypos + 1));
        position.add(new Basicstone(stone, xpos + 1, ypos + 1));
    }

}
