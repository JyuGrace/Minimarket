<main>
    <form action="${pageContext.request.contextPath}/ServletControladorProductos?page=registrarProducto&accion=insertar" class="formulario" id="formulario" method="post">
        <h1 id="titulo_form">Registrar Producto</h1>
        <!-- Grupo: Nombre -->
        <div class="formulario__grupo" id="grupo__nombre">
            <label for="nombre" class="formulario__label">Nombre</label>
            <div class="formulario__grupo-input">
                <input type="text" class="formulario__input" name="nombre" id="nombre" placeholder="manzana"requiered="true"/>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">El nombre del producto tiene que ser de 4 a 16 dígitos.</p>
        </div>

        <!-- Grupo: Categoria -->
        <div class="formulario__grupo" id="grupo__categoria">
            <label for="categoria" class="formulario__label">Categoria</label>
            <div class="formulario__grupo-input">
                <select class="formulario__input" name="categoria" id="producto_categoria" required="true">
                    <option value="">--Seleccionar--</option>
                    <option value="1">Frutas</option>
                    <option value="2">Verduras</option>
                    <option value="3">Carnes</option>
                    <option value="4">Lácteos</option>
                    <option value="5">Panadería</option>
                    <option value="6">Bebidas</option>
                    <option value="7">Productos de limpieza</option>
                    <option value="8">Productos de cuidado personal</option>
                    <option value="9">Miscelánea</option>
                    <option value="10">Legumbres</option>
                    <option value="11">Cereales</option>
                </select>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">Seleccione una categoria de producto.</p>
        </div>

        <!-- Grupo: stock -->
        <div class="formulario__grupo" id="grupo__stock">
            <label for="stock" class="formulario__label">Stock</label>
            <div class="formulario__grupo-input">
                <input type="number" class="formulario__input" name="stock" id="stock" placeholder="10"/>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">Indique el numero de existencias a agregar.</p>
        </div>

        <!-- Grupo: Precio -->
        <div class="formulario__grupo" id="grupo__precio">
            <label for="precio" class="formulario__label">Precio</label>
            <div class="formulario__grupo-input">
                <input type="number" class="formulario__input" name="precio" id="precio" placeholder="2000"/>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">Indique le precio del producto.</p>
        </div>

        <!-- Grupo: Descripcion -->
        <div class="formulario__grupo" id="grupo__telefono">
            <label for="telefono" class="formulario__label">Descripcion del Producto.</label>
            <div class="formulario__grupo-input">
                <textarea class="formulario__input" name="descripcion" id="descripcion" cols="40" rows="10"></textarea>
                <i class="formulario__validacion-estado fas fa-times-circle"></i>
            </div>
            <p class="formulario__input-error">Ingrese la descripcion del producto.</p>
        </div>

        <div class="formulario__mensaje" id="formulario__mensaje">
            <p>
                <i class="fas fa-exclamation-triangle"></i> <b>Error:</b> Por favor
                rellena el formulario correctamente.
            </p>
        </div>

        <div class="formulario__grupo formulario__grupo-btn-enviar">
            <button type="submit" class="formulario__btn">Enviar</button>
            <p class="formulario__mensaje-exito" id="formulario__mensaje-exito">Formulario enviado exitosamente!</p>
        </div>
    </form>
</main>
