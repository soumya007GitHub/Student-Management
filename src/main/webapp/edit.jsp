<%@ page import="model.Student"%>
<%
if(session == null || session.getAttribute("admin") == null){
response.sendRedirect("admin");
return;
}
%>
<%
Student student=(Student)request.getAttribute("student");
%>

<html>
<head>

<title>Edit Student</title>

<style>
body{
padding:0;
margin:0;
box-sizing:border-box;
font-family:Arial;
background:#f5f5f5;
}

nav{
height:50px;
background-color:rgb(98,98,98);
display:flex;
justify-content:space-between;
align-items:center;
color:white;
padding:0 1rem;
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
box-shadow:0 0 5px gray;
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

<h2>Edit Student</h2>

<form action="/student?action=edit&email=<%=student.getEmail()%>" method="post">

<label>First Name</label>
<input type="text" name="firstName"
value="<%=student.getFirst_name()%>">

<label>Last Name</label>
<input type="text" name="lastName"
value="<%=student.getLast_name()%>">

<label>Email</label>
<input type="email" name="email"
value="<%=student.getEmail()%>">

<label>Phone</label>
<input type="text" name="phone"
value="<%=student.getPhone()%>">

<label>Gender</label>

<select name="gender">

<option value="Male"
<%=student.getGender().equals("Male")?"selected":""%>>
Male
</option>

<option value="Female"
<%=student.getGender().equals("Female")?"selected":""%>>
Female
</option>

<option value="Other"
<%=student.getGender().equals("Other")?"selected":""%>>
Other
</option>

</select>

<label>Date of Birth</label>

<input type="date"
name="dob"
value="<%=student.getDob()%>">

<label>Course</label>

<input type="text"
name="course"
value="<%=student.getCourse()%>">

<label>Year</label>

<input type="number"
name="year"
value="<%=student.getYear()%>">

<label>Address</label>

<textarea name="address"><%=student.getAddress()%></textarea>

<button type="submit">
Update Student
</button>

</form>

</div>

</body>
</html>