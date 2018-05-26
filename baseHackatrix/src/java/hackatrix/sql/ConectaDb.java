package hackatrix.sql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.http.HttpSession;
//import parainfo.aes.AdvEncSta;

public class ConectaDb {

    private HttpSession session;
    private final int NUM_CONN = 3;
    private DataConnection[] dataConnection = new DataConnection[NUM_CONN];
    
    public ConectaDb(){
        
    }

    public ConectaDb(HttpSession session, int numConnection) { // 0, 1, 2
        this.session = session;
        dataConnection[numConnection] = (DataConnection) this.session.getAttribute("dataConnection" + numConnection);

        if (dataConnection[numConnection] == null) {
            Properties props = new Properties();
            InputStream in;

            try {
                in = getClass().getClassLoader().getResourceAsStream("config/database.properties");
                props.load(in);
                in.close();

                //AdvEncSta aes = new AdvEncSta();
                dataConnection[numConnection] = new DataConnection();
                dataConnection[numConnection].setDriver(props.getProperty("jdbc.driver" + numConnection));

                dataConnection[numConnection].setUrl(props.getProperty("jdbc.url" + numConnection));
                //dataConnection[numConnection].setUser(aes.decrypt(props.getProperty("jdbc.user" + numConnection)));
                dataConnection[numConnection].setUser(props.getProperty("jdbc.user" + numConnection));
                //dataConnection[numConnection].setPassword(aes.decrypt(props.getProperty("jdbc.password" + numConnection)));
                dataConnection[numConnection].setPassword(props.getProperty("jdbc.password" + numConnection));
                
                session.setAttribute("dataConnection" + numConnection, dataConnection[numConnection]);

            } catch (IOException ex) {
                dataConnection[numConnection] = null;
            }
        }
    }

    public Connection getConnection(int numConnection) throws SQLException { // 0, 1, 2
        Connection cn = null;
        dataConnection[numConnection] = (DataConnection) session.getAttribute("dataConnection" + numConnection);

        if (dataConnection[numConnection] != null) {
            try {
                Class.forName(dataConnection[numConnection].getDriver()).newInstance();
                cn = DriverManager.getConnection(
                        dataConnection[numConnection].getUrl(), 
                        dataConnection[numConnection].getUser(), 
                        dataConnection[numConnection].getPassword());

            } catch (ClassNotFoundException | NullPointerException
                    | InstantiationException | IllegalAccessException ex) {

                throw new SQLException(ex.getMessage());
            }
        }

        return cn;
    }
}
