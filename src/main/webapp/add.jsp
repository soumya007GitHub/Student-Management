<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
if(session == null || session.getAttribute("admin") == null){
response.sendRedirect("admin");
return;
}
%>
<html>
<head>
    <title>Add Student</title>

    <style>
        body{
            padding:0;
            margin:0;
            box-sizing:border-box;
            font-family:Arial, Helvetica, sans-serif;
            background:#f5f5f5;
        }

        nav{
            height:50px;
            background-color:rgb(98,98,98);
            display:flex;
            justify-content:space-between;
            align-items:center;
            color:#fff;
            box-shadow:0px 0px 5px #888;
            padding:0px 1rem;
        }

        nav ul{
            display:flex;
            width:30%;
            justify-content:space-between;
            list-style:none;
        }

        a{
            color:white;
            text-decoration:none;
        }

        .container{
            width:40%;
            margin:30px auto;
            background:white;
            padding:20px;
            border-radius:10px;
            box-shadow:0px 0px 5px gray;
        }

        h2{
            text-align:center;
        }

        input,select,textarea{
            width:100%;
            padding:10px;
            margin:8px 0 18px;
            box-sizing:border-box;
        }

        button{
            width:100%;
            padding:10px;
            background:green;
            color:white;
            border:none;
            cursor:pointer;
            font-size:16px;
        }
    </style>

</head>

<body>

<nav>
    <a href="/student?action=view">Homepage</a>

    <ul>
        <li><a href="/student?action=add">Add Student</a></li>
        <li><a href="/admin">Logout</a></li>
    </ul>
</nav>

<div class="container">

<h2>Add Student</h2>

<form action="/student?action=add" method="post">

<label>First Name</label>
<input type="text" name="firstName" required>

<label>Last Name</label>
<input type="text" name="lastName" required>

<label>Email</label>
<input type="email" name="email" required>

<label>Phone</label>
<input type="text" name="phone" required>

<label>Gender</label>

<select name="gender">
    <option>Male</option>
    <option>Female</option>
    <option>Other</option>
</select>

<label>Date of Birth</label>
<input type="date" name="dob">

<label>Course</label>
<input type="text" name="course">

<label>Year</label>
<input type="number" name="year">

<label>Address</label>
<textarea name="address"></textarea>

<button type="submit">
    Add Student
</button>

</form>

</div>

</body>
</html>