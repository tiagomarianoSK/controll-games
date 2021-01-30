
<%@page import="br.com.controlgames.model.Comprador" %>
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
        <div class="container my-2">
            <div class="bg-dark p-2 rounded" ><h3 class="text-light">Comprador</h3></div>
            <form class="border border-warning border-top-0 border-bottom-0 p-2 my-3 rounded" name="alterarComprador" action="${pageContext.request.contextPath}/CompradorAlterar" method="POST">
                <div><h5>${mensagem}</h5></div>
                <div class="form-group">
                    <label for="idUsuario">ID</label>
                    <input type="text" class="form-control" name="idComprador" id="idComprador" value="${comprador.idComprador}" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="cpfCnpjPessoa">CPF</label>
                    <input type="text" class="form-control" name="cpfCnpjPessoa" value="${comprador.cpf}"  id="cpfCnpjPessoa" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="nomePessoa">Nome</label>
                    <input type="text" class="form-control" name="nomePessoa" value="${comprador.nome}" id="nomePessoa" maxlength="50">
                </div>
                <div class="form-group">
                    <label for="observacao">Observação</label>
                    <input type="text" class="form-control" name="observacao" id="observacao" value="${comprador.observacao}" maxlength="10">
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

