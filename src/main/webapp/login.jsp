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
            <div class="form-box">
                <div class="form-value">
                    <form action="${pageContext.request.contextPath}/ServletControladorLogIn" method="post">
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
        </section>
    </body>

</html>

