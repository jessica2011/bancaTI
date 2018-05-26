package hackatrix.convert;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author parainformaticos.com
 */
public class DeDate {

    public DeDate() {
    }

    /**
     * @param fecha de tipo java.sql.Date
     * @param formato (por ejemplo: dd/MM/yyyy)
     * @return fecha como string
     */
    public static String aString(Date fecha, String formato) {
        String result = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            result = sdf.format(fecha);

        } catch (IllegalArgumentException | NullPointerException e) {
        }

        return result;
    }

    /**
     * @param hora de tipo java.sql.Time
     * @param formato (por ejemplo: hh:mm:ss a)
     * @return hora como string
     */
    public static String aString(Time hora, String formato) {
        String result = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            result = sdf.format(hora);

        } catch (IllegalArgumentException | NullPointerException e) {
        }

        return result;
    }

    /**
     * @param fechahora de tipo java.sql.Timestamp
     * @param formato (por ejemplo: dd/MM/yyyy hh:mm:ss a)
     * @return fechahora como string
     */
    public static String aString(Timestamp fechahora, String formato) {
        String result = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            result = sdf.format(fechahora);

        } catch (IllegalArgumentException | NullPointerException e) {
        }

        return result;
    }
}

