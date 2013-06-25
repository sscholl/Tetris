package stone;

import java.awt.Color;


/**
 * Stein in Form eines T
 * 
 */

public class TStone extends Complexstone
{
    /**
     * Der Konstruktor für einen T-förmigen Stein aus 3 waagerechten Grundsteinen und einem in der Mitte darunter
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
	
    public TStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(104, 34, 139);
        position.add(new Basicstone(stone, xpos,   ypos));
        position.add(new Basicstone(stone, xpos + 1,   ypos));
        position.add(new Basicstone(stone, xpos - 1,   ypos));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos, ypos + 1));
    }

}
