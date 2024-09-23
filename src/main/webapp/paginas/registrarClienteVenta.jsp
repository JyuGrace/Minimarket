<main>
    <div id="">
        <form action="${pageContext.request.contextPath}/ServletControladorVentas" class="formulario" method="post" id="formulario">
            <h1 id="titulo_form">Registrar Cliente</h1>
            <!-- valor oculto -->
            <input type="hidden" name="page" value="ventas"/>
            <input type="hidden" name="accion" value="registrarCliente"/>
            <!-- Grupo: Nombre -->
            <div class="formulario__grupo" id="grupo__nombre">
                <label for="nombre" class="formulario__label">Nombres</label>
                <div class="formulario__grupo-input">
                    <input type="text" class="formulario__input" name="nombre" id="nombre" placeholder="Ingrese el nombre" requiered/>
                    <i class="formulario__validacion-estado fas fa-times-circle"></i>
                </div>
                <p class="formulario__input-error">
                    El nombre tiene que ser de 4 a 16 dígitos.
                </p>
            </div>

            <!-- Grupo: Apellido -->
            <div class="formulario__grupo" id="grupo__apellido">
                <label for="apellido" class="formulario__label">Apellidos</label>
                <div class="formulario__grupo-input">
                    <input type="text" class="formulario__input" name="apellido" id="apellido" placeholder="Doe" requiered/>
                    <i class="formulario__validacion-estado fas fa-times-circle"></i>
                </div>
                <p class="formulario__input-error">
                    El apellido tiene que ser de 4 a 16 dígitos.
                </p>
            </div>

            <!-- Grupo: cedula -->
            <div class="formulario__grupo" id="grupo__cedula">
                <label for="cedula" class="formulario__label">C.C:</label>
                <div class="formulario__grupo-input">
                    <input type="text" class="formulario__input" name="cedula" id="cedula" placeholder="ingrese la cedula" requiered/>
                    <i class="formulario__validacion-estado fas fa-times-circle"></i>
                </div>
                <p class="formulario__input-error">
                    La cedula solo puede contener numeros (maximo 10).
                </p>
            </div>


            <!-- Grupo: Correo Electronico -->
            <div class="formulario__grupo" id="grupo__correo">
                <label for="email" class="formulario__label">Correo Electrónico</label>
                <div class="formulario__grupo-input">
                    <input type="email" class="formulario__input" name="email" id="email" placeholder="correo@correo.com" />
                    <i class="formulario__validacion-estado fas fa-times-circle"></i>
                </div>
                <p class="formulario__input-error">
                    El correo solo puede contener letras, numeros, puntos, guiones y
                    guion bajo.
                </p>
            </div>

            <!-- Grupo: Teléfono -->
            <div class="formulario__grupo" id="grupo__telefono">
                <label for="telefono" class="formulario__label">Teléfono</label>
                <div class="formulario__grupo-input">
                    <input  type="text" class="formulario__input" name="telefono" id="telefono" placeholder="ingrese el telefono" />
                    <i class="formulario__validacion-estado fas fa-times-circle"></i>
                </div>
                <p class="formulario__input-error">
                    El telefono solo puede contener numeros y el maximo son 10 dígitos.
                </p>
            </div>

            <!-- Grupo: Direccion -->
            <div class="formulario__grupo" id="grupo__direccion">
                <label for="direccion" class="formulario__label">Direccion</label>
                <div class="formulario__grupo-input">
                    <input type="text" class="formulario__input" name="direccion" id="direccion" placeholder="123 Main Street, Anytown, USA" />
                    <i class="formulario__validacion-estado fas fa-times-circle"></i>
                </div>
                <p class="formulario__input-error">
                    La direccion puede contener numeros, letras, puntos, comas, barras
                    laterales y nuemrales, maximo # de caracteres, 100.
                </p>
            </div>


            <div class="formulario__mensaje" id="formulario__mensaje">
                <p>
                    <i class="fas fa-exclamation-triangle"></i> <b>Error:</b> Por favor
                    rellena el formulario correctamente.
                </p>
            </div>

            
            <div id="container_botones_consulta">
                    <div class="formulario__grupo formulario__grupo-btn-enviar">
                        <button type="submit" class="formulario__btn">Registrar</button>
                        <p class="formulario__mensaje-exito" id="formulario__mensaje-exito">
                            Formulario enviado exitosamente!
                        </p>
                    </div>
                    <div class="formulario__grupo formulario__grupo-btn-enviar">
                        <button type="button" class="formulario__btn" onclick="cerrarModal('registrarClienteVenta','ventasContainer')">Cerrar</button>
                    </div>
                </div>
        </form>
    </div>
</main>
