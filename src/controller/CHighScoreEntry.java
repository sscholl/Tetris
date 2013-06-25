package controller;

/** 
 * Ein Datensatz der Highscores aus Spielername und Punktzahl
 */

@SuppressWarnings("serial")
public class CHighScoreEntry implements java.io.Serializable
{
    private String  name;
    private int    score;

    public int getScore()
    {
        return score;
    }
/**
 * Erzeugt einen Highscoreeintrag aus Name und zugehöriger Punktzahl 
 * @param name_ Name der Spielerin oder des Spielers 
 * @param score_ Die erreichte Punktzahl
 */
    public CHighScoreEntry(String name_, int score_)
    {
        this.name = new String(name_);
        this.score = score_;
    }

    public String toString()
    {
        return name + "    " + score;
    }

}