/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import hackatrix.sql.ConectaDb;

/**
 *
 * @author Chamoli
 */
public class DatosValidator {
    
    HttpServletRequest request;
            
    public DatosValidator(HttpServletRequest request){
        this.request = request;
    }

    public ResultMaster obtenerDatosPuntuales(String datos[]) throws SQLException {
        
        DaoMaster d = new DaoMaster(new ConectaDb(request.getSession(), 0));
        ResultMaster result;
        
        String storedProcedure = "spASEObtenerDatosPuntualesGet";
        String tipos[] = {"String"};
        
        // Si es necesario se crea un nuevo array con la informacion
        // de los parametros que pasaran al sp de la base de datos
        
        result = d.get(storedProcedure, tipos, 1, datos);
        
        return result;
    }
    
    public ResultMaster obtenerDatosLista(String datos[]) throws SQLException {
        
        DaoMaster d = new DaoMaster(new ConectaDb(request.getSession(), 0));
        ResultMaster result;
        
        String storedProcedure = "spASEObtenerDatosList";
        String tipos[] = {"String","String"};
        
        // Si es necesario se crea un nuevo array con la informacion
        // de los parametros que pasaran al sp de la base de datos
        
        result = d.list(storedProcedure, tipos, datos);
        
        return result;
    }
    
    public ResultMaster actualizarDatosCtl(String datos[]) throws SQLException {
        
        DaoMaster d = new DaoMaster(new ConectaDb(request.getSession(), 0));
        ResultMaster result;
        
        String storedProcedure = "spASEActualizarDatosCtl";
        String tipos[] = {"String","String"};
        
        // Si es necesario se crea un nuevo array con la informacion
        // de los parametros que pasaran al sp de la base de datos
        
        result = d.ctl(storedProcedure, tipos, 4, datos);
        
        return result;
    }
}
