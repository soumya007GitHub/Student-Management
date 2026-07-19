package dao;

import config.DBConfig;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static final Connection con = DBConfig.dbConnection();

    public boolean addStudentDao(Student student){
        try {
            String sql = "INSERT INTO students (first_name, last_name, email, phone, gender, dob, course, year, address)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirst_name());
            ps.setString(2, student.getLast_name());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getGender());
            ps.setString(6, student.getDob());
            ps.setString(7, student.getCourse());
            ps.setInt(8, student.getYear());
            ps.setString(9, student.getAddress());
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Added student to db"+student.getFirst_name()+" "+student.getLast_name());
                return true;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Student> viewStudentDao(){
        List<Student> allStudents = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String dob = result.getString("dob");
                String course = result.getString("course");
                Integer year = result.getInt("year");
                String address = result.getString("address");
                Student student = new Student(first_name, last_name, email, phone, gender, dob, course, year, address);
                allStudents.add(student);
            }
            return allStudents;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateStudentDao(Student student, Integer studentId){
        try {
            String sql = "UPDATE student (first_name, last_name, email, phone, gender, dob, course, year, address)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirst_name());
            ps.setString(2, student.getLast_name());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getGender());
            ps.setString(6, student.getDob());
            ps.setString(7, student.getCourse());
            ps.setInt(8, student.getYear());
            ps.setString(9, student.getAddress());
            ps.setInt(10, studentId);
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Updated details for "+student.getFirst_name()+" "+student.getLast_name());
                return true;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteStudentDao(Integer studentId){
        try {
            String sql = "DELETE FROM student WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Deleted student to db with id "+studentId);
                return true;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Student> searchStudentDao(String parameter){
        List<Student> allStudents = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR "+
                    "email LIKE ? OR phone LIKE ? OR gender LIKE ? OR dob LIKE ? OR course LIKE ? OR year LIKE ? OR address LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, parameter);
            ps.setString(2, parameter);
            ps.setString(3, parameter);
            ps.setString(4, parameter);
            ps.setString(5, parameter);
            ps.setString(6, parameter);
            ps.setString(7, parameter);
            ps.setString(8, parameter);
            ps.setString(9, parameter);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String gender = result.getString("gender");
                String dob = result.getString("dob");
                String course = result.getString("course");
                Integer year = result.getInt("year");
                String address = result.getString("address");
                Student student = new Student(first_name, last_name, email, phone, gender, dob, course, year, address);
                allStudents.add(student);
            }
            return allStudents;

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
