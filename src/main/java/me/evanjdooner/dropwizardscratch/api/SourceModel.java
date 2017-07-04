package me.evanjdooner.dropwizardscratch.api;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class SourceModel {

    @NotEmpty
    private String name;

    @Range(min = 0, max = 99)
    private int age;

    public SourceModel() {}

    public SourceModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter
    public int getAge() {
        return age;
    }

    @JsonSetter
    public void setAge(int age) {
        this.age = age;
    }
}
