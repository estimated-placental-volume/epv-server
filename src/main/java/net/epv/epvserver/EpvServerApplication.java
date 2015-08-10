package net.epv.epvserver;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.epv.epvserver.auth.EpvServerAuthenticator;
import net.epv.epvserver.resources.DataPointResource;
import net.epv.epvserver.resources.UserProfileResource;
import net.epv.epvserver.resources.WelcomeResource;

import java.util.UUID;

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
        environment.jersey().register(new WelcomeResource());
        environment.jersey().register(new UserProfileResource());
        environment.jersey().register(new DataPointResource());
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
                new EpvServerAuthenticator(configuration.getSha256Password()),
                "DEFAULT REALM",
                UUID.class)));
    }
}
