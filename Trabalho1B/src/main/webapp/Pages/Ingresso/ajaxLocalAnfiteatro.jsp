<%@page contentType="text/html" pageEncoding="UTF-8"%>

<label for="Lugar" class="control-label mr-2">Local: </label>
<input type="radio" name="lugar" value="plateia" id="plateia" onchange="carregaSetor()" required>
<label for="plateia" class="control-label">Plateia</label>
<input type="radio" name="lugar" value="cabine" id="cabine" onchange="carregaCabines()" required>
<label for="cabine" class="control-label">Cabine</label>
<div class="form-group" id="lugar">
</div>