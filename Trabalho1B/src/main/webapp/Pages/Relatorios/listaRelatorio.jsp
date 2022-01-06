<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">Relatórios</h1>
</div>

<button type="button" class="btn btn-dark" id="filtros">Filtros <i class="fas fa-sort-down" id="seta"></i></button>

<div class="col-xs-12 mr-2 ml-2" id="div_filtros" style="display: none;">
    <form action="control" method="POST">
        <input type="hidden" name="action" value="listaRelatorios" />
        <br>
        <div class="row">
            <div class=" col-md-6 col-xs-12 form-group">
                <label for="filtro_tipo" class="control-label">Tipo de Evento:</label>
                <select name="filtro_tipo" id="filtro_tipo" class="form-control" required>
                    <option name="filtro_tipo" value="Todos" <c:if test="${requestScope.filtro_tipo eq 'Todos'}"> selected </c:if> > Todos Eventos </option>
                    <option name="filtro_tipo" value="Curso" <c:if test="${requestScope.filtro_tipo eq 'Curso'}"> selected </c:if> > Curso </option>
                    <option name="filtro_tipo" value="Palestra" <c:if test="${requestScope.filtro_tipo eq 'Palestra'}"> selected </c:if> > Palestra </option>
                    <option name="filtro_tipo" value="Teatro" <c:if test="${requestScope.filtro_tipo eq 'Teatro'}"> selected </c:if> > Teatro </option>
                    <option name="filtro_tipo" value="Show" <c:if test="${requestScope.filtro_tipo eq 'Show'}"> selected </c:if> > Show </option>
                    <option name="filtro_tipo" value="Mostra Cultural" <c:if test="${requestScope.filtro_tipo eq 'Mostra Cultural'}"> selected </c:if> > Mostra Cultural </option>  
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-xs-12 form-group">
                <label class="control-label form-check form-check-inline" for="filtro_local">Local:</label>              
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" name="filtro_local" id="local" value="1" <c:if test="${requestScope.filtro_local.idlocal == 1}"> checked </c:if> >
                    <label class="form-check-label" for="local">Salão</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" name="filtro_local" id="local" value="2" <c:if test="${requestScope.filtro_local.idlocal == 2}"> checked </c:if> >
                    <label class="form-check-label" for="local">Anfiteatro</label>
                </div>
            </div>
        </div>        
        <div class="row">
            <div class="col-md-4 col-xs-12 form-group">
                <label for="data" class="control-label">Data do Relatório:</label>
                <div class="input-group">
                    <input type="text"  class="form-control" name="data" id="data" readonly <c:if test="${requestScope.data != null}"> value="${requestScope.data}"  </c:if> >
                    <div class="input-group-append">
                        <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
                    </div>
                </div>
            </div>   
        </div>                      
        <div class="row">
            <div class="col-md-6 col-xs-12 form-group">
                <button class="btn btn-dark" name="filtrar" value="true">Filtrar</button>
                <button class="btn btn-dark" name="filtrar" value="false">Limpar Filtros</button>
            </div>
        </div>
    </form>
    <br>
</div>

<div class="col-xs-12 table-responsive mt-2">
    <table class="table">
        <thead class="bg-dark text-light">
            <tr>              
                <th>Tipo de Evento</th>
                <th>Nome do Evento</th>
                <th>Local</th>
                <th>Data do Evento</th>
                <th class="text-center">Despesas</th>
                <th class="text-center">Ganhos</th>
                <th class="text-center">Lucro</th>
            </tr>
        </thead>
        <tbody>
            
            <c:if test="${requestScope.eventos.size() == 0}">
                <tr>
                    <td class="text-center" colspan="7"> Não é possível gerar relatórios para os filtros selecionados </td>
                </tr> 
            </c:if>
            
            <c:forEach var="e" items="${requestScope.eventos}">
                <tr>
                    <td>${e.tipoEvento}</td>
                    <td>${e.nomeEvento}</td>
                    <td>${e.nomeLocal}</td>
                    <td> <fmt:formatDate value="${e.horario}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td class="text-right">R$ ${e.despesas()}</td>
                    <td class="text-right">R$ ${e.ganhos()}</td>
                    <td class="text-right">R$ ${e.lucro()}</td>                 
                </tr>
            </c:forEach>
                <tr class="bg-dark text-light text-right">
                    <td colspan="4"> Total </td>
                    <td class="text-right"> R$ ${requestScope.despesaTotal} </td>
                    <td class="text-right"> R$ ${requestScope.ganhoTotal} </td>
                    <td class="text-right"> R$ ${requestScope.lucroTotal} </td>
                </tr>                
        </tbody>      
    </table>
</div>

                
                
<script>  
    $(document).ready(function(){     
        $('#data').daterangepicker({
            "locale": {
                "format": "DD/MM/YYYY"
            },
            autoUpdateInput: false
        });
        
        $('#data').on('apply.daterangepicker', function(ev, picker) {
            $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        });

        $('#data').on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });
        
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
    });
</script>
