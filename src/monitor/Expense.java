package monitor;

public class Expense {
    private int expenseId;
    private String employeeFk;
    private String date;
    private String projectItem;
    private String type;
    private int amount;
    private String comment;

    public Expense(int expenseId, String employeeFk, String date, String projectItem, String type, int amount, String comment) {
        this.expenseId = expenseId;
        this.employeeFk = employeeFk;
        this.date = date;
        this.projectItem = projectItem;
        this.type = type;
        this.amount = amount;
        this.comment = comment;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getEmployeeFk() {
        return employeeFk;
    }

    public void setEmployeeFk(String employeeFk) {
        this.employeeFk = employeeFk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProjectItem() {
        return projectItem;
    }

    public void setProjectItem(String projectItem) {
        this.projectItem = projectItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
