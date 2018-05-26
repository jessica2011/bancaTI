package web.tag;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import hackatrix.convert.DeString;

public class DiaActual extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try { // Miércoles, 28 de Setiembre del 2016
            String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
            String[] dia = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("u-dd-MM-yyyy");
            String fecha = sdf.format(date);

            String[] frg = fecha.split("-");

            String format = String.format("%s, %s de %s del %s",
                    dia[DeString.aInteger(frg[0]) - 1], frg[1],
                    mes[DeString.aInteger(frg[2])], frg[3]);

            out.print(format);

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error en tag Hoy", ex);
        }
    }

}
