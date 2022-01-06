
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row justify-content-md-center">
    <h1 class="bg-dark text-light border rounded px-2">Cadastra Ingresso</h1>
</div>
<form action="control" method="POST">    
    <div class="col-xs-12 form-group">
        <label for="evento" class="control-label">Evento</label>
        <select name="evento" id="evento" class="form-control" onchange="carregaLocalAnfiteatro(this)" required>
            <option value="-1"></option>
            <c:forEach var="e" items="${requestScope.eventos}">
                <option value="${e.idevento}" local="${e.local.idlocal}" preco="${e.precoIngresso}">${e.nome} - <fmt:formatDate value="${e.horario}" pattern="dd/MM/yyyy HH:mm"/></option>
            </c:forEach>
        </select>
    </div>
    <div class="col-xs-12 form-group">
        <label for="tipo_entrada" class="control-label mr-2">Entrada:</label>
        <div class="form-check form-check-inline">
            <input type="radio" id="tipo_inteira" name="tipo_entrada" value="inteira" required>
            <label class="form-check-label">Inteira</label>
        </div>
        <div class="form-check form-check-inline">
            <input type="radio" id="tipo_meia" name="tipo_entrada" value="meia" required>
            <label class="form-check-label">Meia</label>
        </div>
        <div class="form-check form-check-inline">
            <input type="radio" id="tipo_doacao" name="tipo_entrada" value="doacao" required>
            <label class="form-check-label">Doação</label>
        </div>
    </div>
    <div class="col-xs-12 from-group" id="local"></div>
    <button class="btn btn-dark mt-3" type="submit" name="action" value="pagaIngresso">Continuar para o Pagamento</button>
</form>

<script>
    
    function carregaCabines(){
        $.ajax({
            url: "control",
            method: "POST",
            data: {
                'action': 'ajaxCarregaCabines',
                'evento': $("#evento").val()
            },
            success: function(retorno){
                $("#lugar").html(retorno);
            },
            erro: function(ex){
                alert("Ocorreu um erro!");
                console.log(ex);
            }
        })
    }
    
    function carregaSetor(){
        $.ajax({
            url: "control",
            method: "POST",
            data: {
                'action': 'ajaxCarregaSetor'
            },
            success: function(retorno){
                $("#lugar").html(retorno);
            },
            erro: function(ex){
                alert("Ocorreu um erro!");
                console.log(ex);
            }
        })
    }
    
    function carregaAssentos(setor){
        $("#setor").val(setor);
        $.ajax({
            url: "control",
            method: "POST",
            data: {
                'action': 'ajaxCarregaAssentos',
                'evento': $("#evento").val(),
                'setor': setor
            },
            success: function(retorno){
                $("#assentos").html(retorno);
            },
            erro: function(ex){
                alert("Ocorreu um erro!");
                console.log(ex);
            }
        })
    }
    
    function escolheAssento(element, assento){
        if($(element).hasClass("bg-light")){    
            $(element).removeClass("bg-light text-dark");
            $(element).children("input").prop("disabled", true)
        } else {
            $(element).addClass("bg-light text-dark");
            $(element).children("input").prop("disabled", false)
        }        
    }
    
    function carregaLocalAnfiteatro(evento){
        if($(evento).val() != "-1"){
            if($(evento).children("option:selected").attr("local") == 2){
                url = "ajaxCarregaLocalAnfiteatro";
            } else {
                url = "ajaxCarregaQuantidade";
            }
            $.ajax({
                url: "control",
                method: "POST",
                data: {
                    'action': url,
                    'evento': $("#evento").val()
                },
                success: function(retorno){
                    $("#local").html(retorno);
                },
                erro: function(ex){
                    alert("Ocorreu um erro!");
                    console.log(ex);
                }
            })    
        
            preco = Number($(evento).children("option:selected").attr("preco"));
            if(preco == 0){
                $("#tipo_inteira").prop("disabled", true);
                $("#tipo_meia").prop("disabled", true);
                $("#tipo_doacao").prop("disabled", false);
                $("#tipo_doacao").prop("checked", true);
            } else {
                $("#tipo_inteira").prop("disabled", false);
                $("#tipo_meia").prop("disabled", false);
                $("#tipo_doacao").prop("checked", false);
                $("#tipo_doacao").prop("disabled", true);
            }
        } else {
            $("#local").html("");
        }
    }
    
</script>
