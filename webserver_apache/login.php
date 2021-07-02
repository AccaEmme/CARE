<html>
 <head>
  <title>CARE - Login</title>
  <script src="./XMLHTTPRequest.js"></script>
  <link rel="stylesheet" href="./css/login.css">
 </head>
 <body>
    <div id="niceframe">
            <img src="images/logoLogin.png" alt="logo"/>
            <form method="POST" action="#" autocomplete="off">
                <input type="text" name="username" id="username" placeholder="Username" />
                <br/>
                <input type="password" name="psw" id="psw" placeholder="Password"/>
                <br/>
                <small><a href="javascript:alert('Al momento per recuperare le tue credenziali bisogna inviare una e-mail al Centro Elaborazione Dati di competenza.')">Recupera Password</a></small>
                <div id="niceframe">
                    <input type="submit" value="LOGIN" name="login_btn" id="login_btn" onclick="login()" />
                </div>
            </form>
    </div>
 </body>
</html>