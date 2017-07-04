package me.evanjdooner.dropwizardscratch.resources;

import com.codahale.metrics.annotation.Timed;
import me.evanjdooner.dropwizardscratch.api.DestinationModel;
import me.evanjdooner.dropwizardscratch.api.SourceModel;
import org.glassfish.jersey.client.rx.RxClient;
import org.glassfish.jersey.client.rx.java8.RxCompletionStageInvoker;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/transform")
@Produces(MediaType.TEXT_PLAIN)
public class TransformerResource {

    private static String host = "http://localhost:8080";

    private final String src;
    private final String dest;
    private final RxClient<RxCompletionStageInvoker> client;

    public TransformerResource(String src, String dest, RxClient<RxCompletionStageInvoker> client) {
        this.src = src;
        this.dest = dest;
        this.client = client;
    }

    @POST
    @Timed
    public void transformJson(@Suspended final AsyncResponse asyncResponse) {
        CompletionStage<SourceModel> sourceModel = client.target(host)
                .path(src)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .rx()
                .get(SourceModel.class);

        CompletionStage<DestinationModel> destinationModel = sourceModel
                .thenApplyAsync(model -> new DestinationModel(model.getName(), model.getAge()));

        CompletionStage<String> response = destinationModel
                .thenComposeAsync(model -> client.target(host)
                        .path(dest)
                        .request(MediaType.TEXT_PLAIN_TYPE)
                        .rx()
                        .post(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE), String.class));
        
        response.thenApplyAsync(post -> "Transformation complete: " + post)
        .thenAcceptAsync(asyncResponse::resume);
    }

}
