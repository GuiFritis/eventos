<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div class="alert alert-dark alert-dismissible fade show text-danger font-weight-bold" role="alert">
        <h1>Ocorreu algum erro!</h1>
        ${requestScope.error}
        <br>
        Entre em contato com os administradores;
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>