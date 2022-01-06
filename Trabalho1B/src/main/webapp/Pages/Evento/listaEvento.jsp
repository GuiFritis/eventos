<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="css/nouislider.min.css" rel="stylesheet">
<script src="js/nouislider.min.js"></script>  

<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">Lista de Eventos</h1>
</div>

<button type="button" class="btn btn-dark" id="filtros">Filtros <i class="fas fa-sort-down" id="seta"></i></button>

<div class="col-xs-12 mr-2 ml-2" id="div_filtros" style="display: none;">
    <form name="listaForm" action="control" method="POST">
        <input type="hidden" name="action" value="listaEventos" />
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
            <div class="col-md-6 col-xs-12 form-group">
                <label for="filtro_nome" class="control-label">Nome do Evento:</label>
                <input type="text" class="form-control" name="filtro_nome" id="nomeEvento" <c:if test="${requestScope.filtro_nome != null}"> value="${requestScope.filtro_nome}" </c:if> >             
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
                <label for="data" class="control-label">Data do Evento:</label>
                <div class="input-group">
                    <input type="text"  class="form-control" name="data" id="data" readonly <c:if test="${requestScope.data != null}"> value="${requestScope.data}"  </c:if> >
                    <div class="input-group-append">
                        <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
                    </div>
                </div>
            </div>   
        </div>
        <div class="col-md-6 col-xs-12 form-group">
            <label for="preco" class="control-label">Preço do Ingresso:</label>
            <div id="slider_preco" width="50%"></div>
        </div>
        <div class="row">
            <div class="col-md-2 mb-2">
               <input class="form-control" type="text" id="filtro_preco_1" name="filtro_preco_1" readonly <c:if test="${requestScope.filtro_preco_1 != null}"> value="${requestScope.filtro_preco_1}" </c:if> >
            </div>      
            <div class="col-md-2 mb-2">
               <input class="form-control" type="text" id="filtro_preco_2" name="filtro_preco_2" readonly <c:if test="${requestScope.filtro_preco_2 != null}"> value="${requestScope.filtro_preco_2}" </c:if> >
            </div>
        </div>
       
        <div class="col-md-6 col-xs-12 form-group">
            <label for="quantIngressos" class="control-label">Quantidade Total de Ingressos:</label>
            <div id="slider_ingressos" width="50%"></div>
        </div>
        <div class="row">
            <div class="col-md-2 mb-2">
                <input class="form-control" type="text" id="filtro_ingressos_1" name="filtro_ingressos_1" readonly <c:if test="${requestScope.filtro_ingressos_1 != null}"> value="${requestScope.filtro_ingressos_1}" </c:if> >
            </div>
            <div class="col-md-2 mb-2">
                <input class="form-control" type="text" id="filtro_ingressos_2" name="filtro_ingressos_2" readonly <c:if test="${requestScope.filtro_ingressos_2 != null}"> value="${requestScope.filtro_ingressos_2}" </c:if> >
            </div>
        </div>
        <br>
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
                <th>Preço do Ingresso</th>
                <th>Quantidade Total de Ingressos</th>
                <th>Ingressos Vendidos</th>
                <th class="text-center">Ações</th>
            </tr>
        </thead>
        <tbody>
            
            <c:if test="${requestScope.eventos.size() == 0}">
                <tr>
                    <td class="text-center" colspan="8"> Não há eventos cadastrados para os filtros selecionados </td>
                </tr> 
            </c:if>
            
            <c:forEach var="e" items="${requestScope.eventos}">
                <tr>
                    <td>${e.tipoEvento}</td>
                    <td>${e.nome}</td>
                    <td>${e.local.nome}</td>
                    <td> <fmt:formatDate value="${e.horario}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td>R$ ${e.precoIngresso}</td>
                    <td>${e.quantIngressos}</td>
                    <td>${e.ingressos.size()}</td>
                    <td class="text-center text-nowrap">
                        <button type="button" class="btn btn-dark" data-toggle="tooltip" title="Ingressos" onclick="document.location='control?action=listaIngressos&evento=${e.idevento}'">
                            <i class="fas fa-ticket-alt"></i>
                        </button>
                        <button type="button" class="btn btn-dark" data-toggle="tooltip" title="Editar Evento" onclick="document.location='control?action=cadastraEvento&id=${e.idevento}'">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button type="button" class="btn btn-dark" data-toggle="tooltip" title="Cancelar Evento" onclick="document.location='control?action=cancelaEvento&id=${e.idevento}'">
                            <i class="fas fa-calendar-times"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>
                                                
<script>  
    $(document).ready(function(){
        var sliderPreco = document.getElementById('slider_preco');
        var sliderIngressos = document.getElementById('slider_ingressos');

        noUiSlider.create(sliderPreco, {
            start: [0, 1000],
            connect: true,
//            tooltips: true,
            step: 10,
            range: {
                'min': 0,
                'max': 1000
            }
        });
        
        sliderPreco.noUiSlider.on('update', function (values, handle) {

            $("#filtro_preco_1").val(values[0]);
            $("#filtro_preco_2").val(values[1]);

        });
        
        noUiSlider.create(sliderIngressos, {
            start: [0, 1000],
            connect: true,
//            tooltips: true,
            step: 10,
            range: {
                'min': 0,
                'max': 636
            }
        });
        
        sliderIngressos.noUiSlider.on('update', function (values, handle) {

            $("#filtro_ingressos_1").val(Number(values[0]));
            $("#filtro_ingressos_2").val(Number(values[1]));

        });
        
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