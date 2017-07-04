package me.evanjdooner.dropwizardscratch;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import me.evanjdooner.dropwizardscratch.api.SourceModel;
import me.evanjdooner.dropwizardscratch.health.DetailsCheck;
import me.evanjdooner.dropwizardscratch.resources.DestinationResource;
import me.evanjdooner.dropwizardscratch.resources.SourceResource;
import me.evanjdooner.dropwizardscratch.resources.TransformerResource;
import org.glassfish.jersey.client.rx.RxClient;
import org.glassfish.jersey.client.rx.java8.RxCompletionStageInvoker;

import javax.ws.rs.client.Client;

public class ScratchApplication extends Application<ScratchConfiguration> {

    public static void main(String[] args) throws Exception {
        new ScratchApplication().run(args);
    }

    public void run(ScratchConfiguration configuration, Environment environment) throws Exception {

        final RxClient<RxCompletionStageInvoker> client =
                new JerseyClientBuilder(environment)
                        .using(configuration.getJerseyClientConfiguration())
                        .buildRx(getName(), RxCompletionStageInvoker.class);

        SourceModel sourceModel = new SourceModel(
                configuration.getName(),
                configuration.getAge());
        SourceResource sourceResource = new SourceResource(sourceModel);
        DestinationResource destinationResource = new DestinationResource(
                configuration.getName(),
                configuration.getAge());
        TransformerResource transformerResource = new TransformerResource(
                configuration.getSrc(),
                configuration.getDest(),
                client);
        DetailsCheck healthCheck = new DetailsCheck(configuration.getName(), configuration.getAge());

        environment.healthChecks().register("details", healthCheck);
        environment.jersey().register(sourceResource);
        environment.jersey().register(destinationResource);
        environment.jersey().register(transformerResource);
    }
}
