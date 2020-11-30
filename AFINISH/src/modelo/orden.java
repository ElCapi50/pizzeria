package modelo;

import control.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class orden {
    
    public int idOrden;
    
    public int comprador;
    private String paquete;
    private String especialidad;
    private String masa;
    
    private String ingrediente1P1;
    private String ingrediente2P1;
    private String ingrediente3P1;
    private String ingrediente4P1;
    
    private String ingrediente1P2;
    private String ingrediente2P2;
    private String ingrediente3P2;
    private String ingrediente4P2;
    
    private String ingrediente1P3;
    private String ingrediente2P3;
    private String ingrediente3P3;
    private String ingrediente4P3;
    
    private String bebida;
    private String extra;
    private String codigoBarra;

    public orden(int idOrden, int comprador, String paquete, String especialidad, String masa, String ingrediente1P1, String ingrediente2P1, String ingrediente3P1, String ingrediente4P1, String ingrediente1P2, String ingrediente2P2, String ingrediente3P2, String ingrediente4P2, String ingrediente1P3, String ingrediente2P3, String ingrediente3P3, String ingrediente4P3, String bebida, String extra, String codigoBarra) {
        
        this.idOrden = idOrden;
        
        this.comprador = comprador;
        this.paquete = paquete;
        this.especialidad = especialidad;
        this.masa = masa;
        
        this.ingrediente1P1 = ingrediente1P1;
        this.ingrediente2P1 = ingrediente2P1;
        this.ingrediente3P1 = ingrediente3P1;
        this.ingrediente4P1 = ingrediente4P1;
        
        this.ingrediente1P2 = ingrediente1P2;
        this.ingrediente2P2 = ingrediente2P2;
        this.ingrediente3P2 = ingrediente3P2;
        this.ingrediente4P2 = ingrediente4P2;
        
        this.ingrediente1P3 = ingrediente1P3;
        this.ingrediente2P3 = ingrediente2P3;
        this.ingrediente3P3 = ingrediente3P3;
        this.ingrediente4P3 = ingrediente4P3;
        
        this.bebida = bebida;
        this.extra = extra;
        this.codigoBarra = codigoBarra;
    }
    
    public int pedir(int accionsi) {
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "INSERT INTO clientes () VALUES ()";
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(sSQL);
            
            pst.setInt(1, this.comprador);
            pst.setString(2, this.paquete);
            pst.setString(3, this.especialidad);
            pst.setString(4, this.masa);
            
            pst.setString(5, this.ingrediente1P1);
            pst.setString(6, this.ingrediente2P1);
            pst.setString(7, this.ingrediente3P1);
            pst.setString(8, this.ingrediente4P1);
            
            pst.setString(9, this.ingrediente1P2);
            pst.setString(10, this.ingrediente2P2);
            pst.setString(11, this.ingrediente3P2);
            pst.setString(12, this.ingrediente4P2);
            
            pst.setString(13, this.ingrediente1P3);
            pst.setString(141, this.ingrediente2P3);
            pst.setString(15, this.ingrediente3P3);
            pst.setString(16, this.ingrediente4P3);
            
            pst.setString(17, this.bebida);
            pst.setString(18, this.extra);
            pst.setString(19, this.codigoBarra);
            
            int n = pst.executeUpdate();
            
            if ( n > 0 ) {
                return 1;
            }
            return 0;
        } catch ( Exception e ) {
            return 0;
        }
    }
}
