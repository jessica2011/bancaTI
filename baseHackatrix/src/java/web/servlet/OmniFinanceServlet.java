package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hackatrix.json.JSon;
import hackatrix.sql.ConectaDb;
import hackatrix.sql.Db;

@WebServlet(name = "OmniFinanceServlet", urlPatterns = {"/OmniFinanceServlet"})
public class OmniFinanceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String result = JSon.empty();
        
        String accion = (String) request.getParameter("accion");
        
        if (accion.equals("login")){
            String usuario = (String) request.getParameter("usuario");
            String password = (String) request.getParameter("password");
            
            result = "[{\"resultado\": \"OK\", \"nombre\": \"Anais Olivares\", \"token\":\"BGD2334FGFVF\"}]";
        }
        if (accion.equals("listarBancos")){
            String usuario = (String) request.getParameter("usuario");
            
            result = "[{ \"codigoBanco\": 2,\"nombreBanco\": \"Banco de Crédito del Perú\" }, "
                         + "{\"codigoBanco\": 3, \"nombreBanco\": \"Interbank\"}," 
                         + "{\"codigoBanco\": 12, \"nombreBanco\": \"Scotiabank\",}," 
                         + "{\"codigoBanco\": 11, \"nombreBanco\": \"BBVA Banco Continental\" },"
                         + "{\"codigoBanco\": 7, \"nombreBanco\": \"Citibank\"}";

        }else if (accion.equals("validarBanco")){
            String userId = (String) request.getParameter("userId");
            String clave = (String) request.getParameter("clave");
            String capcha = (String) request.getParameter("capcha");
            
            
        }else if (accion.equals("obtenerProductos")){
            String usuario = (String) request.getParameter("usuario");
            String token = (String) request.getParameter("token");
            
            result = "[" +
                "{"+
                  "\"FIELD1\": 2,"+
                  "\"FIELD2\": \"Banco de Crédito del Perú\","+
                  "\"FIELD3\": \"000212-358858585885-85500\","+
                  "\"FIELD4\": \"Soles\","+
                  "\"FIELD5\": 300"+
                "},"+
                "{"+
                "  \"FIELD1\": 2,"+
                "  \"FIELD2\": \"Banco de Crédito del Perú\","+
                "  \"FIELD3\": \"000212-358858585885-85400\","+
                "  \"FIELD4\": \"Dolares\","+
                "  \"FIELD5\": 200"+
                "},"+
                "{"+
                "  \"FIELD1\": 3,"+
                "  \"FIELD2\": \"Interbank\","+
                "  \"FIELD3\": \"000300-3588585-85-85400\","+
                "  \"FIELD4\": \"Soles\","+
                "  \"FIELD5\": 400"+
                "},"+
                "{"+
                "  \"FIELD1\": 3,"+
                "  \"FIELD2\": \"Interbank\","+
                "  \"FIELD3\": \"000300-3588585-85-85401\","+
                "  \"FIELD4\": \"Dolares\","+
                "  \"FIELD5\": 500"+
                "},"+
                "{"+
                "  \"FIELD1\": 3,"+
                "  \"FIELD2\": \"Interbank\","+
                "  \"FIELD3\": \"000300-3588585-85-84401\","+
                "  \"FIELD4\": \"Dolares\","+
                "  \"FIELD5\": 1400"+
                "}"+
               "]";
            
        }else if (accion.equals("registrarProductoAsociado")){
            String usuario = (String) request.getParameter("usuario");
            String token = (String) request.getParameter("token");
            String producto = (String) request.getParameter("producto");
            
            result = "[{\"resultado\": \"OK\"}]";

        }else if (accion.equals("listarProductosAsociados")){
            String usuario = (String) request.getParameter("usuario");
            
            result = "[" +
                        "{" +
                        " \"codigoBanco\": 2," +
                        " \"nombreBanco\": \"Banco de Crédito del Perú\"," +
                        "  \"tipo\": \"Credito Vehicular\"," +
                        "  \"moneda\": \"Dólares\"," +
                        "  \"saldo\": 40000," +
                        "  \"cuotasPendientes\": 12" +
                        "}," +
                        "{" +
                        "  \"codigoBanco\": 2," +
                        "  \"nombreBanco\": \"Banco de Crédito del Perú\"," +
                        "  \"tipo\": \"Credito Efectivo\"," +
                        "  \"moneda\": \"Soles\"," +
                        "  \"saldo\": 1000," +
                        "  \"cuotasPendientes\": 4" +
                        "}," +
                        "{" +
                        "  \"codigoBanco\": 3," +
                        "  \"nombreBanco\": \"Interbank\"," +
                        "  \"tipo\": \"Credito Hipotecario\"," +
                        "  \"moneda\": \"Soles\"," +
                        "  \"saldo\": 300000," +
                        "  \"cuotasPendientes\": 40" +
                        "}," +
                        "{" +
                        "  \"codigoBanco\": 3," +
                        "  \"nombreBanco\": \"Interbank\"," +
                        "  \"tipo\": \"Credito Efectivo\"," +
                        "  \"moneda\": \"Soles\"," +
                        "  \"saldo\": 1000," +
                        "  \"cuotasPendientes\": 3" +
                        "}," +
                        "{" +
                        "  \"codigoBanco\": 3," +
                        "  \"nombreBanco\": \"Interbank\"," +
                        "  \"tipo\": \"Credito Efectivo\"," +
                        "  \"moneda\": \"Dólares\"," +
                        "  \"saldo\": 400," +
                        "  \"cuotasPendientes\": 1" +
                        "}" +
                       "]";
            
        }else if (accion.equals("pagoPrestamo")){
            String usuario = (String) request.getParameter("usuario");
            String banco1 = (String) request.getParameter("banco1");
            String producto1 = (String) request.getParameter("producto1");
            String monto1 = (String) request.getParameter("monto1");
            String banco2 = (String) request.getParameter("banco2");
            String producto2 = (String) request.getParameter("producto2");
            String monto2 = (String) request.getParameter("monto2");
            
            result = "[{\"resultado\": \"OK\"}]";
            
        }else if (accion.equals("pagoTarjeta")){
            
            String token = (String) request.getParameter("token");
            String usuario = (String) request.getParameter("usuario");
            String producto = (String) request.getParameter("producto");
            
            result = "[{\"resultado\": \"OK\", \"mensaje\":\"Tarjeta " + producto + " Pagada correctamente\"}]";
        }else if (accion.equals("pagoServicio")){
            String token = (String) request.getParameter("token");
            String usuario = (String) request.getParameter("usuario");
            String producto = (String) request.getParameter("Servicio");
            String monto = (String) request.getParameter("monto");
            
            result = "[{\"resultado\": \"OK\", \"mensaje\":\"Servicio Pagado correctamente\"}]";
        }
        /*
        Usuarios user = new Usuarios();
        AutenticaValidator validator = new AutenticaValidator(request);
        List<String> message = validator.valida(user);

        if (message.isEmpty()) {
            //ConectaDb db = new ConectaDb(request.getSession());
            ConectaDb db = new ConectaDb(request.getSession(), Db.DATA01);
            //
            DaoAutentica daoAutentica = new DaoAutenticaImpl(db);
            user = daoAutentica.autentica(user);

            if (user != null) {

                request.getSession().setAttribute("usuarios", user);
                final String ID = request.getSession().getId();
                request.getSession().setAttribute("ID", ID);
                request.getSession().setAttribute("perfil", user.getPerfil());

            } else {
                result = JSon.forMsg(daoAutentica.getMessage());
            }

        } else {
            result = JSon.forMsg(message);
        }
        
        */
        
        
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(result);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="doGet y doPost">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
