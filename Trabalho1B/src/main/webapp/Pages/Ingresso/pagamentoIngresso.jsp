<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="control" method="POST">
    <div class="col-xs-12 text-center">
        <h2 class="bg-dark border rounded border-dark text-light">Opções</h2>
    </div>
    
    <div class="col-xs-12">
        <div class="col-12 form-group">
            <label for="evento" class="label-control">Evento:</label>
            <input type="text" class="form-control" value="${requestScope.evento.nome}" readonly>
            <input type="hidden" name="evento" value="${requestScope.evento.idevento}">
        </div>
        <div class="col-12 form-group">
            <label for="tipo_ingresso" class="label-control">Entrada:</label>
            <input type="text" name="tipo_entrada" class="form-control" value="${requestScope.tipo_entrada}" readonly>        
        </div>
            <c:if test="${lugar != \"\"}">
            <div class="col-12 form-group">
                <label for="assentos" class="label-control">Assentos:</label>
                <div class="row">
                    <c:forEach var="a" items="${requestScope.assentos}">
                        <div class="col-md-4 col-6 mb-1">
                            <input type="text" name="assento" class="form-control" value="${a}" readonly>
                        </div>
                    </c:forEach>
                </div>   
            </div>
        </c:if>
        <input type="hidden" name="qtd_ingressos" value="${requestScope.qtdIngressos}">
        <div class="col-12 form-group">
            <label for="qtd_ingressos" class="label-control">Quantidade de ingressos:</label>
            <input type="text" name="qtdIngressos" class="form-control" value="${requestScope.qtdIngressos}" readonly>        
        </div>
        <c:if test="${requestScope.preco > 0}">
            <div class="col-12 form-group">
                <label for="preco_unitario" class="label-control">Preço unitário:</label>
                <input type="text" class="form-control" value="R$ ${requestScope.preco}" readonly>        
            </div>
        </c:if>
    </div>
            
    <div class="col-xs-12 text-center mt-4">
        <h2 class="bg-dark border rounded border-dark text-light">Total: 
            <c:choose>
                <c:when test="${requestScope.preco > 0}">R$ ${requestScope.preco * requestScope.qtdIngressos}</c:when>
                <c:otherwise>${requestScope.qtdIngressos} doações</c:otherwise></c:choose>
        </h2>  
    </div>
    
    <button class="btn btn-dark" type="submit" name="action" value="finalizaCompra">Finalizar Compra</button>
</form>
