<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">Lista de Ingressos: ${requestScope.evento.nome}</h1>
</div>

<button type="button" class="btn btn-dark" id="filtros">Filtros <i class="fas fa-sort-down" id="seta"></i></button>
<span class="bg-dark text-light border rounded px-1">Horário: ${requestScope.evento.getStringData()}</span>

<div class="col-xs-12 mr-2 ml-2" id="div_filtros" style="display: none;">
    <form action="control" method="POST">
        <input type="hidden" name="action" value="listaIngressos" />
        <input type="hidden" name="evento" value="${requestScope.evento.idevento}">
        <div class="row">
            <div class="col-md-6 form-group">
                <label for="filtro_numero" class="control-label">Número:</label>
                <input type="number" min="0" max="700" step="1" name="filtro_numero" id="filtro_numero" class="form-control" value="${requestScope.filtro_numero}">
            </div>
            <div class="col-md-6 form-group">
                <label for="filtro_entrada" class="control-label">Entrada:</label><br>
                <div class="form-check form-check-inline">
                    <input type="radio" id="tipo_inteira" name="filtro_entrada" value="inteira" 
                           <c:if test="${requestScope.filtro_entrada.equals(\"inteira\")}">checked</c:if>>
                    <label class="form-check-label">Inteira</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" id="tipo_meia" name="filtro_entrada" value="meia" 
                           <c:if test="${requestScope.filtro_entrada.equals(\"meia\")}">checked</c:if>>
                    <label class="form-check-label">Meia</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" id="tipo_doacao" name="filtro_entrada" value="doacao" 
                           <c:if test="${requestScope.filtro_entrada.equals(\"doacao\")}">checked</c:if>>
                    <label class="form-check-label">Doação</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 form-group">
                <label for="filtro_setor" class="control-label">Setor:</label>
                <select name="filtro_setor" id="filtro_setor" class="form-control">
                    <option value="">Selecione um setor</option>
                    <c:forTokens var="j" delims="," items="0,1,2,3,4,5">
                        <option value="${j * 81 + 1}" 
                            <c:if test="${requestScope.filtro_setor == (j * 81 + 1)}">selected</c:if>>
                                Setor ${j + 1}
                        </option>
                    </c:forTokens>
                    <c:forTokens var="j" delims="," items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14">
                        <option value="${(81 * 6) + 1 + (j * 10)}" 
                            <c:if test="${requestScope.filtro_setor == ((81 * 6) + 1 + (j * 10))}">selected</c:if>>
                                Cabine ${j + 1}
                        </option>
                    </c:forTokens>>
                </select>
            </div>
            <div class="col-md-6 form-group mt-4">
                <button class="btn btn-dark" name="filtrar" value="1">Filtrar</button>
                <button class="btn btn-dark" name="filtrar" value="0">Limpar Filtros</button>
            </div>
        </div>
    </form>
</div>
<div class="table-responsive mt-2">
    <table class="table table-sm">
        <thead class="thead-dark">
            <tr>
                <th>Tipo de Ingresso</th>
                <th>Preço</th>
                <th>Número</th>
                <c:if test="${requestScope.evento.local.idlocal==2}">
                    <th>Setor</th>
                </c:if>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${requestScope.ingressos}">
                <tr>
                    <td>${i.tipoEntrada}</td>
                    <td>${i.CalculaPreco()}</td>
                    <td>${i.numero}</td>
                    <c:if test="${requestScope.evento.local.idlocal==2}">
                        <td>
                            <c:if test="${i.numero <= (81 * 6)}">
                                Setor: <fmt:formatNumber type="number" pattern="##" value="${Math.ceil(i.numero/81)}"/>
                            </c:if>
                            <c:if test="${i.numero > (81 * 6)}">
                                Cabine: <fmt:formatNumber type="number" pattern="##" value="${Math.ceil((i.numero - 81 * 6)/10)}"/>
                            </c:if>
                        </td>
                    </c:if>
                    <td>
                        <c:if test="${i.numero <= (81 * 6)}">
                            <button type="button" class="btn btn-dark btn-sm" data-toggle="tooltip" title="Deletar Ingresso" onclick="document.location='control?action=cancelaIngresso&id=${i.idingresso}'">
                                <i class="fas fa-window-close"></i>
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
        
        <c:if test="${requestScope.filtrar}">
                $("#filtros").click();
        </c:if>
            
    })
</script>