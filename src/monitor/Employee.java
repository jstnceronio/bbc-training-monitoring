package monitor;

public class Employee {
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjektFk() {
        return projektFk;
    }

    public void setProjektFk(String projektFk) {
        this.projektFk = projektFk;
    }

    public String getChargeRate() {
        return chargeRate;
    }

    public void setChargeRate(String chargeRate) {
        this.chargeRate = chargeRate;
    }

    public Employee(String shortName, String surname, String firstname, String password, String department, String projektFk, String chargeRate) {
        this.shortName = shortName;
        this.surname = surname;
        this.firstname = firstname;
        this.password = password;
        this.department = department;
        this.projektFk = projektFk;
        this.chargeRate = chargeRate;
    }

    private String shortName;
    private String surname;
    private String firstname;
    private String password;
    private String department;
    private String projektFk;
    private String chargeRate;



}
