package me.evanjdooner.dropwizardscratch.health;

import com.codahale.metrics.health.HealthCheck;

public class DetailsCheck extends HealthCheck {

    private final String name;
    private final int age;

    public DetailsCheck(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Result check() throws Exception {
        if (name.length() == 0 || age < 0 || age > 99) {
            return Result.unhealthy("Name and age are not valid");
        }
        return Result.healthy();
    }
}
