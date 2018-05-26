package hackatrix.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Combo {

    private final ConectaDb db;
    private String message;

    public Combo(ConectaDb db) {
        this.db = db;
    }

    public List<Object[]> combo(String sql) {
        List<Object[]> list = null;

        try (Connection cn = db.getConnection(Db.DATA03); // tercera conexion
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Object[] fil = new Object[2];

                fil[0] = rs.getObject(1);
                fil[1] = rs.getObject(2);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    public String getMessage() {
        return message;
    }
}
