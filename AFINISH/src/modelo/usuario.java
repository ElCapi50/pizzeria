package modelo;

import control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class usuario {
    
    public int ID;
    public int rol;
    private String nombre;
    private String numero;
    private String password;
    private String direccion1;
    private String direccion2;
    private String direccion3;
    private String referencia1;
    private String referencia2;
    private String referencia3;

    public usuario(String nombre,String numero,String password) {
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "SELECT * FROM clientes WHERE telefono = " +numero;
        
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while ( rs.next() ) {
                
                this.ID = rs.getInt("idCliente");
                this.rol = rs.getInt("rol");
                this.nombre = rs.getString("nombre");
                this.numero = rs.getString("telefono");
                this.password = rs.getString("password");
                this.direccion1 = rs.getString("dir1");
                this.direccion2 = rs.getString("dir2");
                this.direccion3 = rs.getString("dir3");
                this.referencia1 = rs.getString("ref1");
                this.referencia2 = rs.getString("ref2");
                this.referencia3 = rs.getString("ref3");
                
            }
            
            if ( this.nombre.equals(nombre) && this.numero.equals(numero) && this.password.equals(password)) {
                
                conexion hc = new conexion();
                hc.inicioConexion(this.ID);
                
            } else {
                
                this.ID = -1;
                this.rol = -1;
                this.nombre = "";
                this.numero = "";
                this.password = "";
                this.direccion1 = "";
                this.direccion2 = "";
                this.direccion3 = "";
                this.referencia1 = "";
                this.referencia2 = "";
                this.referencia3 = "";
                
                JOptionPane.showMessageDialog(null, "LOS DATOS INGRESADOS SON INCORRECTOS");
            }
        } catch ( Exception e ) {
            
        }
    }
    
    public int register(int rol,String nombre,String numero,String password,String dir1,String dir2,String dir3,String ref1,String ref2,String ref3) {
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "INSERT INTO clientes (rol, nombre, telefono, password, dir1, dir2, dir3, ref1, ref2, ref3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(sSQL);
            
            pst.setInt(1, rol);
            pst.setString(2, nombre);
            pst.setString(3, numero);
            pst.setString(4, password);
            pst.setString(5, dir1);
            pst.setString(6, dir2);
            pst.setString(7, dir3);
            pst.setString(8, ref1);
            pst.setString(9, ref2);
            pst.setString(10, ref3);
            
            int n = pst.executeUpdate();
            
            if ( n > 0 ) {
                return 1;
            }
            return 0;
        } catch ( Exception e ) {
            return 0;
        }
    }

    public int update(String nombre, String numero, String password, String dir1, String dir2, String dir3, String ref1, String ref2, String ref3) {
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "UPDATE clientes SET WHERE idCliente = " +this.ID;
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(sSQL);
            
            pst.setString(2, nombre);
            pst.setString(3, numero);
            pst.setString(4, password);
            pst.setString(5, dir1);
            pst.setString(6, dir2);
            pst.setString(7, dir3);
            pst.setString(8, ref1);
            pst.setString(9, ref2);
            pst.setString(10, ref3);
            
            int n = pst.executeUpdate();
            
            if ( n > 0 ) {
                return 1;
            } else {
                return 0;
            }
        } catch ( Exception e ) {
            return 0;
        }
    }
    
    public int erase() {
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "DELETE * FROM clientes WHERE idCliente = " +this.ID;
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(sSQL); 
               
                int m = pst.executeUpdate();
                 if (m > 0) {
                     return 1;
                 } else {
                     return 0;
                 }
                 
        } catch ( Exception e ) {
            return 0;
        }
    }
    
    public DefaultTableModel cargarTabla(String nombreTabla) {
        
        String[] titulos;
        String[] registros;
        int flager = 0;
        
        if ( nombreTabla.equals("clientes") ) {
            String[] refill = {"ID", "nombre", "numero Telefono", "password", "dir1", "dir2", "dir3", "ref1", "ref2", "ref3" };
            titulos = refill;
            registros = new String[11];
            flager = 1;
        } else {
            if ( nombreTabla.equals("conexiones") ) {
                String[] refill = {"ID", "Usuario", "inicio", "duracion", "finalizacion"};
                titulos = refill;
                registros = new String[4];
                flager = 2;
            } else {
                if ( nombreTabla.equals("ordenes") ) {
                    String[] refill = {"idOrden", "cliente", "paquete", "especialidad", "masa1", "masa2", "masa3", "ingrediente1P1", "ingrediente2P1", "ingrediente3P1", "ingrediente4P1", "ingrediente1P2", "ingrediente2P2", "ingrediente3P2", "ingrediente4P2", "ingrediente1P3", "ingrediente2P3", "ingrediente3P3", "ingrediente4P3", "Bebida", "Extra", "codigo barras"};
                    titulos = refill;
                    registros = new String[22];
                    flager = 3;
                } else {
                    String[] refill = {};
                    titulos = refill;
                    registros = new String[0];
                    flager = 0;
                }
            }
        }
        
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        String sSQL = "";
        
        conexion sql = new conexion();
        Connection conn = sql.conexion();
        
        sSQL = "SELECT * FROM " +nombreTabla;
        
        
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            if ( flager == 1 ) {
                
                while ( rs.next() ) {
                    
                    registros[0] = rs.getString("idCliente");
                    registros[1] = rs.getString("rol");
                    registros[2] = rs.getString("nombre");
                    registros[3] = rs.getString("telefono");
                    registros[4] = rs.getString("password");
                    registros[5] = rs.getString("dir1");
                    registros[6] = rs.getString("dir2");
                    registros[7] = rs.getString("dir3");
                    registros[8] = rs.getString("ref1");
                    registros[9] = rs.getString("ref2");
                    registros[10] = rs.getString("ref3");
                    
                    modelo.addRow(registros);
                }
            } else {
                if ( flager == 2 ) {
                   
                    while ( rs.next() ) {
                        registros[0] = rs.getString("idConexion");
                        registros[1] = rs.getString("usuarioConectado");
                        registros[2] = rs.getString("inicioConexion");
                        registros[3] = rs.getTime("duracionConexion").toString();
                        
                        modelo.addRow(registros);
                    }
                } else {
                    if ( flager == 3 ) {
                        
                        while ( rs.next() ) {
                            
                            registros[0] = rs.getString("idOrden");
                            registros[1] = rs.getString("comprador");
                            registros[2] = rs.getString("paquete");
                            registros[3] = rs.getString("especialidad");
                            
                            registros[4] = rs.getString("masa1");
                            registros[5] = rs.getString("masa2");
                            registros[6] = rs.getString("masa3");
                            
                            registros[7] = rs.getString("ingrediente1P1");
                            registros[8] = rs.getString("ingrediente2P1");
                            registros[9] = rs.getString("ingrediente3P1");
                            registros[10] = rs.getString("ingrediente4P1");
                            
                            registros[11] = rs.getString("ingrediente1P2");
                            registros[12] = rs.getString("ingrediente2P2");
                            registros[13] = rs.getString("ingrediente3P2");
                            registros[14] = rs.getString("ingrediente4P2");
                            
                            registros[15] = rs.getString("ingrediente1P3");
                            registros[16] = rs.getString("ingrediente2P3");
                            registros[17] = rs.getString("ingrediente3P3");
                            registros[18] = rs.getString("ingrediente4P3");
                            
                            registros[19] = rs.getString("bebida");
                            registros[20] = rs.getString("extra");
                            registros[21] = rs.getString("codigoBarra");
                            
                            modelo.addRow(registros);
                        }
                    }
                }
            }
        } catch ( Exception e ) {
            
        }
        return modelo;
    }
}

/*

void consultarTabla(String valor) {
        String[] titulos = {"ID", "nombre", "apellido", "genero", "medidas", "prueba covid", "Sentir en el encierro", "Que ha hecho para combatir la depresion", "Afectacion a su comunidad"};
        String[] registros = new String[9];
        modelo = new DefaultTableModel(null, titulos);
        
        conexion mysql = new conexion();
        Connection conn = mysql.conexion();
        
        String sSQL = "";
        sSQL = "SELECT idPersona, nombre, apellido, genero, medidas, prueba, encierro, deprimir, afectar FROM covidZunne WHERE CONCAT(nombre, ' ', apellido) LIKE '%" +valor +"%'";
        
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while (rs.next()) {
                
                registros[0] = rs.getString("idPersona");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido");
                registros[3] = rs.getString("genero");
                registros[4] = rs.getString("medidas");
                registros[5] = rs.getString("prueba");
                registros[6] = rs.getString("encierro");
                registros[7] = rs.getString("deprimir");
                registros[8] = rs.getString("afectar");
                modelo.addRow(registros);
            }
            
            jTable1.setModel(modelo);
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargar tabla " +"\n" +ex);
        }
    }

*/