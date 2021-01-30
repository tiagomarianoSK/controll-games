<%-- 
    Document   : marca
    Created on : 03/04/2019, 16:53:12
    Author     : jeffersonpasserini
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
<c:when test = "${sessionScope.idusuario != null}">
         
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Gerenciamento de ColeÃ§Ã£o de Videogames</title>
        </head>
        <body>
            <%@include file="/cabecalho.jsp" %>
       
            <article class="sessao">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/menuLogado.jsp">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Marca</li>
                    </ol>
                </nav>
                <div class="container">
                    <h1 class="text-primary text-center">Marcas</h1>
                    <table class="data-table stripe hover nowrap">
                        <thead>
                            <tr>
                                <th colspan="4" aling="center">Lista de Marcas</th>
                            </tr>
                            <tr>
                                <th class="th-sm" colspan="1">ID</th>
                                <th class="th-sm" colspan="1">DescriÃ§Ã£o</th>
                                <th class="th-sm" colspan="1">SituaÃ§Ã£o</th>
                                <th class="th-sm" colspan="1">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="marca" items="${marcas}">
                            <tr>
                                <td align="center">${marca.idMarca}</td>
                                <td align="center">${marca.descricao}</td>
                                <td align="center">${marca.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                <td align="center">    
                                    <a href="${pageContext.request.contextPath}/MarcaExcluir?idMarca=${marca.idMarca}"><button class="btn btn-group-lg <c:out value="${marca.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>"><c:out value="${marca.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                    <a href="#"><button class="btn btn-group-lg btn-success" data-toggle="modal" data-target="#cadastrar-marca-modal" onclick="CarregarMarca(${marca.idMarca})">Alterar</button></a>                                
                                </td> 
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="btns">
                        <a href="#"><button class="btn btn-group-lg btn-success" data-toggle="modal" data-target="#cadastrar-marca-modal" onclick="LimparModal()">Nova Marca</button></a>
                        <a href="${pageContext.request.contextPath}/menuLogado.jsp"><button class="btn btn-group-lg btn-primary">Voltar a PÃ¡gina Inicial</button></a>
                    </div>
                </div>
            </article>
            <%@include file="/rodape.jsp" %>
            <script>
                $(document).ready(function () {
                    $('#table_id').DataTable();
                });
            </script>

            <!-- Large modal -->
            <div class="modal fade bs-example-modal-lg" id="cadastrar-marca-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarMarca" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myLargeModalLabel">Marca</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã—</button>
                        </div>
                        <form  data-toggle="validator" name="cadastro" action="${pageContext.request.contextPath}/MarcaCadastrar" method="POST">
                            <div class="modal-body">                                     
                                <div class="form-group row">
                                    <label class="col-sm-12 col-md-2 col-form-label">ID:</label>
                                    <div class="col-sm-12 col-md-10">
                                        <input class="form-control fct" readonly="readonly" type="text" id="idmarca" name="idmarca"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-12 col-md-2 col-form-label">DescriÃ§Ã£o:</label>
                                    <div class="col-sm-12 col-md-10">
                                        <input class="form-control fct" type="text" name="descricao" id="descricao" data-error="Por favor, preencha o campo descriÃ§Ã£o." required/>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">                                                        
                                <input type="reset" onclick="LimparModal()" class="btn btn-secondary" value="Limpar"/>
                                <input type="submit" class="btn btn-primary" value="Salvar"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--Fim da Modal-->
            <script>
                $('document').ready(function () {
                    $('.data-table').DataTable({
                        scrollCollapse: true,
                        autoWidth: false,
                        responsive: true,
                        columnDefs: [{
                                targets: "datatable-nosort",
                                orderable: false
                            }],
                        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Tudo"]],
                        "language": {
                            "oPaginate": {
                                "sNext": "PrÃ³ximo",
                                "sPrevious": "Anterior",
                                "sFirst": "Primeiro",
                                "sLast": "Ãšltimo"
                            },
                            "lengthMenu": "Mostrar _MENU_ por pÃ¡gina",
                            "zeroRecords": "Sem registro",
                            "infoEmpty": "Nenhum registro encontrado",
                            "info": "_START_-_END_ de _TOTAL_ registros",
                            "searchPlaceholder": "Buscar",
                            "search": ""
                        }
                    });
                    $('.data-table-export').DataTable({
                        scrollCollapse: true,
                        autoWidth: false,
                        responsive: true,
                        columnDefs: [{
                                targets: "datatable-nosort",
                                orderable: false,
                            }],
                        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                        "language": {
                            "info": "_START_-_END_ de _TOTAL_ registros",
                            searchPlaceholder: "Buscar"
                        },
                        dom: 'Bfrtip',
                        buttons: [
                            'copy', 'csv', 'pdf', 'print'
                        ]
                    });
                    var table = $('.select-row').DataTable();
                    $('.select-row tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('selected')) {
                            $(this).removeClass('selected');
                        } else {
                            table.$('tr.selected').removeClass('selected');
                            $(this).addClass('selected');
                        }
                    });
                    var multipletable = $('.multiple-select-row').DataTable();
                    $('.multiple-select-row tbody').on('click', 'tr', function () {
                        $(this).toggleClass('selected');
                    });
                });
            </script>
            <script type="text/javascript">
                function CarregarMarca(v) {
                    console.log("Entrou");
                    var idM = v;
                    console.log("Marca = " + idM);
                    $(document).ready(function () {             
                        $.getJSON('MarcaCarregar', {idMarca: idM}, function (respostaServlet) {
                            $('#idmarca').val(respostaServlet.idMarca);                        
                            $('#descricao').val(respostaServlet.descricao);
                        });
                     });
                }
                function LimparModal() {
                    $('.fct').val('');
                    $('#descricao').empty();
                    $('#idmarca').val("0");
                }
            </script> 

        </body>
    </html>
</c:when>
<c:otherwise>
    <script>
        window.location.replace("/ControlGames/index.jsp");
    </script>
</c:otherwise>
</c:choose>

