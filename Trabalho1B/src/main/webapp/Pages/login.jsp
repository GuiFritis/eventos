<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Eventos</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="img/favicon.ico"  />
        <!--===============================================================================================-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous" />
        <!--===============================================================================================-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/open-iconic/1.1.1/font/css/open-iconic.min.css" integrity="sha512-LeCmts7kEi09nKc+DwGJqDV+dNQi/W8/qb0oUSsBLzTYiBwxj0KBlAow2//jV7jwEHwSCPShRN2+IWwWcn1x7Q==" crossorigin="anonymous" />
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css" />
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/animsition@4.0.2/dist/css/animsition.min.css" />
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </head>
    <body>
        <div class="container-fluid">
            <div class="col-xs-12 text-center bg-dark rounded-bottom">
                <h1 class="display-2 text-light pb-2">Evento</h1>
            </div>
            <div class="row justify-content-md-center h-100 p-5 align-middle">
                <div class="col-md-6 col-xs-12">
                    <div class="card border border-dark">
                        <div class="card-header bg-dark">
                            <h1 class="lead text-light">Login</h1>
                        </div>
                        <form method="POST" action="control" class="my-login-validation" novalidate="">
                            <input type="hidden" name="action" value="logar"/>                                                       
                            <div class="card-body">
                                <label class="control-label">Usuário:</label>
                                <input type="text" class="form-control" name="username" required>
                                <label class="control-label">Senha:</label>
                                <input type="password" class="form-control" name="senha" required>   
                                
                                <br>
                                
                                <%String error = request.getParameter("error");
                                    if (error != null){
                                %>
                                        <div class="form-group">
                                            <label class="control-label text-center" for="remember"> Login ou senha incorreta </label>
                                        </div>
                                <%
                                    }
                                %>
                                
                            </div>    
                            <div class="card-footer bg-dark">
                                <button type="submit" class="btn btn-dark border border-light">Entrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
