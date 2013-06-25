package stone;

import java.awt.Color;

/**
 * Stein in Form eines senkrechten blauen Balkens aus 4 Grundsteinen
 * 
 */

	public class IStone extends Complexstone
	{
    /**
     * Der Konstruktor für einen senkrechten Stein aus 4 Grundsteinen
     * @param xpos_ X-Position des Mittelpunktes des neuen Steins
     * @param ypos_ Y-Position des Mittelpunktes des neuen Steins
     */

    public IStone(int xpos_, int ypos_)
    {
        super(xpos_, ypos_);
        Color stone = new Color(39, 64, 139);
        position.add(new Basicstone(stone, xpos, ypos - 1));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos, ypos));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos, ypos + 1));
        stone = stone.brighter();
        position.add(new Basicstone(stone, xpos, ypos + 2));
    }

}
