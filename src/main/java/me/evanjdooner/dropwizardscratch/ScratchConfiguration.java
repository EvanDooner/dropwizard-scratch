package me.evanjdooner.dropwizardscratch;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ScratchConfiguration extends Configuration {

    private String name;

    private int age;

    private String src;

    private String dest;

    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
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

    @JsonGetter
    public String getSrc() {
        return src;
    }

    @JsonSetter
    public void setSrc(String src) {
        this.src = src;
    }

    @JsonGetter
    public String getDest() {
        return dest;
    }

    @JsonSetter
    public void setDest(String dest) {
        this.dest = dest;
    }
}
