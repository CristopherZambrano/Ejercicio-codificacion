/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PedidoDao;
import Models.Pedido;

/**
 *
 * @author churri
 */
public class PedidoController {

    /**
     * Variables de clase y locales
     */
    PedidoDao pedao;
    String message;

    /**
     * Constructor de la clase
     */
    public PedidoController() {
        this.message = "";
    }

    /**
     * Ingreso del pedido al sistema
     */
    public String insertarPedido(String estado, String id_usuario, String total) {
        pedao = new PedidoDao();
        Pedido pe = new Pedido();

        this.message = "Error en los parametros de entrada";

        pe.setEstado(estado);
        pe.setId_usuario(id_usuario);
        pe.setTotal(total);

        //validacion de los descuentos
        int descuento = Integer.parseInt(pedao.cantidadVentasPorMes(id_usuario));
        pe.setDescuento(descuento == 0 ? descuento : (descuento > 0 && descuento < 11) ? descuento : 10);

        if (pedao.insertarPedido(pe)) {
            this.message = "procesando...";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

    /**
     * Listado de los pedidos globales
     */
    public String listarPedidos(String id_usuario) {
        pedao = new PedidoDao();
        return pedao.listarPedidos(id_usuario);
    }

    /**
     * Listado de los pedidos cancelados por los usuarios
     */
    public String listarPedidosCancelados(String id_usuario) {
        pedao = new PedidoDao();
        return pedao.listarPedidosCancelados(id_usuario);
    }

    /**
     * Listado de los pedidos en tienda
     */
    public String listarPedidosTienda(String id_usuario) {
        pedao = new PedidoDao();
        Pedido pe = new Pedido();

        pe.setId_usuario(id_usuario);
        return pedao.listarPedidosTienda(pe);
    }

    /**
     * Cancelacion del pedido en el sistema
     */
    public String cancelarPedido(String id_pedido) {
        pedao = new PedidoDao();
        if (pedao.cancelarPedido(id_pedido)) {
            this.message = "Pedido cancelado correctamente";
        } else {
            this.message = "error";
        }
        return this.message;
    }

    /**
     * Entrega del pedido realizado por el usuario
     */
    public String despacharPedido(String id_pedido) {
        pedao = new PedidoDao();
        if (pedao.despacharPedido(id_pedido)) {
            this.message = "Pedido despachado correctamente";
        } else {
            this.message = "error";
        }
        return this.message;
    }

    public String cantidadDescuento(String id_usuario) {
        pedao = new PedidoDao();
        int descuento = Integer.parseInt(pedao.cantidadVentasPorMes(id_usuario));
        int descuento_response = (descuento == 0 ? descuento : (descuento > 0 && descuento < 11) ? descuento : 10);
        return String.valueOf(descuento_response);
    }
}
