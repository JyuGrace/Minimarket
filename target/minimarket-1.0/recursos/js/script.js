const formulario = document.getElementById("formulario");
const inputs = document.querySelectorAll("#formulario input");
//obtenemos el parametro page
const urlParams = new URLSearchParams(window.location.search);
const page = urlParams.get('page');

const expresiones = {
    usuario: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
    nombre: /^[a-zA-Z\u00C0-\u00FF\s]{1,50}$/, // Letras y espacios, pueden llevar acentos.
    apellido: /^[a-zA-Z\u00C0-\u00FF\s]{1,50}$/, // Letras y espacios, pueden llevar acentos.
    password: /^.{4,12}$/, // 4 a 12 digitos.
    email: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    telefono: /^\d{7,10}$/, // 7 a 10 numeros.
    cedula: /^\d{6,11}$/, // 6 a 11 numeros.
    direccion: /^[a-zA-Z0-9\s\.,#\/-]{1,100}$/, // Letras, espacios, puntos, comas, numerales y barras laterales
    stock: /^\d{1,3}$/, // 1 a 3 numeros.
    precio: /^\d+(\.\d{1,2})?/, //numeros con punto
    nombre_empresa: /^[a-zA-Z\u00C0-\u00FF\s]{1,50}$/ // Letras y espacios, pueden llevar acentos.
};
//    /^\d{1,6}$/, // 1 a 6 numeros.
//    /^\d+(\.\d{1,2})?/, //numeros con punto

const campos = {
    usuario: false,
    nombre: false,
    apellido: false,
    password: false,
    email: false,
    telefono: false,
    cedula: false,
    direccion: false,
    fecha: false,
    stock: false,
    precio: false,
    nombre_empresa: false
};

const validarFormulario = (e) => {
    switch (e.target.name) {
        case "usuario":
            validarCampo(expresiones.usuario, e.target, "usuario");
            break;
        case "nombre":
            validarCampo(expresiones.nombre, e.target, "nombre");
            break;
        case "apellido":
            validarCampo(expresiones.apellido, e.target, "apellido");
            break;
        case "password":
            validarCampo(expresiones.password, e.target, "password");
            validarPassword2();
            break;
        case "password2":
            validarPassword2();
            break;
        case "email":
            validarCampo(expresiones.email, e.target, "email");
            break;
        case "telefono":
            validarCampo(expresiones.telefono, e.target, "telefono");
            break;
        case "cedula":
            validarCampo(expresiones.cedula, e.target, "cedula");
            break;
        case "direccion":
            validarCampo(expresiones.direccion, e.target, "direccion");
            break;
        case "stock":
            validarCampo(expresiones.stock, e.target, "stock");
            break;
        case "precio":
            validarCampo(expresiones.precio, e.target, "precio");
            break;
        case "nombre_empresa":
            validarCampo(expresiones.nombre_empresa, e.target, "nombre_empresa");
            break;
    }
};

const validarCampo = (expresion, input, campo) => {
    if (expresion.test(input.value)) {
        document
                .getElementById(`grupo__${campo}`)
                .classList.remove("formulario__grupo-incorrecto");
        document
                .getElementById(`grupo__${campo}`)
                .classList.add("formulario__grupo-correcto");
        document
                .querySelector(`#grupo__${campo} i`)
                .classList.add("fa-check-circle");
        document
                .querySelector(`#grupo__${campo} i`)
                .classList.remove("fa-times-circle");
        document
                .querySelector(`#grupo__${campo} .formulario__input-error`)
                .classList.remove("formulario__input-error-activo");
        campos[campo] = true;
    } else {
        document
                .getElementById(`grupo__${campo}`)
                .classList.add("formulario__grupo-incorrecto");
        document
                .getElementById(`grupo__${campo}`)
                .classList.remove("formulario__grupo-correcto");
        document
                .querySelector(`#grupo__${campo} i`)
                .classList.add("fa-times-circle");
        document
                .querySelector(`#grupo__${campo} i`)
                .classList.remove("fa-check-circle");
        document
                .querySelector(`#grupo__${campo} .formulario__input-error`)
                .classList.add("formulario__input-error-activo");
        campos[campo] = false;
    }
};

const validarPassword2 = () => {
    const inputPassword1 = document.getElementById("password");
    const inputPassword2 = document.getElementById("password2");

    if (inputPassword1.value !== inputPassword2.value) {
        document
                .getElementById(`grupo__password2`)
                .classList.add("formulario__grupo-incorrecto");
        document
                .getElementById(`grupo__password2`)
                .classList.remove("formulario__grupo-correcto");
        document
                .querySelector(`#grupo__password2 i`)
                .classList.add("fa-times-circle");
        document
                .querySelector(`#grupo__password2 i`)
                .classList.remove("fa-check-circle");
        document
                .querySelector(`#grupo__password2 .formulario__input-error`)
                .classList.add("formulario__input-error-activo");
        campos["password"] = false;
    } else {
        document
                .getElementById(`grupo__password2`)
                .classList.remove("formulario__grupo-incorrecto");
        document
                .getElementById(`grupo__password2`)
                .classList.add("formulario__grupo-correcto");
        document
                .querySelector(`#grupo__password2 i`)
                .classList.remove("fa-times-circle");
        document
                .querySelector(`#grupo__password2 i`)
                .classList.add("fa-check-circle");
        document
                .querySelector(`#grupo__password2 .formulario__input-error`)
                .classList.remove("formulario__input-error-activo");
        campos["password"] = true;
    }
};

// Ejecutar la validación para todos los campos cuando el DOM esté completamente cargado.
function validateCamposOnLoad(){
document.addEventListener("DOMContentLoaded", () => {
    inputs.forEach((input) => {
        validarFormulario({ target: input });
    });
});
};
validateCamposOnLoad();
inputs.forEach((input) => {
    input.addEventListener("keyup", validarFormulario);
    input.addEventListener("blur", validarFormulario);
});

function validate() {
    // Selecciona los inputs del formulario dentro del modal
    const inputs = document.querySelectorAll('#editar_cliente input');

    // Itera sobre cada input y valida
    inputs.forEach((input) => {
        validarFormulario({ target: input });
    });
}


//funcion que presenta mensaje de exito al validar le formulario
function formValid() {
    //formulario.reset();

    document
            .getElementById("formulario__mensaje-exito")
            .classList.add("formulario__mensaje-exito-activo");
    setTimeout(() => {
        document
                .getElementById("formulario__mensaje-exito")
                .classList.remove("formulario__mensaje-exito-activo");
    }, 3000);

    document.querySelectorAll(".formulario__grupo-correcto").forEach((icono) => {
        icono.classList.remove("formulario__grupo-correcto");
    });
}

//funcion que presenta mensaje de error al no poder validar el formulario
function formNotValid() {
    document
            .getElementById("formulario__mensaje")
            .classList.add("formulario__mensaje-activo");

    setTimeout(() => {
        document
                .getElementById("formulario__mensaje")
                .classList.remove("formulario__mensaje-activo");
    }, 3000);
}
;


//EMPEZAMOS LA PRUEBA DE 
formulario.addEventListener("submit", (e) => {
    e.preventDefault();

    switch (page) {
        case "registrarCliente":
            let valRegClient =
                    campos.nombre &&
                    campos.apellido &&
                    campos.cedula &&
                    campos.email &&
                    campos.telefono &&
                    campos.direccion;
            
            if (valRegClient) {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert(page);
            }
            break;
        case "registrarProducto":
            const categoriaProducto = document.getElementById("producto_categoria").value;
            let valRegProducto =
                    campos.nombre &&
                    campos.stock &&
                    campos.precio;

            if (valRegProducto && categoriaProducto !== "") {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert("no se valido el formulario");
            }
            break;
        case "registrarProveedor":
            let valRegProveedor =
                    campos.nombre &&
                    campos.apellido &&
                    campos.nombre_empresa &&
                    campos.email &&
                    campos.telefono;

            if (valRegProveedor) {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert("no se valido el formulario");
            }
            break;
        case "registrarEmpleado":
            const tipoEmpleado = document.getElementById("producto_tipoEmpleado").value;
            let valRegEmpleado =
                    campos.nombre &&
                    campos.apellido &&
                    campos.cedula &&
                    campos.usuario &&
                    campos.password &&
                    campos.email &&
                    campos.telefono &&
                    campos.direccion;

            if (valRegEmpleado && tipoEmpleado !== "") {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert("no se valido el formulario");
            }
            break;
        case "consultarCliente":
            let valConClient =
                    campos.nombre &&
                    campos.apellido &&
                    campos.cedula &&
                    campos.email &&
                    campos.telefono &&
                    campos.direccion;
            if (valConClient) {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
            }
            break;
        case "consultarProducto":
            const categoryProducto = document.getElementById("producto_categoria").value;
            let valConProducto =
                    campos.nombre &&
                    campos.stock &&
                    campos.precio;
            if (valConProducto && categoryProducto !== "") {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert("no se valido el formulario");
            }
            break;
        case "consultarProveedor":
            let valConProveedor =
                    campos.nombre &&
                    campos.apellido &&
                    campos.nombre_empresa &&
                    campos.email &&
                    campos.telefono;

            if (valConProveedor) {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert("no se valido el formulario");
            }
            break;
        case "consultarEmpleado":
            const typeEmpleado = document.getElementById("producto_tipoEmpleado").value;
            let valConEmpleado =
                    campos.nombre &&
                    campos.apellido &&
                    campos.cedula &&
                    campos.usuario &&
                    campos.password &&
                    campos.email &&
                    campos.telefono &&
                    campos.direccion;

            if (valConEmpleado && typeEmpleado !== "") {
                formValid();
                formulario.submit();
            } else {
                formNotValid();
                alert("no se valido el formulario");
            }
            break;
        default:
            
            break;
    }
});



