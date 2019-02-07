<%-- 
    Document   : index
    Created on : 07-feb-2019, 0:15:23
    Author     : saryc
--%>

<%@page import="modelo.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ComentarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BD Hola Mundo</title>
    </head>

    <body>

        <h1>BD Hola Mundo</h1>
        <br>

        <h2> Envíanos tu Comentario... </h2>

        <form role="form" action="AgregarComentario" method="POST">
            <div>
                <label>Título</label>
                <input name="titulo" placeholder="Título..." maxlength="30" required>
            </div>
            <div>
                <label>Descripción</label>
                <input name="descripcion" placeholder="Descripción..." maxlength="100" required>
            </div>

            <button type="submit">Enviar</button>

        </form>

        <br>
        <h2>Comentarios... </h2>
        <br>

        <div>
            <table id="tablacomentarios">
                <thead>
                    <tr>
                        <th>Título</th>
                        <th>Descripción</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        ComentarioDAO comentariodao = new ComentarioDAO();
                        ArrayList<Comentario> comentarios = comentariodao.getAllComentarios();

                        for (int i = 0; i < comentarios.size(); i++) {

                            String titulo = comentarios.get(i).getTitulo();
                            String descripcion = comentarios.get(i).getDescripcion();
                            
                    %>

                    <tr class="odd gradeA">
                        <td><%=titulo%></td>
                        <td><%=descripcion%></td>
                    </tr>

                    <%
                        }
                    %>

                </tbody>
            </table>
            <br>           

        </div>


    </body>
</html>
