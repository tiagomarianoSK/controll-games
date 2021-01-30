
<%@page import="br.com.controlgames.model.Vendedor" %>
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
            <div class="bg-dark p-2 rounded" ><h3 class="text-light">Vendedor</h3></div>
            <form class="border border-secondary border-top-0 border-bottom-0 p-2 my-3 rounded" name="alterarVendedor" action="${pageContext.request.contextPath}/VendedorAlterar" method="POST">
                <div><h5>${mensagem}</h5></div>
                <div class="form-group">
                    <label for="idUsuario">ID</label>
                    <input type="text" class="form-control" name="idVendedor" value="${vendedor.idVendedor}"  id="idVendedor" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="cpfCnpjPessoa">CPF</label>
                    <input type="text" class="form-control" name="cpfCnpjPessoa" value="${vendedor.cpf}"  id="cpfCnpjPessoa" maxlength="50" readonly>
                </div>
                <div class="form-group">
                    <label for="nomePessoa">Nome</label>
                    <input type="text" class="form-control" name="nomePessoa" value="${vendedor.nome}" id="nomePessoa" maxlength="50">
                </div>
                <div class="form-group">
                    <label for="url">Url</label>
                    <input type="text" class="form-control" name="url" id="url" value="${vendedor.url}" maxlength="10">
                </div>
                <div class="form-group">
                    <label for="observacao">Observação</label>
                    <input type="text" class="form-control" name="observacao" id="obaservacao"value="${vendedor.observacao}" maxlength="10">
                </div>
                <div class="form-group d-flex justify-content-end">       
                    <input type="reset" class="btn btn-secondary mx-1" name="limpar" id="limpar" value="Limpar"/>
                    <input type="submit" class="btn btn-success mx-1" name="alterar" id="alterar" value="Alterar"/>
                </div>
            </form>
        </div>
        <%@include file="/rodape.jsp" %>
    </body>
</html>

