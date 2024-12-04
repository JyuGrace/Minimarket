<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <link rel="stylesheet" href="recursos/css/login.css"/>
    </head>

    <body>
        <section>
            <div class="container_2">
                <div class="form-box">
                    <div class="form-value">
                        <form action="${pageContext.request.contextPath}/ServletControladorLogIn" method="post">
                            <h1>Beto&Slendy</h1>
                            <h2>Login</h2>

                            <div class="inputbox">
                                <ion-icon name="person-outline"></ion-icon>
                                <input type="text" name="usuario" required id="user" />
                                <label for="usuario">Usuario</label>
                            </div>

                            <div class="inputbox">
                                <ion-icon name="lock-closed-outline"></ion-icon>
                                <input type="password" name="password" required id="password" />
                                <label for="password">Password</label>
                            </div>

                            <div class="forget">
                                <label><input type="checkbox" />Recuerdame</label>
                                <a href="#">Olvido su contraseña</a>
                            </div>
                            <button>Iniciar</button>
                            <div class="register">
                                <p>No tiene una cuenta? <a href="#">Registrarse</a></p>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- Mensaje de error si las credenciales son incorrectas -->
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <div class="error-message" id="login-error-message">
                    <%= error%>
                </div>
                <%
                    }
                %>
            </div>

        </section>
    </body>
    <script src="recursos/js/login.js"></script>
</html>

