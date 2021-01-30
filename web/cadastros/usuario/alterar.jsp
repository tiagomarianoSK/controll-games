
<%
    if (session.getAttribute("idusuario") != null) {
%>
<%@page import="br.com.controlgames.model.Usuario" %>
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
                <li class="breadcrumb-item"><a href="${pageContextPath}/UsuarioListar">Usuarios</a></li>
                <li class="breadcrumb-item active" aria-current="page">Alterar</li>
            </ol>
        </nav>   
        <div class="container my-2">
            <div class="bg-dark p-2 rounded" ><h3 class="text-light">Usuário</h3></div>
            <form class="needs-validation" name="alterarUsuario" action="${pageContext.request.contextPath}/UsuarioAlterar" method="POST">
                <div><h5>${mensagem}</h5></div>
                <div class="form-row">
                    <label for="idUsuario">ID</label>
                    <input type="text" class="form-control" name="idUsuario" value="${usuario.idUsuario}"  id="idUsuario" maxlength="50" readonly>
                </div>
                <div class="form-row">
                    <label for="cpfCnpjPessoa">CPF</label>
                    <input type="text" class="form-control" name="cpfCnpjPessoa" value="${usuario.cpf}"  id="cpfCnpjPessoa" maxlength="50" readonly>
                </div>
                <div class="form-row">
                    <label for="nomePessoa">Nome</label>
                    <input type="text" class="form-control" name="nomePessoa" value="${usuario.nome}" id="nomePessoa" maxlength="50">
                </div>
                <div class="form-row">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" name="login" value="${usuario.login}" id="login"  maxlength="10">
                </div>
                <div class="form-row">
                    <label for="senha">Senha</label>
                    <input type="password" class="form-control" name="senha" value="${usuario.senha}" id="senha" maxlength="10">
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
    window.location.replace("/ControlGames/index.jsp");
</script>
<%}%>