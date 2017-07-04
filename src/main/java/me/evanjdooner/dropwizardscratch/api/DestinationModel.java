package me.evanjdooner.dropwizardscratch.api;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class DestinationModel {

    @NotEmpty
    private String employeeName;

    @Range(min = 0, max = 99)
    private int employeeAge;

    public DestinationModel() {

    }

    public DestinationModel(String employeeName, int employeeAge) {
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
    }

    @JsonGetter
    public String getEmployeeName() {
        return employeeName;
    }

    @JsonSetter
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @JsonGetter
    public int getEmployeeAge() {
        return employeeAge;
    }

    @JsonSetter
    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }
}
