/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import Models.UserSession;
import Models.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class UsuarioController {

    /**
     * Variables de clase y locales
     */
    UsuarioDao usdao;
    String message;

    /**
     * Constructor de la clase
     */
    public UsuarioController() {
        message = "";
    }

    /**
     * Ingresar Usuario al sistema
     */
    public String insertarUsuario(String nombres, String apellidos, String nombre_tienda, String estado, String tipo_usuario, String usuario, String contrasenia) {
        usdao = new UsuarioDao();
        Usuario us = new Usuario();
        this.message = "Error en los parametros de entrada.";
        us.setNombres(nombres);
        us.setApellidos(apellidos);
        us.setNombre_tienda(nombre_tienda);
        us.setEstado(estado);
        us.setTipo_usuario(tipo_usuario);
        us.setUsuario(usuario);
        us.setContrasenia(contrasenia);
        if (usdao.insertarUsuario(us)) {
            this.message = "Usuario registrado correctamente.";
        } else {
            this.message = "Erro al agregar un nuevo usuario.";
        }
        return this.message;
    }

    /**
     * Activacion de un usuario en el sistema
     */
    public String habilitarUsuario(String id_usuario) {
        usdao = new UsuarioDao();
        Usuario us = new Usuario();
        this.message = "Error en los parametros de entrada.";
        us.setId_usuario(id_usuario);
        if (usdao.habilitar(us)) {
            this.message = "Usuario habilitado correctamente";
        } else {
            this.message = "Error al habilitar el usuario";
        }
        return this.message;
    }

    public UserSession login(String usuario, String contrasenia) {
        UserSession usr = null;
        usdao = new UsuarioDao();
        DefaultTableModel employeeresponse = usdao.login(usuario, contrasenia);
        if (employeeresponse.getRowCount() > 0) {
            usr = new UserSession();
            usr.setId_user(employeeresponse.getValueAt(0, 0).toString());
            usr.setNombre(employeeresponse.getValueAt(0, 1).toString());
            usr.setApellido(employeeresponse.getValueAt(0, 2).toString());
            usr.setNombre_usuario(employeeresponse.getValueAt(0, 6).toString());
            usr.setCargo(employeeresponse.getValueAt(0, 5).toString());
            usr.setEstado(employeeresponse.getValueAt(0, 4).toString());
        } else {
            usr = null;
        }
        return usr;
    }

    /**
     * Obtener la lista de usuarios registrados en el sistema
     */
    public String listarUsuarios() {
        usdao = new UsuarioDao();
        return usdao.listarUsuarios();
    }

}
