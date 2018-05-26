package dao;

import domain.Usuarios;

public interface DaoAutentica {

    public Usuarios autentica(Usuarios usuarios);

    public String getMessage();
}
