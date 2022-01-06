
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">${requestScope.titulo}</h1>
</div>
<form action="control" method="POST">
    <input type="hidden" name="idUsuario" value="${requestScope.usuario.idusuario}">
    <div class="col-xs-12 from-group">
        <label class="control-label">Login</label>
        <input type="text" name="login" id="login" class="form-control" required value="${requestScope.usuario.login}" <c:if test="${requestScope.usuario.idusuario > 0}">readonly</c:if>>
    </div>
    <div class="col-xs-12 from-group">
        <label class="control-label">Senha</label>
        <input type="password" name="senha" id="senha" class="form-control" <c:if test="${requestScope.usuario.idusuario <= 0}">required</c:if>>
    </div>
    <div class="col-xs-12 from-group">
        <label class="control-label">Redigite sua senha</label>
        <input type="password" name="senha_redigita" id="senha_redigita" class="form-control" <c:if test="${requestScope.usuario.idusuario <= 0}">required</c:if>>
    </div>
    <div class="col-xs-12 from-check form-check-inline">
        <input type="checkbox" name="isAdmin" id="isAdmin" value="true" class="form-control-checkbox" 
            <c:if test="${requestScope.usuario.adm.equalsIgnoreCase(\"SIM\")}">checked</c:if>
            <c:if test="${sessionScope.user.idusuario == requestScope.usuario.idusuario}">disabled</c:if>>
        <label class="control-label">Acesso Administrativo:</label>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <c:if test="${requestScope.usuario.idusuario == null}">
                <button type="submit" class="btn btn-dark" name="action" value="insertUsuario">Inserir</button>
            </c:if>
            <c:if test="${requestScope.usuario.idusuario > 0}">
                <button type="submit" class="btn btn-dark" name="action" value="updateUsuario">Atualizar</button>
            </c:if>
        </div>        
    </div>
</form>
