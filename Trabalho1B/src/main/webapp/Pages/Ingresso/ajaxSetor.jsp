
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<label for="assento" class="control-label">Escolha seu assento:</label>
<div id="mudanca"
<input type="hidden" name="setor" value="" required/>
<div id="assentos">  
    <table class="table table-bordered table-dark"
    <c:forTokens var="i" delims="," items="1,0">
        <tr>
            <c:forTokens var="j" delims="," items="1,2,3">
                <td class="text-center" onclick="carregaAssentos(${i*3 + j})">
                    SETOR ${i*3 + j}
                </td>
            </c:forTokens>
        </tr>
    </c:forTokens >
    <tr>
        <td colspan="3" class="text-center">
            PALCO
        </td>
    </tr>
    </table>
</div>
