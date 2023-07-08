/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import modelo.Usuario;

/**
 *
 * @author saduj
 */
public class Ctrl_Usuario {
   //metoodo para logearse
    public boolean loginUsuario(Usuario objeto) {
        boolean respuesta = false;

        Connection cn = Conexion.conectar();
        String sql = "select usuario, contraseña from tb_usuario where usuario = '"+ objeto.getUsuario() +"' and contraseña = '"+ objeto.getContraseña()+"'";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion");
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion");
        }
        return respuesta;
    }
    
    public boolean guardar(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_usuario values(?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getUsuario());
            consulta.setString(5, objeto.getContraseña());
            consulta.setString(6, objeto.getRol());
            consulta.setString(7, objeto.getTelefono());
            consulta.setInt(8, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar el usuario:" + e);
        }
        return respuesta;
    }
//metodo para verificar el cliente existe

    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "select usuario from tb_usuario where usuario = '" + usuario + "';";
        Statement st;

        try {
            Connection cn = conexion.Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el usuario" + e);
        }
        return respuesta;
    }
    
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_usuario set nombre=?, apellido = ?, usuario = ?, contraseña= ?, rol = ?, telefono = ?, estado = ? where idUsuario ='" + idUsuario + "'");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getContraseña());
            consulta.setString(5, objeto.getRol());
            consulta.setString(6, objeto.getTelefono());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
        return respuesta;
    }
    
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("update tb_usuario set estado='0' where idUsuario='" + idUsuario +"'");
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }
        return respuesta;
    }

}
