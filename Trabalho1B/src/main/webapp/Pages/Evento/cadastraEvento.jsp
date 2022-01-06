<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">${requestScope.titulo}</h1>
</div>

<form action="control" method="POST">
    <input type="hidden" name="idEv" value="${requestScope.evento.idevento}" />
    <div class="col-xs-12 form-group">
        <label for="tipoEvento" class="control-label">Tipo de Evento</label>
        <select class="form-control" name="tipoEvento" id="tipo_evento" required>           
            <option value=""> Selecione... </option>
            <option value="Curso" <c:if test="${requestScope.evento.tipoEvento eq 'Curso'}"> selected </c:if> >  Curso </option>
            <option value="Palestra" <c:if test="${requestScope.evento.tipoEvento eq 'Palestra'}"> selected </c:if> >  Palestra </option>
            <option value="Teatro" <c:if test="${requestScope.evento.tipoEvento eq 'Teatro'}"> selected </c:if> >  Teatro </option>
            <option value="Show" <c:if test="${requestScope.evento.tipoEvento eq 'Show'}"> selected </c:if> >  Show </option>
            <option value="Mostra Cultural" <c:if test="${requestScope.evento.tipoEvento eq 'Mostra Cultural'}"> selected </c:if> >  Mostra Cultural </option>            
        </select>
    </div>
    <div class="col-xs-12 form-group">
        <label for="nomeEvento" class="control-label">Nome do Evento:</label>
        <input type="text" class="form-control" name="nomeEvento" id="nomeEvento" required value="${requestScope.evento.nome}">
    </div>    
    <div class="col-xs-12 form-group">
        <label class="control-label mr-2">Local: </label>
        <div class="form-check form-check-inline">
            <input type="radio" class="form-check-input" name="local" value="1" required
                <c:if test="${requestScope.evento.local.idlocal == 1}"> checked onload="rangeIngressosSalao(${requestScope.salao.capacidadeMax})" </c:if>
                <c:if test="${requestScope.titulo eq 'Alterar Evento'}"> onclick="javascript: return false;" </c:if>
                onchange="rangeIngressosSalao(${requestScope.salao.capacidadeMax})" 
            >
            <label class="form-check-label" for="local">Salão</label>
        </div>
        <div class="form-check form-check-inline">
            <input type="radio" class="form-check-input" name="local" value="2" required 
                <c:if test="${requestScope.evento.local.idlocal == 2}"> checked onload="rangeIngressosAT(${requestScope.anfiteatro.capacidadeMax})" </c:if>
                <c:if test="${requestScope.titulo eq 'Alterar Evento'}"> onclick="javascript: return false;" </c:if> 
                onchange="rangeIngressosAT(${requestScope.anfiteatro.capacidadeMax})" 
            >
            <label class="form-check-label" for="local">Anfiteatro</label>
        </div>
    </div>
    <div class="col-xs-12 form-group">
        <label for="max_ingressos" class="control-label">Quantidade Total de Ingressos: 
        </label>
        <input type="range" class="form-control-range" name="max_ingressos" id="max_ingressos" required disabled oninput="atualizaRange(this)">   
        <input type="text" class="form-control w-25" id="max_ingressos_input" name="max_ingressos_input" required readonly value="${requestScope.evento.quantIngressos}">
    </div>
    <div class="col-xs-12 form-group">
        <label class="control-label mr-2"> Evento possuirá entrada por Doação? </label>
        <div class="form-check form-check-inline">
            <input type="radio" class="form-check-input" name="doacao" value="true" required
                <c:if test="${requestScope.evento.precoIngresso == 0}"> checked onload="habilitaPreco(false)" </c:if>
                <c:if test="${requestScope.titulo eq 'Alterar Evento'}"> onclick="javascript: return false;" </c:if>
                onchange="habilitaPreco(false)" 
            >
            <label class="form-check-label" for="doacao"> Sim </label>
        </div>
        <div class="form-check form-check-inline">
            <input type="radio" class="form-check-input" name="doacao" value="false" required 
                <c:if test="${requestScope.evento.precoIngresso > 2}"> checked onload="habilitaPreco(true)" </c:if>
                <c:if test="${requestScope.titulo eq 'Alterar Evento'}"> onclick="javascript: return false;" </c:if> 
                onchange="habilitaPreco(true)" 
            >
            <label class="form-check-label" for="doacao"> Não </label>
        </div>
    </div>
    <div class="col-xs-12 form-group">
        <label for="preco" class="control-label">Preço do Ingresso: </label>
        <input type="number" class="form-control" name="preco" id="preco" step="0.01" min="0" max="1000" placeholder="00,00" required 
            <c:if test="${requestScope.titulo eq 'Novo Evento'}" > value="0.0" </c:if> 
            <c:if test="${requestScope.titulo eq 'Alterar Evento'}"> readonly value="${requestScope.evento.precoIngresso}" </c:if> 
        >
    </div>
    <div class="col-xs-12 form-group">
        <label for="data" class="control-label">Data e Horário do Evento:</label>
        <div class="input-group" required>
            <input type="text"  class="form-control w-90" name="data" id="data" required readonly value="<fmt:formatDate value="${requestScope.evento.horario}" pattern="dd/MM/yyyy HH:mm"/>">
            <div class="input-group-append">
                <span class="input-group-text"><i class="fas fa-calendar-alt"></i></span>
            </div>
            
        </div>
            <span id="validacao" class="text-center text-danger"> </span>
    </div>   
    <div class="col-xs-12">
        <c:if test="${requestScope.titulo eq 'Novo Evento'}">
            <button type="submit" name="action" value="insertEvento" class="btn btn-dark">Inserir</button>
        </c:if>
        <c:if test="${requestScope.titulo eq 'Alterar Evento'}">
            <button type="submit" name="action" value="updateEvento" class="btn btn-dark">Atualizar</button>
        </c:if>
    </div>
