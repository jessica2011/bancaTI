package dao;

import hackatrix.sql.ConectaDb;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DaoMaster  {

    private final ConectaDb db;

    public DaoMaster() {
        this.db = new ConectaDb();
    }
    
    public DaoMaster(ConectaDb _db) {
        this.db = _db;
    }

    //@Override
    public ResultMaster get(String storedProcedure, String tipos[], int cantOutParms, String values[]) throws SQLException {

        ResultMaster resultado = new ResultMaster();
        
        Object[] f = null;
        

        f = new Object[cantOutParms];

        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<(tipos.length+cantOutParms); i++){
            sb.append("?");
            if((i+1)<(tipos.length+cantOutParms)){
                sb.append(",");
            }
        }
        
        String sql01 = "{call " + storedProcedure + "  (" + sb.toString() + ") }";
        
        System.out.println("sql01 " + sql01);
            
        Connection cn = db.getConnection(0);
        
        if (cn != null) {
            try {
                CallableStatement cs = cn.prepareCall(sql01);
                
                int i=0;
                int j=0;
                
                for(i=0; i<tipos.length; i++)
                    cs.setString(i+1, values[i]);
                
                int posOutParm = i;
                
                for(j=0; j<cantOutParms;j++, i++){
                    cs.registerOutParameter(i+1, java.sql.Types.VARCHAR);
                }

                cs.execute();

                // Los 3 primeros campos son los de error
                for(i=posOutParm, j=0;j<cantOutParms;i++,j++){
                    f[j] = cs.getString(i+1);
                }
                
//                f[0] = cs.getString(2); // codigoEntidad
//                f[1] = cs.getString(3); // codigoEstadoOperacion
//                f[2] = cs.getString(4); // codigoTipoProducto
                cs.close();

            } catch (SQLException e) {
                resultado.setCodigoError("000001");
                resultado.setTipoError("000001");
                resultado.setMensaje(e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    resultado.setCodigoError("000001");
                    resultado.setTipoError("000001");
                    resultado.setMensaje(e.getMessage());
                }
            }

        }

        resultado.setLista(f);
        return resultado;
    }

    
    public ResultMaster list(String storedProcedure, String tipos[], String values[]) throws SQLException {
        ResultMaster resultado = new ResultMaster();
        PreparedStatement ps = null;

        Connection cn = db.getConnection(0);
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<(tipos.length); i++){
            sb.append("?");
            if((i+1)<(tipos.length)){
                sb.append(",");
            }
        }
        
        String sql01 = "{call " + storedProcedure + "  (" + sb.toString() + ") }";
        
        System.out.println("sql01 " + sql01);
        
        
        if (cn != null) {
            try {

                ps = cn.prepareStatement(sql01);
//                ps.setString(1, sCodigoOperacion);
                
                for(int i=0; i<tipos.length; i++)
                    ps.setString(i+1, values[i]);

                ResultSet rs = ps.executeQuery();

                List<Object[]> list = new LinkedList<>();

                while (rs.next()) {
                    int cantCampos = rs.getMetaData().getColumnCount();
                    Object[] f = new Object[cantCampos];
                    for(int i=0;i<cantCampos;i++){
                        f[i] = rs.getObject(i+1); 
                    }
                    
                    list.add(f);
                }

                resultado.setResultSet(list);

            } catch (SQLException e) {
                resultado.setCodigoError("000002");
                resultado.setMensaje(e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    resultado.setCodigoError("000002");
                    resultado.setMensaje(e.getMessage());
                }
            }

        }
        return resultado;
    }

    // cantOutParms: Cantidad total de parámetros
    public ResultMaster ctl(String storedProcedure, String tipos[], int cantOutParms, String values[]) throws SQLException {

        ResultMaster resultado = new ResultMaster();
        
        Object[] f = null;

        f = new Object[cantOutParms];
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<(tipos.length+cantOutParms); i++){
            sb.append("?");
            if((i+1)<(tipos.length+cantOutParms)){
                sb.append(",");
            }
        }
        
        String sql01 = "{call " + storedProcedure + "  (" + sb.toString() + ") }";

        Connection cn = db.getConnection(0);
        
        cn.setAutoCommit(false);
        
        if (cn != null) {
            try {
                int i=0, j=0;
                CallableStatement ps = cn.prepareCall(sql01);

                for(i=0; i<values.length; i++){
                    ps.setString(i+1, values[i]);
                }
                
                int posOutParm = i;

                for(j=0; j<cantOutParms;j++, i++){
                    ps.registerOutParameter(i+1, java.sql.Types.VARCHAR);
                }
                
                ps.execute();

                // Los 3 primeros campos output son los de error
                for(i=posOutParm, j=0;j<cantOutParms;i++,j++){
                    f[j] = ps.getString(i+1);
                }
                
                if (f[0].toString().equals("000001")){
                    cn.rollback();
                }
                
                //codigo ejemplo
//                f[0] = "000002";
//                f[1] = "000001";
//                f[2] = "Resultado Satisfactorio";
//                f[3] = "mensaje de salida";

            } catch (SQLException e) {

                f[0] = "000001";
                f[2] = e.getMessage();
                //cn.rollback();
                
            } finally {
                try {
                    cn.commit();
                    cn.close();
                } catch (SQLException e) {
                    f[0] = "000001";
                    f[2] = e.getMessage();
                }
            }
        }
        
        resultado.setCodigoError(f[0].toString());
        resultado.setTipoError(f[1].toString());
        resultado.setMensaje(f[2].toString());
        
        if (f.length > 3){
            
            Object[] lista = new Object[f.length-3];
        
            for(int i=0; i<f.length-3; i++){
                lista[i] = f[i+3];
            }
            
            resultado.setLista(lista);
        }

        return resultado;

    }

//    @Override
//    public Resultado GetInteresCorrido(String fechaInicio, String fechaFin, Double nMonto, Double nTasa) {
//        Resultado resultado = new Resultado();
//
//        String sql = "{call spSafJOperObtieneMontoInteresesLFGet  (?, ?, ?, ?, ?, ?) }";
//
//        Object[] arreglo = new Object[2];
//        Connection cn = db.getConnection();
//
//        if (cn != null) {
//            try {
//
//                CallableStatement ps = cn.prepareCall(sql);
//
//                ps.setString(1, fechaInicio);
//                ps.setString(2, fechaFin);
//                ps.setDouble(3, nMonto);
//                ps.setDouble(4, nTasa);
//
//                ps.registerOutParameter(5, java.sql.Types.INTEGER); //dias Corridos
//                ps.registerOutParameter(6, java.sql.Types.DOUBLE); //Monto Interes Corrido
//
//                ps.execute();
//
//                arreglo[0] = ps.getInt(5); // dias Corridos
//                arreglo[1] = ps.getDouble(6); // Monto Interes Corrido
//
//            } catch (SQLException e) {
//
//                arreglo[0] = "000001";
//                arreglo[2] = e.getMessage();
//
//            } finally {
//                try {
//                    cn.close();
//                } catch (SQLException e) {
//                    arreglo[0] = "000001";
//                    arreglo[2] = e.getMessage();
//                }
//            }
//        }
//        resultado.setObject(arreglo);
//        return resultado;
//    }

}
