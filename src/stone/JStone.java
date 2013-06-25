package stone;

import java.awt.Color;

/**
 * Stein in Form eines J-förmigen Steins
 * 
 */

public class JStone extends Complexstone
{
    /**
     * Der Konstruktor für einen J-förmigen Stein aus 3 senkrechten Grundsteinen und einem links unten daneben
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
	
    public JStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(0, 0, 128);
        position.add(new Basicstone(stone, xpos,   ypos - 1));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos,   ypos));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos,   ypos + 1));
        position.add(new Basicstone(stone, xpos - 1, ypos + 1));
    }



}
