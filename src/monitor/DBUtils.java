package monitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBUtils {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/monitor", "root", "root");
    }

    public static Employee getEmployee(String username, String password) {
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM employees WHERE shortName = ? AND password = ?");
            psSelect.setString(1, username);
            psSelect.setString(2, password);
            ResultSet rs = psSelect.executeQuery();
            Employee employee = null;

            if (rs.next()) {
                employee = new Employee(
                        rs.getString("shortName"),
                        rs.getString("surname"),
                        rs.getString("firstname"),
                        rs.getString("password"),
                        rs.getString("department"),
                        rs.getString("projektFk"),
                        rs.getString("chargeRate")
                );
            }
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // no user found :(
    }

    public static ObservableList<WorkingHour> getWorkingHours() {
        ObservableList<WorkingHour> workingHours = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM workingHours");
            ResultSet rs = psSelect.executeQuery();
            WorkingHour workingHour;
            while (rs.next()) {
                workingHour = new WorkingHour(
                        rs.getString("employeeFk"),
                        rs.getString("date"),
                        rs.getString("timeBegin"),
                        rs.getString("timeEnd"),
                        rs.getString("timeDiff"),
                        rs.getString("project"),
                        rs.getString("item"),
                        rs.getString("comment")
                );
                workingHours.add(workingHour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workingHours;
    }

    public static void updateWorkingHour(WorkingHour oldHour, WorkingHour newHour) {
        Connection connection;
        PreparedStatement psUpdate = null;
        try {
            connection = getConnection();
            psUpdate = connection.prepareStatement("UPDATE workingHours SET project = ?, item = ?, timeBegin = ?, timeEnd = ?, timeDiff = ?, comment = ? " +
                    "WHERE date = ? AND timeBegin = ?");
            psUpdate.setString(1, newHour.getProject());
            psUpdate.setString(2, newHour.getItem());
            psUpdate.setString(3, newHour.getTimeBegin());
            psUpdate.setString(4, newHour.getTimeEnd());
            psUpdate.setString(5, newHour.getTimeDiff());
            psUpdate.setString(6, newHour.getComment());
            psUpdate.setString(7, oldHour.getDate());
            psUpdate.setString(8, oldHour.getTimeBegin());

            System.out.println(psUpdate.toString());
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addWorkingHour(WorkingHour h) {
        try {
            Connection connection = getConnection();
            PreparedStatement psInsert = connection.prepareStatement(
                    "INSERT INTO workingHours (employeeFk, date, timeBegin, timeEnd, timeDiff, project, item, comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            psInsert.setString(1, h.getEmployeeFk());
            psInsert.setString(2, h.getDate());
            psInsert.setString(3, h.getTimeBegin());
            psInsert.setString(4, h.getTimeEnd());
            psInsert.setString(5, h.getTimeDiff());
            psInsert.setString(6, h.getProject());
            psInsert.setString(7, h.getItem());
            psInsert.setString(8, h.getComment());
            System.out.println(psInsert.toString());
            psInsert.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public static ObservableList<Expense> getExpenses(Employee employee) {
        ObservableList<Expense> expenses = FXCollections.observableArrayList();
        try {
            Connection connection = getConnection();
            PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM expenses");
            ResultSet rs = psSelect.executeQuery();
            Expense expense;
            while (rs.next()) {
                expense = new Expense(
                        rs.getInt("expenseId"),
                        rs.getString("employeeFk"),
                        rs.getString("date"),
                        rs.getString("projectFk") + " " + rs.getString("itemFk"),
                        rs.getString("type"),
                        rs.getInt("amount"),
                        rs.getString("comment")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public static ObservableList<Project> getProjects() {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        try {
            Connection con = getConnection();
            PreparedStatement select = con.prepareStatement("SELECT * FROM projects");
            ResultSet rs = select.executeQuery();
            Project project;
            while(rs.next()) {
                project = new Project(
                        rs.getString("customer"),
                        rs.getString("project"),
                        rs.getDouble("budget"),
                        rs.getString("item"),
                        rs.getDouble("plannedHours"),
                        rs.getDouble("plannedExpenses"),
                        rs.getDouble("progress")
                );
                projects.add(project);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projects;
    }
}
