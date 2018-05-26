$(document).ready(function() {
  var usuario=$();

    $.ajax({
    type: "GET",
    url: "ClienteBusquedaServlet",
    dataType: "json",
    data: {tipoBusq: valorTipoFiltro, codigoCliente: varCodigoCliente},
    success: function(data){
        $.each(data, function(i,item){ 
        $("#busqHidCliente").val(item.n_codigo_cliente); //Setear el campo nombre con el valor obtenido de la funcion ajax y el campo hidden
        $("#busqTxtNombre").val(item.v_nombre_cliente);
    });
    }
});

});