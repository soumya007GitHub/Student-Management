<%
if(session.getAttribute("admin") != null){
 response.sendRedirect("student");
    return;
}
%>
<html>

<head>
    <title>Student Management</title>
</head>
<style>
    .container{
        width:100%;
        height: 100vh;
        display:flex;
        justify-content:center;
        align-items:center;
        flex-direction: column;
    }
    h2{
        font-size: 2rem;
        margin-bottom: 1rem;
        font-family: Arial, sans-serif;
        font-weight: bold;
    }
    form{
        width: 30%;
        height: auto;
        display:flex;
        flex-direction:column;
        justify-content:center;
        padding: 1rem 0.5rem;
        border-radius: 1rem;
        box-shadow: 0px 0px 5px 0px #888888;
    }
    input{
        border: 1px solid #000;
        padding: 0.5rem;
        outline: none;
        border-radius: 0.5rem;
    }
    button{
        padding: 0.5rem;
        border-radius: 0.5rem;
        border: none;
        background-color: rgb(66, 66, 194);
        color: #fff;
    }
    h4{
    color: red;
    }
</style>
<body>
    <div class="container">
        <h2>Login</h2>

        <form method="post" action="/admin">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="e.g. johny" required /><br />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="e.g. %*&!*" required /><br />
            <input type="hidden" name="action" value="login" />
            <button type="submit">Login</button>
        </form>
        <h4><em><%= session.getAttribute("error") != null ? session.getAttribute("error") : ""%></em></h4>
    </div>
</body>

</html>