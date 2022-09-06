package monitor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MonitorController implements Initializable {

    public Label lblName;

    public TableView<WorkingHour> tvWorkingHours;
    public TableColumn<WorkingHour, String> colProject;
    public TableColumn<WorkingHour, String> colBegin;
    public TableColumn<WorkingHour, String> colEnd;
    public TableColumn<WorkingHour, String> colTime;
    public TableColumn<WorkingHour, String> colComment;
    public DatePicker dpDateFilter;
    public Label lblWorkingHours;
    public ChoiceBox<String> cbProjectItem;
    public TextField txtBegin;
    public TextField txtEnd;
    public TextField txtDiff;
    public TextField txtComment;
    public Label lblExpenses;
    public TableView<Expense> tvExpenses;
    public TableColumn<Expense, String> colExpProject;
    public TableColumn<Expense, String> colExpType;
    public TableColumn<Expense, String> colExpAmount;
    public TableColumn<Expense, String> colExpComment;
    public Button btnNewExpense;
    public Button btnSave1;
    public ChoiceBox<String> cbExpProject;
    public TextField txtType;
    public TextField txtAmount;
    public TextField txtExpComment;
    public VBox vbProjects;

    private Employee employee; // the logged in employee
    ObservableList<WorkingHour> workingHours;
    ObservableList<Expense> expenses;
    private WorkingHour selectedHour;
    private Expense selectedExpense;
    ObservableList<String> projectItems;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.uuuu");

    ObservableList<Project> projects;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dpDateFilter.setValue(LocalDate.now());
        tvWorkingHours.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkingHour>() {
            @Override
            public void changed(ObservableValue<? extends WorkingHour> observableValue, WorkingHour workingHour, WorkingHour t1) {
                if (t1 != null)
                    selectedHour = t1;
                showSelectedWorkingHour();
            }
        });
        tvExpenses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Expense>() {
            @Override
            public void changed(ObservableValue<? extends Expense> observableValue, Expense expense, Expense t1) {
                if (t1 != null)
                    selectedExpense = t1;
                showSelectedExpense();
            }
        });
    }

    private void displayProjects() {
        ObservableList<Project> projects = DBUtils.getProjects().stream()
                .filter(p -> p.getProject().equals(employee.getProjektFk()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        projects.forEach(p -> {
                    vbProjects.getChildren().add(
                            new ProjectController(
                                    p.getProject(),
                                    p.getProgress(),
                                    p.getPlannedHours(),
                                    p.getPlannedExpenses()
                            )
                    );
                }
        );
        vbProjects.setSpacing(5);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        lblName.setText(employee.getFirstname() + " " + employee.getSurname());
        displayWorkingHours();
        displayExpenses();
        displayProjects();
    }

    private void displayWorkingHours() {
        String currentDateString = LocalDate.now().format(formatter);
        workingHours = DBUtils.getWorkingHours().stream()
                .filter(w -> w.getEmployeeFk().equals(employee.getShortName()))
                .filter(w -> w.getDate().equals(currentDateString))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        colProject.setCellValueFactory(new PropertyValueFactory<>("projectItem"));
        colBegin.setCellValueFactory(new PropertyValueFactory<>("timeBegin"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("timeDiff"));
        colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        tvWorkingHours.setItems(workingHours);
        double time = 0;
        for (WorkingHour hour : workingHours) {
            time += Double.parseDouble(hour.getTimeDiff());
        }
        lblWorkingHours.setText(Double.toString(time));
    }

    private void showSelectedWorkingHour() {
        // Project items
        projectItems = FXCollections.observableArrayList();
        projectItems.addAll(
                "Initialisation",
                "Frontend",
                "Backend"
        );
        projectItems = projectItems.stream().map(i -> employee.getProjektFk() + ":" + i)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        cbProjectItem.setItems(projectItems);

        // In case user is creating new entry
        if (selectedHour == null) {
            // Clear fields
            txtBegin.setText("");
            txtEnd.setText("");
            txtDiff.setText("");
            txtComment.setText("");
            return;
        }

        cbProjectItem.getSelectionModel().select(selectedHour.getProjectItem());
        // Time
        txtBegin.setText(selectedHour.getTimeBegin());
        txtEnd.setText(selectedHour.getTimeEnd());
        txtDiff.setText(selectedHour.getTimeDiff());
        // Comment
        if (selectedHour.getComment() != null)
            txtComment.setText(selectedHour.getComment());
    }

    private void showSelectedExpense() {
        // Project items
        projectItems = FXCollections.observableArrayList();
        projectItems.addAll(
                "Initialisation",
                "Frontend",
                "Backend"
        );
        projectItems = projectItems.stream().map(i -> employee.getProjektFk() + ":" + i)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        cbExpProject.setItems(projectItems);

        // In case user is creating new entry
        if (selectedExpense == null) {
            // Clear fields
            txtType.setText("");
            txtAmount.setText("");
            txtComment.setText("");
            return;
        }

        cbExpProject.getSelectionModel().select(selectedExpense.getProjectItem());

        txtType.setText(selectedExpense.getType());
        txtAmount.setText(Integer.toString(selectedExpense.getAmount()));
        // Comment
        if (selectedExpense.getComment() != null)
            txtComment.setText(selectedExpense.getComment());
    }

    public void newWorkItem(ActionEvent actionEvent) {
        selectedHour = null;
        showSelectedWorkingHour();
    }

    public void saveWorkItem(ActionEvent actionEvent) {
        if (selectedHour == null) {
            String[] projectInfo = cbProjectItem.getSelectionModel().getSelectedItem().split(":");
            // FIXME: Get diff date
            // Get fields
            WorkingHour hourToAdd = new WorkingHour(
                    employee.getShortName(),
                    dpDateFilter.getValue().format(formatter),
                    txtBegin.getText(),
                    txtEnd.getText(),
                    txtDiff.getText(),
                    projectInfo[0],
                    projectInfo[1],
                    txtComment.getText()
            );
            // Add entry to DB
            DBUtils.addWorkingHour(hourToAdd);
            return;
        }

        String[] projectInfo = cbProjectItem.getSelectionModel().getSelectedItem().split(":");
        WorkingHour newHour = new WorkingHour(
                selectedHour.getEmployeeFk(),
                selectedHour.getDate(),
                txtBegin.getText(),
                txtEnd.getText(),
                txtDiff.getText(),
                projectInfo[0],
                projectInfo[1],
                txtComment.getText()
        );
        System.out.println(projectInfo[0] + " : " + projectInfo[1]);
        DBUtils.updateWorkingHour(selectedHour, newHour);
        // Refresh
        displayWorkingHours();
        selectedHour = newHour;
        showSelectedWorkingHour();
    }

    private void displayExpenses() {
        String currentDateString = LocalDate.now().format(formatter);
        expenses = DBUtils.getExpenses(employee).stream()
                .filter(w -> w.getDate().equals(currentDateString))
                .filter(w -> w.getEmployeeFk().equals(employee.getShortName()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        colExpProject.setCellValueFactory(new PropertyValueFactory<>("projectItem"));
        colExpType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colExpAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        tvExpenses.setItems(expenses);
        double cost = 0;
        for (Expense expense : expenses) {
            cost += expense.getAmount();
        }
        lblExpenses.setText(cost + " CHF");
    }
}