</form>
    
<script>
    $(document).ready(function(){        
        var today = new Date();
        var minDate = today.getDate() + 1;
        
        if(${requestScope.titulo eq 'Alterar Evento'}){
            minDate = '<fmt:formatDate value="${requestScope.evento.horario}" pattern="dd/MM/yyyy HH:mm"/>';
        }
        
        $('#data').daterangepicker({                       
            "singleDatePicker": true,            
            "minDate": minDate.toString(),
            "timePicker": true,
            "drops": 'up',
            "timePicker24Hour": true,
            "autoUpdateInput": false,
            "locale": {
                "format": "DD/MM/YYYY HH:mm"
            }         
        });
        
        $('#data').on('apply.daterangepicker', function(ev, picker) {
            
            $.ajax({
                url: "control",
                method: "POST",
                data: {
                    'data': picker.startDate.format('DD/MM/YYYY HH:mm'),
                    'action': 'ajaxValidaData'
                },
            
                success: function(retorno){
                    if(retorno == 1){
                        $('#data').val(picker.startDate.format('DD/MM/YYYY HH:mm'));
                        $("#validacao").html("");
                    } else {
                        $('#data').val('');
                        $("#validacao").html("Esta data não está disponível! Por favor, escolha outra!");
                    }
                },
                erro: function(ex){
                    console.log(ex);
                }
            });
        });

        $('#data').on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });
                        
    });

    function rangeIngressosSalao(qtd){
        $("#max_ingressos").prop('disabled',false);
        $("#max_ingressos").attr('max', qtd);
        $("#max_ingressos").attr('min', 10);
        atualizaRange($("#max_ingressos"));
    }
    
    function rangeIngressosAT(qtd){
        $("#max_ingressos").prop('disabled',false);
        $("#max_ingressos").attr('max', qtd);
        $("#max_ingressos").attr('min', qtd);
        atualizaRange($("#max_ingressos"));
    }
    
    function atualizaRange(range){
        $("#max_ingressos_input").val($(range).val());
    }
    
    function habilitaPreco(habilitar){
        if(habilitar){
            $("#preco").prop('onclick', true);
            $("#preco").prop('readonly', false);
            $("#preco").prop('min', 5);
        }else{
            $("#preco").prop('onclick', false);
            $("#preco").prop('readonly', true);
            $("#preco").val(0.0);
            $("#preco").prop('min', 0);
        }
    }
         
</script>