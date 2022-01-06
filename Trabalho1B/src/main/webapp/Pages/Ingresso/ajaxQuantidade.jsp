<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<label for="select_cabine" class="control-label">Quantidade de ingressos:</label>
<input type="number" class="form-control" min="0" max="${requestScope.vagas > 20?20:requestScope.vagas}" 
       step="1" name="qtd_ingressos" id="qtd_ingressos" placeholder="Quantos ingressos deseja comprar" required>
<c:choose>
    <c:when test="${requestScope.vagas > 20}">
        <small>Máximo de 20 ingressos por vez</small>
    </c:when>
    <c:otherwise>
        <small>Últimas ${requestScope.vagas} disponíveis</small>
    </c:otherwise>
</c:choose>