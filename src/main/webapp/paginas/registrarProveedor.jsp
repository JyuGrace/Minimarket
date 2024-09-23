<main>
    <form action="${pageContext.request.contextPath}/ServletControladorProveedores?page=registrarProveedor&accion=insertar" class="formulario" id="formulario" method="post">
        <h1 id="titulo_form">Registrar Proveedor</h1>
        <!-- Grupo: Nombre -->
        <div class="formulario__grupo" id="grupo__nombre">
            <label for="nombre" class="formulario__label">Nombres</label>
            <div class="formulario__grupo-input">
                <input type="text" class="formulario__input" name="nombre" id="nombre" placeholder="John" requiered="true"/>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">El nombre tiene que ser de 4 a 16 dígitos.</p>
        </div>

        <!-- Grupo: Apellido -->
        <div class="formulario__grupo" id="grupo__apellido">
            <label for="apellido" class="formulario__label">Apellidos</label>
            <div class="formulario__grupo-input">
                <input type="text" class="formulario__input" name="apellido" id="apellido" placeholder="Doe" requiered="true" />
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">El apellido tiene que ser de 4 a 16 dígitos.</p>
        </div>

        <!-- Grupo: Nombre Empresa-->
        <div class="formulario__grupo" id="grupo__nombre_empresa">
            <label for="nombre_empresa" class="formulario__label">Nombre de la Empresa</label>
            <div class="formulario__grupo-input">
                <input type="text" class="formulario__input" name="nombre_empresa" id="nombre_empresa" placeholder="Colombina"/>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">El nombre tiene que ser de 4 a 16 dígitos.</p>
        </div>

        <!-- Grupo: Correo Electronico -->
        <div class="formulario__grupo" id="grupo__email">
            <label for="correo" class="formulario__label">Email</label>
            <div class="formulario__grupo-input">
                <input type="email" class="formulario__input" name="email" id="email" placeholder="correo@correo.com"/>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">El correo solo puede contener letras, numeros, puntos, guiones y guion bajo.</p>
        </div>

        <!-- Grupo: Teléfono -->
        <div class="formulario__grupo" id="grupo__telefono">
            <label for="telefono" class="formulario__label">Teléfono</label>
            <div class="formulario__grupo-input">
                <input type="text" class="formulario__input" name="telefono" id="telefono" placeholder="3104578412" />
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">El telefono solo puede contener numeros y el maximo son 10 dígitos.</p>
        </div>

        <div class="formulario__mensaje" id="formulario__mensaje">
            <p>
                <i class="fas fa-exclamation-triangle"></i> <b>Error:</b> Por favor
                rellena el formulario correctamente.
            </p>
        </div>

        <div class="formulario__grupo formulario__grupo-btn-enviar">
            <button type="submit" class="formulario__btn">Enviar</button>
            <p class="formulario__mensaje-exito" id="formulario__mensaje-exito">
                Formulario enviado exitosamente!
            </p>
        </div>
    </form>
</main>
