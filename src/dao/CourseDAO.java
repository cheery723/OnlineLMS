package dao;

import model.Course;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public void addCourse(Course course) {
        String query = "INSERT INTO courses (title, instructor_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getInstructorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getCoursesByInstructor(int instructorId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses WHERE instructor_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, instructorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("instructor_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
