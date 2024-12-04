//abrir y cerrar modales
function abrirModal(modalId,containerId){
    document.getElementById(modalId).style.display = "block";
    document.getElementById(containerId).style.display = "none";
}

function cerrarModal(modalId, containerId){
    document.getElementById(containerId).style.display = "block";
    document.getElementById(modalId).style.display = "none";
}


//cambia parametros entre One y All en las consultas
function consultar(parametro){
    document.getElementById("accionConsultar").value = parametro;
}


//funcionalidad vender
//onclick boton "Finalizar Venta"
function vender(parametro){
    document.getElementById("accionVenta").value = parametro;
    //devolvemos false si no se han agregado productos a la lista
    const idProductoVenta = document.getElementById("idProductoVenta");
    const nombreCliente = document.getElementById("nombreCliente").value;
    if(idProductoVenta && nombreCliente){
        document.getElementById("form_venta").submit();
    } else{
        alert("Verifique que se hayan agregado productos o Ingresado el cliente");
        return false;
    }
}
//funcionalidades para ventas-cliente
function clienteVenta(accion){
    
    let cedula = $("#cedula").val();
    if (cedula) {
        $("#accionVenta").val(accion);
        // Enviar el formulario manualmente si la cédula está llena
        $("#form_venta").submit();
    } else {
        alert("Por favor, ingrese la cedula del cliente.");
        return false;
    }
}

// mostrar-ocultar container de NO stock
// Función para mostrar/ocultar el div con la lista de productos
function toggleDiv() {
    var div = document.getElementById("listaProductos");
    if (div.style.display === "none") {
        div.style.display = "block";
    } else {
        div.style.display = "none";
    }
}







