package gui;

import dao.UserDAO;
import dao.CourseDAO;
import model.User;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LMSApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Learning Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JButton addUserButton = new JButton("Add User");
        JButton viewUsersButton = new JButton("View Users");
        JButton addCourseButton = new JButton("Add Course");
        JButton viewCoursesButton = new JButton("View Courses");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(addUserButton);
        panel.add(viewUsersButton);
        panel.add(addCourseButton);
        panel.add(viewCoursesButton);

        frame.add(panel);
        frame.setVisible(true);

        UserDAO userDAO = new UserDAO();
        CourseDAO courseDAO = new CourseDAO();

        addUserButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name:");
            String email = JOptionPane.showInputDialog("Enter email:");
            String role = JOptionPane.showInputDialog("Enter role (student/instructor/administrator):");
            userDAO.addUser(new User(0, name, email, role));
        });

        viewUsersButton.addActionListener(e -> {
            String role = JOptionPane.showInputDialog("Enter role to view:");
            List<User> users = userDAO.getUsersByRole(role);
            StringBuilder message = new StringBuilder("Users:\n");
            for (User user : users) {
                message.append(user.getName()).append(" - ").append(user.getEmail()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, message.toString());
        });

        addCourseButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter course title:");
            int instructorId = Integer.parseInt(JOptionPane.showInputDialog("Enter instructor ID:"));
            courseDAO.addCourse(new Course(0, title, instructorId));
        });

        viewCoursesButton.addActionListener(e -> {
            int instructorId = Integer.parseInt(JOptionPane.showInputDialog("Enter instructor ID:"));
            List<Course> courses = courseDAO.getCoursesByInstructor(instructorId);
            StringBuilder message = new StringBuilder("Courses:\n");
            for (Course course : courses) {
                message.append(course.getTitle()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, message.toString());
        });
    }
}
