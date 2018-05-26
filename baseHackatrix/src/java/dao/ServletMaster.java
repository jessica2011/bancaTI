/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chamoli
 */
@WebServlet(name = "ServletMaster", urlPatterns = {"/view/ServletMaster"})
public class ServletMaster extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        
        String accion = request.getParameter("accion")==null?"":request.getParameter("accion");
        String datos[] = request.getParameterValues("datos[]");
        
        ResultMaster result = null;
         
        System.out.println("Se pintan los datos de entrada");

        for(int i=0; i < datos.length; i++)
                System.out.println(datos[i]);
        
        //if (datos != null) {
            
            DatosValidator validator = new DatosValidator(request);
            CasosValidator casosValidator = new CasosValidator(request);
            
            switch(accion){
                
                case "ASIGNAR_CASO_CTL":

                    //datos = new String[]{"parametro1", "parametro2"};
                    
                    try {
                        result = casosValidator.asignarCasoCtl(datos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                
                case "CASO_INS_CTL":

                    //datos = new String[]{"parametro1", "parametro2"};
                    
                    try {
                        result = casosValidator.crearCasoCtl(datos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                
                case "CASOS_LIST":

                    //datos = new String[]{"parametro1", "parametro2"};
                    
                    try {
                        result = casosValidator.obtenerCasosLista(datos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                
                case "GET":

                    datos = new String[]{"parametro1"};
                    
                    try {
                        result = validator.obtenerDatosPuntuales(datos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                    
                case "LIST":

                    datos = new String[]{"parametro1", "parametro2"};
                    
                    try {
                        result = validator.obtenerDatosLista(datos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                    
                case "CTL":
                    
                    datos = new String[]{"parametro1", "parametro2"};
                    
                    try {
                        result = validator.actualizarDatosCtl(datos);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                    
                case "CARGAR":

                    Object campos[] = new Object[10];
                    Object campos2[] = new Object[10];

                    result = new ResultMaster();
                    List<Object[]> resultSet = new ArrayList<Object[]>();

                    // Ejemplo con tablas
                    campos[0] = "fila1/valor0";
                    campos[1] = "fila1/valor1";
                    campos[2] = "fila1/valor2";
                    campos[3] = "fila1/valor3";
                    campos[4] = "fila1/valor4";

                    campos2[0] = "fila2/valor0";
                    campos2[1] = "fila2/valor1";
                    campos2[2] = "fila2/valor2";
                    campos2[3] = "fila2/valor3";
                    campos2[4] = "fila2/valor4";

                    resultSet.add(campos);
                    resultSet.add(campos2);

                    result.setCodigoError("000001");
                    result.setTipoError("000012");
                    result.setMensaje("Consulta con Errores a la base de datos");                
                    result.setResultSet(resultSet);

                    String cadenaTabla = new Gson().toJson(result);

                    // Ejemplo con listados de valores
                    ResultMaster result2 = new ResultMaster();
                    List<Object> valores = new ArrayList<Object>();

                    Object valor;

                    valor = "valor1";
                    valores.add(valor);

                    valor = "valor2";
                    valores.add(valor);

                    valor = "valor3";
                    valores.add(valor);

                    result2.setCodigoError("000002");
                    result2.setTipoError("000001");
                    result2.setMensaje("Registro Satisfactorio");
                    //result2.setLista(valores);

                    String cadenaLista = new Gson().toJson(result2);

                    PrintWriter out = response.getWriter();
                    out.print(cadenaTabla);
                    out.close();
                    break;

    //                try (PrintWriter out = response.getWriter()) {
    //                    /* TODO output your page here. You may use following sample code. */
    //                    out.println("<!DOCTYPE html>");
    //                    out.println("<html>");
    //                    out.println("<head>");
    //                    out.println("<title>Servlet ServletMaster</title>");            
    //                    out.println("</head>");
    //                    out.println("<body>");
    //                    out.println("<h1>Pintando resultset</h1>");
    //                    out.println(cadenaTabla);
    //                    out.println("<h1>Pintando lista</h1>");
    //                    out.println(cadenaLista);
    //                    out.println("</body>");
    //                    out.println("</html>");
    //                }
    //            default:
    //                try (PrintWriter out = response.getWriter()) {
    //                    /* TODO output your page here. You may use following sample code. */
    //                    out.println("<!DOCTYPE html>");
    //                    out.println("<html>");
    //                    out.println("<head>");
    //                    out.println("<title>Servlet ServletMaster</title>");            
    //                    out.println("</head>");
    //                    out.println("<body>");
    //                    out.println("<h1>Servlet ServletMaster at " + request.getContextPath() + "</h1>");
    //                    out.println("</body>");
    //                    out.println("</html>");
    //                }
            }
      //  }
        response.getWriter().print(new Gson().toJson(result));
        response.getWriter().close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
