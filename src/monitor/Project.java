package monitor;

public class Project {

    private String customer;
    private String project;
    private double budget;
    private String item;
    private double plannedHours;
    private double plannedExpenses;
    private double progress;

    public Project(String customer, String project, double budget, String item, double plannedHours, double plannedExpenses, double progress) {
        this.customer = customer;
        this.project = project;
        this.budget = budget;
        this.item = item;
        this.plannedHours = plannedHours;
        this.plannedExpenses = plannedExpenses;
        this.progress = progress;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPlannedHours() {
        return plannedHours;
    }

    public void setPlannedHours(double plannedHours) {
        this.plannedHours = plannedHours;
    }

    public double getPlannedExpenses() {
        return plannedExpenses;
    }

    public void setPlannedExpenses(double plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
