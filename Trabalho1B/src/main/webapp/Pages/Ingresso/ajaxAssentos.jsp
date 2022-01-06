<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<button type="button" class="btn btn-dark" onclick="carregaSetor()">Voltar para sessões</button>
<table class="table table-bordered table-dark">
    <c:forTokens var="i" delims="," items="8,7,6,5,4,3,2,1,0">
        <tr>
            <c:forTokens var="j" delims="," items="1,2,3,4,5,6,7,8,9">
                <td class="text-center
                    <c:set var="ocupado" value="${false}"></c:set>
                    <c:forEach var="c" items="${requestScope.cadeiras}">
                        <c:if test="${c == ((requestScope.setor -1) * 81) + i*9 + j}">
                            <c:set var="ocupado" value="${true}"></c:set>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${ocupado == true}">
                            bg-secondary text-dark"
                        </c:when>
                        <c:otherwise>
                           " onclick="escolheAssento(this, ${i*9 + j})"
                        </c:otherwise>
                    </c:choose>
                >
                    <input type="hidden" name="assento" value="${((requestScope.setor -1) * 81) + i*9 + j}" disabled>
                    ASSENTO ${((requestScope.setor -1) * 81) + i*9 + j}
                </td>
            </c:forTokens>
        <tr>
    </c:forTokens >
    <c:if test="${requestScope.setor > 3}">
    <tr>
        <td colspan="9" class="text-center bg-secondary">
            SESSÃO ${requestScope.setor - 3}
        </td>
    </tr>
    </c:if>
    <tr>
        <td colspan="9" class="text-center">
            PALCO
        </td>
    </tr>
</table>
