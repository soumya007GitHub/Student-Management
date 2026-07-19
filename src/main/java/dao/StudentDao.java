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

    public boolean addStudentDao(Student student) {
        try {

            String sql = "INSERT INTO students (first_name, last_name, email, phone, gender, dob, course, year, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            if (result > 0) {
                System.out.println("Added student " + student.getFirst_name() + " " + student.getLast_name());
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Student> viewStudentDao() {

        List<Student> allStudents = new ArrayList<>();

        try {

            String sql = "SELECT * FROM students";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Student student = new Student(
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("gender"),
                        result.getString("dob"),
                        result.getString("course"),
                        result.getInt("year"),
                        result.getString("address")
                );

                allStudents.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStudents;
    }

    public boolean updateStudentDao(Student student, String email) {

        try {

            String sql =
                    "UPDATE students SET first_name=?, last_name=?, email=?, phone=?, gender=?, dob=?, course=?, year=?, address=? WHERE email=?";

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
            ps.setString(10, email);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Updated student " + student.getFirst_name() + " " + student.getLast_name());
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteStudentDao(String email) {

        try {

            String sql = "DELETE FROM students WHERE email=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Deleted student " + email);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Student> searchStudentDao(String parameter) {

        List<Student> allStudents = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR phone LIKE ? OR gender LIKE ? OR dob LIKE ? OR course LIKE ? OR year LIKE ? OR address LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);

            String value = "%" + parameter + "%";

            ps.setString(1, value);
            ps.setString(2, value);
            ps.setString(3, value);
            ps.setString(4, value);
            ps.setString(5, value);
            ps.setString(6, value);
            ps.setString(7, value);
            ps.setString(8, value);
            ps.setString(9, value);

            ResultSet result = ps.executeQuery();

            while (result.next()) {

                Student student = new Student(
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("gender"),
                        result.getString("dob"),
                        result.getString("course"),
                        result.getInt("year"),
                        result.getString("address")
                );

                allStudents.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStudents;
    }

}