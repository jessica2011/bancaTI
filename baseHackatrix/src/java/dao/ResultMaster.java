/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Chamoli
 */
public class ResultMaster {
    private String codigoError;
    private String tipoError;
    private String mensaje;
    private Object[] lista;
    private List<Object[]> resultSet;

  
    public List<Object[]> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<Object[]> resultSet) {
        this.resultSet = resultSet;
    }

    public Object[] getLista() {
        return lista;
    }

    public void setLista(Object[] lista) {
        this.lista = lista;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    
}
