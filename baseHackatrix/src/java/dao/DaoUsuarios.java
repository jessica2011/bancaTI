package dao;

import domain.Usuarios;
import java.util.List;

public interface DaoUsuarios {

    public Usuarios usuariosGet(Integer idusuario);

    public String usuariosUpd(Usuarios usuarios);

    public List<Object[]> usuariosIds();

    public String getMessage();
}
