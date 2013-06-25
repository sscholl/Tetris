package stone;

import java.awt.Color;

/**
 * Stein in Form eines blauen Kreuzes mit einem gelben Grundstein (Mond) in einer Ecke
 * 
 */

public class MoonStone extends Complexstone
{

    /**
     * Der Konstruktor für einen Mondstein
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */
    public MoonStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        position.add(new Basicstone(Color.yellow, xpos - 2,   ypos - 2));
        position.add(new Basicstone(Color.blue, xpos,   ypos - 1));
        position.add(new Basicstone(Color.blue, xpos - 1,   ypos));
        position.add(new Basicstone(Color.blue, xpos, ypos)); // ursprung
        position.add(new Basicstone(Color.blue, xpos + 1, ypos));
        position.add(new Basicstone(Color.blue, xpos, ypos + 1));
    }



}
