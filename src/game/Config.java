package game;

/**
 * Klasse mit allen Konstanten, damit diese an einer Stelle verändert werden können
 */

public class Config
{
    public static int s_FieldWidth = 10;  //<! Breite des Spielfeldes in Grundsteinen
    private static int s_FieldHeight = s_FieldWidth * 2; //<! Höhe des Spielfeldes in Grundsteinen

    public static int s_linesperlevel = 1; // normal 10  //<! wie viele Linien pro Level aufgelöst werden müssen

    /**
     * Funktion die Höhe des Spielfeldes in Grundsteinen zurückliefert
     * @return Höhe des spielfeldes 
     */
    public static int GetFieldHeight()
    {
        return s_FieldHeight;
    }

    /**
     * Funktion die die Breite des Spielfeldes in Grundsteinen zurückliefert
     * @return Breite des spielfelds
     */
    public static int GetFieldWidth()
    {
        return s_FieldWidth;
    }

 
    
/**
 *  Funktion die je nach Level eine unterschiedliche Wartezeit 
 *  bis der Stein eine Position eine  Position weiterfällt
 * @param lvl Level für das die Wartezeit gesucht wird
 * @return Wartezeit in Millisekunden 
 */
    public static int Getadjustedstepwaittime(int lvl)
    {
        int result = 0;
        switch(lvl)
        {
            case 1:
                result = 500;
                break;
            case 2:
                result = 460;
                break;
            case 3:
                result = 420;
                break;
            case 4:
                result = 380;
                break;
            case 5:
                result = 340;
                break;
            case 6:
                result = 300;
                break;
            case 7:
                result = 260;
                break;
            case 8:
                result = 220;
                break;
            case 9:
                result = 180;
                break;
            case 10:
                result = 140;
                break;
            default:
                result = 100;
                break;
        }
        return result;
    }
}
