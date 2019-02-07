package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Comentario;

/**
 *
 * @author Sary
 */
public class ComentarioDAO {

    private final Connection connection;

    public ComentarioDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addComentario(Comentario comentario) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into comentario(titulo,descripcion) values (?,?)");
        preparedStatement.setString(1, comentario.getTitulo());
        preparedStatement.setString(2, comentario.getDescripcion());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Comentario> getAllComentarios() throws SQLException {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from comentario");
        while (rs.next()) {
            Comentario comentario = new Comentario();
            comentario.setTitulo(rs.getString("titulo"));
            comentario.setDescripcion(rs.getString("descripcion"));
            comentarios.add(comentario);
        }

        return comentarios;
    }

}

