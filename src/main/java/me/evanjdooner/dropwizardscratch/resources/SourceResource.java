package me.evanjdooner.dropwizardscratch.resources;

import com.codahale.metrics.annotation.Timed;
import me.evanjdooner.dropwizardscratch.api.SourceModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/src")
@Produces(MediaType.APPLICATION_JSON)
public class SourceResource {

    private final SourceModel model;

    public SourceResource(SourceModel model) {
        this.model = model;
    }

    @GET
    @Timed
    public SourceModel getSourceJson() {
        return model;
    }

}
