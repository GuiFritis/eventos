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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>      
        
    </head>
    <body>
        <div class="row">
            <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-dark sidebar collapse">
                <div class="sidebar-sticky pt-3">
                    <a class="nav-link" href="control?action=home"><h1 class="display-4 text-light">Eventos</h1></a>
                    <ul class="nav flex-column">
                        <li class="nav-item mb-1">
                            <a class="nav-link btn-light text-dark rounded-right" href="control?action=listaEventos"><i class="fas fa-calendar"></i> Listar Eventos</a>
                        </li>
                        <li class="nav-item mb-1">
                            <a class="nav-link btn-light text-dark rounded-right" href="control?action=cadastraEvento"><i class="fas fa-calendar-plus"></i> Cadastrar Evento</a>
                        </li>
                        <li class="nav-item mb-1 mt-3">
                            <a class="nav-link btn-light text-dark rounded-right" href="control?action=cadastraIngresso"><i class="fas fa-plus-circle"></i> Cadastrar Ingresso</a>
                        </li>
                        <c:if test="${sessionScope.user.isAdmin()}">
                            <li class="nav-item mb-1 mt-3">
                                <a class="nav-link btn-light text-dark rounded-right" href="control?action=listaRelatorios"><i class="far fa-chart-bar"></i> Relatórios </a>
                            </li>
                            <li class="nav-item mb-1 mt-3">
                                <a class="nav-link btn-light text-dark rounded-right" href="control?action=listaUsuarios"><i class="fas fa-users"></i> Listar Usuários</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a class="nav-link btn-light text-dark rounded-right" href="control?action=cadastraUsuario"><i class="fas fa-user-plus"></i> Cadastrar Usuário</a>
                            </li>
                        </c:if>
                        <li class="nav-item mb-1 mt-3">
                            <a class="nav-link btn-light text-dark rounded-right" href="control?action=logout"><i class="fas fa-sign-out-alt"></i> Sair</a>
                        </li>
                    </ul>
              </div>
            </nav>
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 bg-light">
                <div class="container-fluid mt-2">
                    <c:if test="${requestScope.msg != null && requestScope.msg != \"\"}">
                        <div class="alert alert-dark alert-dismissible fade show" role="alert">
                            ${requestScope.msg}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.page == null}">
                        <jsp:include page="Pages/home.jsp"/>
                    </c:if>            
                    <c:if test="${requestScope.page != null}">
                        <jsp:include page="${requestScope.page}" />
                    </c:if>
                </div>
            </main>
            <footer class="bg-dark text-light text-center">
                Eventos - Williane & Fritis'
            </footer>
        </div>
    </body>
</html>

