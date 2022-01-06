<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<label for="select_cabine" class="control-label">Cabine:</label>
<select name="assento" id="select_cabine" class="form-control" required>
    <option value="">Selecione uma cabine</option>
    <c:forTokens var="j" delims="," items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14">
        <c:set var="ocupado" value="${false}"></c:set>
        <c:forEach var="c" items="${requestScope.cadeiras}">
            <c:if test="${c == (81 * 6) + 1 + (j * 10)}">
                <c:set var="ocupado" value="${true}"></c:set>
            </c:if>
        </c:forEach>
        <option value="${(81 * 6) + 1 + (j * 10)}" <c:if test="${ocupado == true}">disabled</c:if>>Cabine ${j + 1}</option>
    </c:forTokens>>
</select>
<small class="text-danger"> Não é possível desmarcar ingressos de cabine depois de compradas.</small>