

<%
    if (session.getAttribute("idusuario") != null) {
%>

<%@page import="br.com.controlgames.model.Plataforma"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciamento de Coleção de Videogames</title>
    </head>
    <body>
        <%@include file="/cabecalho.jsp" %>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menuLogado.jsp">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Regiões</li>
            </ol>
        </nav>
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <button type="button" class="btn btn-outline-warning my-2 mx-1 my-sm-0" data-toggle="modal" data-target="#CadModal" name="cadastrar" id="cadastrar">Cadastrar</button>
            </li>
            <li class="nav-item">
                <a class="btn btn-outline-warning my-2 mx-1 my-sm-0" href="${pageContext.request.contextPath}/menuLogado.jsp"  name="voltar" id="sair">Voltar</a>
            </li>
        </ul>
        <div class="container my-3">
            <table id="tabdados" name="tbusuario" class="table rounded">
                <thead class="thead-dark">
                    <tr>
                        <th colspan="5">Plataforma</th>
                    </tr>
                    <tr>
                        <th>#</th>
                        <th>Nome</th>
                        <th>Situação</th>
                        <th colspan="2">Editar</th>
                    </tr>
                </thead>

                <%
                    List<Plataforma> Plataformas = (List<Plataforma>) request.getAttribute("plataformas");
                    for (Plataforma plataforma : Plataformas) {
                %>
                <tbody>
                    <tr scope="row">
                        <td><%= plataforma.getIdPlataforma()%></td>
                        <td><%= plataforma.getDescricao()%></td>
                        <td><%= plataforma.getSituacao()%></td>
                        <%
                            if (plataforma.getSituacao().equals("A")) {
                        %>
                        <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/PlataformaExcluir?idPlataforma=<%=plataforma.getIdPlataforma()%>">Inativar</a>
                            <%
                            } else {
                            %>
                        <td><a class="btn btn-success" href="${pageContext.request.contextPath}/PlataformaExcluir?idPlataforma=<%=plataforma.getIdPlataforma()%>">Ativar</a>
                            <%
                                }
                            %>
                        <td><a class="btn btn-outline-success" href="${pageContext.request.contextPath}/PlataformaCarregar?idPlataforma=<%=plataforma.getIdPlataforma()%>">Alterar</a>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%@include file="/rodape.jsp" %>
        <div class="modal fade" id="CadModal" tabindex="-1" role="dialog" aria-labelledby="CadModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form name="cadastrarPlataforma" action="${pageContext.request.contextPath}/PlataformaCadastrar" method="POST">
                        <div class="modal-header bg-dark">
                            <h5 class="modal-title text-light" id="exampleModalLabel">Cadastrar</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="descricao" class="col-form-label">Nome</label>
                                <input type="text" class="form-control"  name="descricao" id="descricao" maxlength="50">
                            </div>
                        </div>
                        <div class="modal-footer bg-dark">
                            <input type="reset" class="btn btn-secondary" name="limpar" id="limpar" value="Limpar"/>
                            <input type="submit" class="btn btn-warning" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<%
} else {
%>
<script>
    window.location.replace("/ControlGames/index.jsp");
</script>
<%}%>
