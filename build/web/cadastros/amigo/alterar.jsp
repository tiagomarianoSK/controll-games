<%
    if (session.getAttribute("idusuario") != null) {
%>
<%@page import="br.com.controlgames.model.Amigo" %>
<%@page import="java.util.List" %>
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
                <li class="breadcrumb-item"><a href="${pageContextPath}/menulogado.jsp">Home</a></li>
                <li class="breadcrumb-item"><a href="${pageContextPath}/AmigoListar">Usuarios</a></li>
                <li class="breadcrumb-item active" aria-current="page">Alterar</li>
            </ol>
        </nav>
        <div class="container my-2">
            <div class="bg-dark p-2 rounded" ><h3 class="text-light">Amigo</h3></div>
            <form class="border border-warning border-top-0 border-bottom-0 p-2 my-3 rounded" name="alterarAmigo" action="${pageContext.request.contextPath}/AmigoAlterar" method="POST">
                <div><h5>${mensagem}</h5></div>
                <div class="form-group">
                    <label for="idUsuario">ID</label>
                    <input type="text" class="form-control" name="idAmigo" id="idAmigo" value="${amigo.idAmigo}" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="cpfCnpjPessoa">CPF</label>
                    <input type="text" class="form-control" name="cpfCnpjPessoa" value="${amigo.cpf}"  id="cpfCnpjPessoa" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="nomePessoa">Nome</label>
                    <input type="text" class="form-control" name="nomePessoa" value="${amigo.nome}" id="nomePessoa" maxlength="50">
                </div>
                <div class="form-group">
                    <label for="observacao">Observação</label>
                    <input type="text" class="form-control" name="observacao" id="observacao" value="${amigo.observacao}" maxlength="10">
                </div>
                <div class="form-group d-flex justify-content-end">       
                    <input type="reset" class="btn btn-secondary mx-1" name="limpar" id="limpar" value="Limpar"/>
                    <input type="submit" class="btn btn-warning mx-1" name="alterar" id="alterar" value="Alterar"/>
                </div>
            </form>
        </div>
        <%@include file="/rodape.jsp" %>

    </body>
</html>

<%
} else {
%>
<script>
    window.location.replace("/ControlGamesII/index.jsp");
</script>
<%}%>