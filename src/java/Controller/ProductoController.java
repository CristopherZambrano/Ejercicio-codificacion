/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProductoDao;
import Models.Producto;

/**
 *
 * @author churri
 */
public class ProductoController {

    /**
     * Variables de clase y locales
     */
    ProductoDao pdao;
    String message;

    /**
     * Constructor de la clase
     */
    public ProductoController() {
        this.message = "";
    }

    /**
     * Ingreso de producto en el sistema
     */
    public String insertarProducto(String nombre, String stock, String precio_unit, String id_usuario) {
        pdao = new ProductoDao();
        Producto pd = new Producto();
        this.message = "Error en los parametros ingresados";
        pd.setNombre(nombre);
        pd.setCantidad(stock);
        pd.setPrecio(precio_unit);
        pd.setId_usuario(id_usuario);
        if (pdao.insertarProducto(pd)) {
            this.message = "Producto agregado correctamente";
        } else {
            this.message = "Error al agregar producto.";
        }
        return this.message;
    }

    /**
     * Obtener la lista de productos general
     */
    public String listarProductos(String id_usuario) {
        pdao = new ProductoDao();
        Producto pd = new Producto();
        pd.setId_usuario(id_usuario);
        return pdao.listarProductos(pd);
    }

    /**
     * Obtener la lista de productos especifica
     */
    public String listarProductosTienda(String id_usuario) {
        pdao = new ProductoDao();
        Producto pd = new Producto();
        pd.setId_usuario(id_usuario);
        return pdao.listarProductosTienda(pd);
    }

}
