<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<%
if(session == null || session.getAttribute("admin") == null){
response.sendRedirect("admin");
return;
}
%>
<html>
<head>
    <title>Dashboard</title>
</head>
<style>
body{
padding: 0;
margin: 0;
box-sizing: border-box;
}
    nav{
        height: 50px;
        background-color: rgb(98, 98, 98);
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: #fff;
        box-shadow: 0px 0px 5px 0px #888;
        padding: 0px 1rem;
    }
    nav ul{
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 30%;
    list-style-type: none;
    }
     a{
    color: #fff;
    text-decoration: none;
    }
    .edit{
    background-color: green;
    color: #fff;
    }
    .delete{
    background-color: red;
    color: #fff;
    }
    h4{
    color: red;
    }
</style>
<body>
    <nav>
        <a href="/student?action=view">Homepage</a>
        <ul>
            <li><a href="/student?action=add">Add Student</a></li>
            <li><a href="/admin">Logout</a></li>
        </ul>
    </nav>
    <h2>All Students</h2>
    <h4><em><%= session.getAttribute("error") != null ? session.getAttribute("error") : ""%></em></h4>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Gender</th>
            <th>DOB</th>
            <th>Course</th>
            <th>Year</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
        %>
        <%
            for (Student student : students) {
        %>

            <tr>
                <td><%= student.getFirst_name() %></td>
                <td><%= student.getLast_name()  %></td>
                <td><%= student.getEmail()  %></td>
                <td><%= student.getPhone() %></td>
                <td><%= student.getGender() %></td>
                <td><%= student.getDob() %></td>
                <td><%= student.getCourse() %></td>
                <td><%= student.getYear() %></td>
                <td><%= student.getAddress() %></td>
                <td>
                <a href="student?action=edit&email=<%=student.getEmail()%>" class="edit">Edit</a>
                <a href="student?action=delete&email=<%= student.getEmail()%>" class="delete">Delete</a>
                </td>
            </tr>

        <%
            }
        %>
    </table>
</body>

</html>