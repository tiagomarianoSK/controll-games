<%-- 
    Document   : cabecalho
    Created on : 25/10/2016, 20:49:04
    Author     : Jefferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta name="viewport" content="widht=device.widht, initial-scale=1, shrink-to-fit=no">
        <link href="css/responsive.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- ImportaÃ§Ãµes jquery/ajax --%>

        <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.mask.min.js" type="text/javascript"></script>
        <%-- ImportaÃ§Ãµes da minha biblioteca de javascript --%>
        <script src="${pageContext.request.contextPath}/js/app.js" type="text/javascript"></script>
        <%-- Validacao formulario --%>
        <script src="js/validator.min.js"></script>
        <%-- ImportaÃ§Ãµes bootstrap --%>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        
        
        <script tyoe="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        
        <link href="css.css" rel="stylesheet" type="text/css"/>

        <title>Gerenciamento de Coleções</title>
    </head>
    <body>

        <nav class="navbar navbar-dark bg-dark" style="background-color: #e3f2fd;">  <h1 align="center" id="titulo">CONTROL GAMES</h1>
          
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
               
                <span class="navbar-toggler-icon"></span>

            </button>

            <div class="collapse navbar-collapse" id="collapsibleNavbar">
               
                <%
                    HttpSession sessaoCab = request.getSession(false);
                    if (sessaoCab == null) {
                %>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="cabecalho.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="rodape.jsp">Sobre</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="rodape.jsp">Contato</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <button type="button" class="btn btn-outline-warning my-2 my-sm-0" data-toggle="modal" data-target="#myModal">
                        Login </button>
                </form> 

                <%
                } else {
                    String nomeUsuarioLogado = (String) sessaoCab.getAttribute("nomeusuario");
                %>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Usuarios</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/UsuarioListar">Usuario</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/VendedorListar">Vendedor</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/CompradorListar">Comprador</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/AmigoListar">Amigo</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Marca</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/MarcaListar">Marca</a>
                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Modelo</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/ModeloListar">Modelo</a>
                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Regiao</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/RegiaoListar">Regiao</a>
                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Item</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/TipoItemListar">Tipo Do Item</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Plataforma</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/PlataformaListar">Plataforma</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Procedencia</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/ProcedenciaListar">Procedencia</a>
                        </div>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <button type="button" class="btn btn-outline-success my-2 my-sm-0" data-toggle="modal" data-target="" onclick="deslogar()" name="sair" id="sair">
                        <% out.print(nomeUsuarioLogado);%> - Sair </button>
                </form>                   
                <% }%>
            </div>
        </nav>

        <!-- The Modal -->
        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="" id="frmlogin" name="frmlogin" 
                          accept-charset="UTF-8" role="form">
                        <!-- Modal Header -->
                        <div class="modal-header bg-dark">
                            <h4 class="modal-title text-white">Login</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <p class="erro"> ${mensagemErro}</p>

                            <fieldset id="fslogin">
                                <div class="form-group controls">
                                    <label>NOME : </label>
                                    <input type="text" name="loginusuario" id="loginusuario"  class="form-control" placeholder="Login Usuario"/>
                                </div>
                                <div class="form-group">
                                    <label>SENHA :</label>
                                    <input type="password" name="senhausuario" id="senhausuario" class="form-control" placeholder="Senha"/>
                                </div>
                                <a href="#" class=""><p>Recuperar a senha</p></a>
                                <p id="errorlog" align="center">Usuario ou senha incorretos</p>
                            </fieldset>

                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer bg-dark">
                            <input type="reset" value="LIMPAR" class="btn btn-secondary"/>
                            <input type="submit" value="LOGAR" class="btn btn-warning"/>
                        </div>
                    </form>

                </div>
            </div>

        </div>

        <script>
            $(document).ready(function () {
                $('#errorlog').hide();
                $('#frmlogin').submit(function () {
                    var login = $('#loginusuario').val();
                    var senha = $('#senhausuario').val();
                    $.ajax({
                        url: "/ControlGames/UsuarioLogar",
                        type: "post",
                        data: "loginuser=" + login + "&senhauser=" + senha,
                        success: function (result) {
                            if (result === "ok") {
                                location.href = 'menuLogado.jsp';
                            } else {
                                $('#errorlog').show();
                            }
                        }
                    });
                    return false;
                });
            });
        </script>

        <script>
            function deslogar() {
                //console.log("entrei funcao sair");
                window.location.href = "/ControlGames/index.jsp";
            }
        </script>         
    </body>
</html>

