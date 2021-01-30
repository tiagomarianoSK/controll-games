
<%
    if (session.getAttribute("idusuario") != null) {
%>
<%@page import="br.com.controlgames.model.Regiao" %>
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
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menuLogado.jsp">Home</a></li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/RegiaoListar">Procedencias</a></li>
                <li class="breadcrumb-item active" aria-current="page">Alterar</li>
            </ol>
        </nav>

        <div class="container my-2">
            <div class="bg-dark p-2 rounded" ><h3 class="text-light">Procedencia</h3></div>
            <form class="border border-warning border-top-0 border-bottom-0 p-2 my-3 rounded" name="alterarProcedencia" action="${pageContext.request.contextPath}/ProcedenciaAlterar" method="POST">
                <div><h5>${mensagem}</h5></div>
                <div class="form-group">
                    <label for="idUsuario">ID</label>
                    <input type="text" class="form-control" name="idProcedencia" id="idProcedencia" value="${procedencia.idProcedencia}" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="descricao">Descrição</label>
                    <input type="text" class="form-control" name="descricao" id="descricao" value="${procedencia.descricao}" maxlength="10">
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