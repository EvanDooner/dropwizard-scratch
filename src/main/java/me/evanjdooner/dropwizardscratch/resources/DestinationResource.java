package me.evanjdooner.dropwizardscratch.resources;

import com.codahale.metrics.annotation.Timed;
import me.evanjdooner.dropwizardscratch.api.DestinationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dest")
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
public class DestinationResource {

    private final String name;
    private final int age;

    public DestinationResource(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @POST
    @Timed
    public String acceptModel(@NotNull @Valid DestinationModel destinationModel) {
        if (!name.equals(destinationModel.getEmployeeName()) || age != destinationModel.getEmployeeAge()) {
            return "Bad request";
        }
        return "Resource received";
    }

}
