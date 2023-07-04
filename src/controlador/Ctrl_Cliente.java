/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;

/**
 *
 * @author saduj
 */
public class Ctrl_Cliente {

    //metodo para registrar un nuevo cliente
    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values(?,?,?,?,?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getNombre_cliente());
            consulta.setString(3, objeto.getApellido_cliente());
            consulta.setString(4, objeto.getDni_cliente());
            consulta.setString(5, objeto.getTelefono_cliente());
            consulta.setString(6, objeto.getDireccion_cliente());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar el cliente:" + e);
        }
        return respuesta;
    }
//metodo para verificar el cliente existe

    public boolean existeCliente(String dni) {
        boolean respuesta = false;
        String sql = "select dni_cliente from tb_cliente where dni_cliente = '" + dni + "';";
        Statement st;

        try {
            Connection cn = conexion.Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el cliente" + e);
        }
        return respuesta;
    }
}
