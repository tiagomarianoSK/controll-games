<%
    if (session.getAttribute("idusuario") != null) {
%>
<%@page import="br.com.controlgames.model.Amigo"%>
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
                <li class="breadcrumb-item"><a href="${pageContextPath}/menuLogado.jsp">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Amigos</li>
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
            <table id="tabdados" name="tbamigo" class="table">
                <thead class="thead-dark">
                    <tr>
                        <th colspan="7" >Amigos</th>
                    </tr>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Observacao</th>
                        <th scope="col">CPF</th>
                        <th scope="col">Situação</th>
                        <th scope="col" colspan="2">Editar</th>
                    </tr>
                </thead>

                <%
                    List<Amigo> amigos = (List<Amigo>) request.getAttribute("amigos");
                    for (Amigo amigo : amigos) {
                %>
                <tbody>
                    <tr scope="row">
                        <td><%= amigo.getIdAmigo()%></td>
                        <td><%= amigo.getNome()%></td>
                        <td><%= amigo.getObservacao()%></td>
                        <td><%= amigo.getCpf()%></td>
                        <td><%= amigo.getSituacao()%></td>
                        <%
                            if (amigo.getSituacao().equals("A")) {
                        %>
                        <td><a class="btn btn-danger"  href="${pageContext.request.contextPath}/AmigoExcluir?idAmigo=<%=amigo.getIdAmigo()%>">Inativar</a>
                            <%
                            } else {
                            %>
                        <td><a class="btn btn-success"  href="${pageContext.request.contextPath}/AmigoExcluir?idAmigo=<%=amigo.getIdAmigo()%>">Ativar</a>
                            <%
                                }
                            %>
                        <td><a class="btn btn-outline-success" href="${pageContext.request.contextPath}/AmigoCarregar?idAmigo=<%=amigo.getIdAmigo()%>">Alterar</a>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%@include file="/rodape.jsp" %>
</html>
<!-- Modal -->
<div class="modal fade" id="CadModal" tabindex="-1" role="dialog" aria-labelledby="CadModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form name="cadastrarComprador" action="${pageContext.request.contextPath}/AmigoCadastrar" method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Cadastrar</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="cpfCnpjPessoa">CPF</label>
                        <input type="text" class="form-control" name="cpfCnpjPessoa" id="cpfCnpjPessoa" maxlength="50">
                    </div>
                    <div class="form-group">
                        <label for="nomePessoa">Nome</label>
                        <input type="text" class="form-control" name="nomePessoa" id="nomePessoa" maxlength="50">
                    </div>
                    <div class="form-group">
                        <label for="observacao">Observação</label>
                        <input type="text" class="form-control" name="observacao" id="observacao" maxlength="10">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="reset" class="btn btn-secondary" name="limpar" id="limpar" value="Limpar"/>
                    <input type="submit" class="btn btn-warning" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                </div>
            </form>
        </div>
    </div>
</div>
<%
} else {
%>
<script>
    window.location.replace("/ControlGames/index.jsp");
</script>
<%}%>