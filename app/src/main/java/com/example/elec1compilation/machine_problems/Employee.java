package com.example.elec1compilation.machine_problems;

public class Employee {
    private String employeeId;
    private String name;
    private String positionCode;
    private String civilStatus;
    private double ratePerDay;
    private double taxRate;
    private double sssRate;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPositionCode() { return positionCode; }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
        // Set rate based on position code
        switch(positionCode) {
            case "A":
                this.ratePerDay = 500.00;
                break;
            case "B":
                this.ratePerDay = 400.00;
                break;
            case "C":
                this.ratePerDay = 300.00;
                break;
        }
    }
    public String getCivilStatus() { return civilStatus; }
    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
        // Set tax rate based on civil status
        switch (civilStatus) {
            case "Single":
                taxRate = 0.10;
                break;
            case "Married":
                taxRate = 0.05;
                break;
            case "Widowed":
                taxRate = 0.05;
                break;
            default:
                taxRate = 0.10;
                break;
        }
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getRatePerDay() { return ratePerDay; }

    // Calculate SSS rate based on basic pay
    public double calculateSSSRate(double basicPay) {
        if(basicPay >= 10000) return 0.07;
        else if(basicPay >= 5000) return 0.05;
        else if(basicPay >= 1000) return 0.03;
        else return 0.01;
    }
}
