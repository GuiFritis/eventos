<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">Lista de Usuários</h1>
</div>

<button type="button" class="btn btn-dark" id="filtros">Filtros <i class="fas fa-sort-down" id="seta"></i></button>

<div class="col-xs-12 mx-2" id="div_filtros" style="display: none;">
    <form action="control" method="POST">
        <input type="hidden" name="action" value="listaUsuarios">
        <div class="row">            
            <div class="col-md-6 col-12 from-group">
                <label for="filtro_login" class="control-label">Login</label>
                <input type="text" name="filtro_login" class="form-control" value="${requestScope.filtro_login}"/>
            </div>  
            <div class="col-md-6 col-12 form-group">
                <label for="adm" class="control-label">Administrador: </label>
                <select name="filtro_adm" id="filtro_adm" class="form-control">
                    <option value="">Administrador?</option>
                    <option value="SIM" <c:if test="${requestScope.filtro_adm.equals(\"SIM\")}">selected</c:if>>SIM</option>
                    <option value="NAO" <c:if test="${requestScope.filtro_adm.equals(\"NAO\")}">selected</c:if>>NÃO</option>
                </select>
            </div>
        </div>
            <div class="col-md-12 col-12">
                <button class="btn btn-dark" name="filtrar" value="true">Filtrar</button>
                <button class="btn btn-dark" name="filtrar" value="false">Limpar Filtros</button>
            </div>
        </div>
    </form>
</div>

<div class="col-xs-12 table-responsive mt-2">
    <table class="table">
        <thead class="bg-dark text-light">
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">Login</th>
                <th class="text-center">Admin</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${requestScope.usuarios}">
                <tr>
                    <td class="text-center">${u.idusuario}</td>
                    <td class="text-center">${u.login}</td>
                    <td class="text-center">
                        <c:if test="${u.isAdmin()}"><i class="fas fa-check-square"></i></c:if>
                        <c:if test="${!u.isAdmin()}"><i class="far fa-square"></i></c:if>
                    </td>
                    <td class="text-center">
                        <button type="button" class="btn btn-dark" data-toggle="tooltip" title="Editar Usuário" onclick="document.location='control?action=cadastraUsuario&id=${u.idusuario}'">
                            <i class="fas fa-user-edit"></i>
                        </button>
                        <c:if test="${sessionScope.user.idusuario != u.idusuario}">
                            <button type="button" class="btn btn-dark" data-toggle="tooltip" title="Desligar Usuário" onclick="document.location='control?action=excluiUsuario&id=${u.idusuario}'">
                                <i class="fas fa-user-times"></i>
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>

<script>
    $(document).ready(function(){
        $("#filtros").on('click', function(){
            console.log("TESTE");
            if($("#div_filtros").css("display") === "none"){
                $("#div_filtros").slideDown('slow');
                $("#seta").removeClass("fa-sort-down");
                $("#seta").addClass("fa-sort-up");
            } else {
                $("#div_filtros").slideUp('slow');
                $("#seta").removeClass("fa-sort-up");
                $("#seta").addClass("fa-sort-down");
            }
        }); 
    })
</script>
