/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetallePedidoDao;
import Models.DetallePedido;

/**
 *
 * @author churri
 */
public class DetallePedidoController {

    /**
     * Variables de clase y locales
     */
    DetallePedidoDao detpdao;
    String message;

    /**
     * Constructor de la clase
     */
    public DetallePedidoController() {
        this.message = "";
    }

    /**
     * Ingreso de los detalles del pedido
     *
     * @param id_producto Identificador del producto
     * @param cantidad Cantidad del producto solicitado
     * @param precio Precio del producto solicitado
     * @return
     */
    public String insertarDetallePedido(String id_producto, String cantidad, String precio) {
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setCantidad(cantidad);
        detm.setPrecio(precio);
        detm.setId_producto(id_producto);

        if (detpdao.insertarDetallePedido(detm)) {
            this.message = "Pedido realizado con exito";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

    /**
     * Disminucion del valor inventariado de un producto
     *
     * @param id_producto Identificador del producto
     * @param id_cantidad Cantidad a menorar
     * @return
     */
    public String disminuirStock(String id_producto, String id_cantidad) {
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setId_producto(id_producto);
        detm.setCantidad(id_cantidad);

        if (detpdao.disminuirStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

    /**
     * Adicion del valor inventariado de un producto
     *
     * @param id_producto Identificador del producto
     * @param id_cantidad Cantidad a menorar
     * @return
     */
    public String aumentarStock(String id_producto, String id_cantidad) {
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setId_producto(id_producto);
        detm.setCantidad(id_cantidad);

        if (detpdao.aumentarStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

}
