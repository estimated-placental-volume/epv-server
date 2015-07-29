package net.epv.epvserver;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.epv.epvserver.health.TemplateHealthCheck;
import net.epv.epvserver.resources.DataPointResource;
import net.epv.epvserver.resources.HelloWorldResource;
import net.epv.epvserver.resources.UserProfileResource;

/**
 * Dropwizard application class for EpvServer
 */
public class EpvServerApplication extends Application<EpvServerConfiguration> {

    public static void main(String[] args) throws Exception {
        new EpvServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "epvserver";
    }

    @Override
    public void initialize(Bootstrap<EpvServerConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(EpvServerConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        environment.jersey().register(resource);
        environment.jersey().register(new UserProfileResource());
        environment.jersey().register(new DataPointResource());
        environment.healthChecks().register("template", healthCheck);
    }
}