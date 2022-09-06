package monitor;

public class WorkingHour {
    private String employeeFk;
    private String date;
    private String timeBegin;
    private String timeEnd;
    private String timeDiff;
    private String project;
    private String item;
    private String comment;
    private String projectItem;

    public WorkingHour(String employeeFk, String date, String timeBegin, String timeEnd, String timeDiff, String project, String item, String comment) {
        this.employeeFk = employeeFk;
        this.date = date;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.timeDiff = timeDiff;
        this.project = project;
        this.item = item;
        this.comment = comment;
        this.projectItem = project + ":" + item;
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

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(String timeDiff) {
        this.timeDiff = timeDiff;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProjectItem() {
        return projectItem;
    }

    public void setProjectItem(String projectItem) {
        this.projectItem = projectItem;
    }
}
