/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Producto;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Sary
 */
public class ProductoDAO {

    private final Connection connection;

    public ProductoDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addProducto(Producto producto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into producto(nombre,precio) values (?,?)");
        preparedStatement.setString(1, producto.getNombre());
        preparedStatement.setFloat(2, producto.getPrecio());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Producto> getAllProductos() throws SQLException {
        ArrayList<Producto> productos = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from producto");
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setNombre(rs.getString("nombre"));
            producto.setPrecio(rs.getFloat("precio"));
            productos.add(producto);
        }

        Collections.sort(productos, new Comparator() {
            public int compare(Producto obj1, Producto obj2) {
                return obj1.getNombre().toLowerCase().compareTo(obj2.getNombre().toLowerCase());
            }

            @Override
            public int compare(Object o1, Object o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        return productos;
    }

    public void deleteProducto(String nombre) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from producto where nombre=?");
        preparedStatement.setString(1, nombre);
        preparedStatement.executeUpdate();
    }

    public void updateProducto(String opcion, Producto producto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update producto set nombre=?,precio=?" + " where nombre=?");
        preparedStatement.setString(1, producto.getNombre());
        preparedStatement.setFloat(2, producto.getPrecio());
        preparedStatement.setString(3, opcion);
        preparedStatement.executeUpdate();
    }

    public Producto getProductoById(String nombre) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from producto where nombre=" + "\'" + nombre + "\'");
        Producto producto = new Producto();
        if (rs.next()) {
            producto.setNombre(rs.getString("nombre"));
            producto.setPrecio(rs.getFloat("precio"));
        }
        return producto;
    }

}
