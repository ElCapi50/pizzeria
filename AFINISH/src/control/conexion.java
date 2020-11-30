package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class conexion {
    
    private final String db = "aFINISH";
    private final String url = "jdbc:mysql://localhost:3306/" +db +"?characterEncoding=latin1&useConfigs=maxPerformance";
    private final String user = "root";
    private final String password = "password";
    
    public conexion() {
        
    }
    
    public Connection conexion() {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.password);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error public connection conexion"  +"\n" +e);
        }
        return link;
    } 
    
    public int inicioConexion(int ID) {
        
        Date currentDate = new Date();
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String hola = timeFormat.format(currentDate);
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "INSERT INTO conexiones (usuarioConectado, inicioConexion) VALUES (?,?)";
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(sSQL);
            
            pst.setInt(1, ID);
            pst.setString(2, hola);
            
            int n = pst.executeUpdate();
            
            if ( n > 0 ) {
                
            }
            
            return 1;
            
        } catch ( Exception e ) {
            
            return 0;
            
        }
    }
}