package net.epv.epvserver;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.epv.epvserver.auth.EpvServerAuthenticator;
import net.epv.epvserver.jdbi.DataPointDao;
import net.epv.epvserver.jdbi.UUIDArgumentFactory;
import net.epv.epvserver.jdbi.UserProfileDao;
import net.epv.epvserver.resources.DataResource;
import net.epv.epvserver.resources.UserProfileResource;
import net.epv.epvserver.resources.WelcomeResource;
import org.skife.jdbi.v2.DBI;

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

        DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, configuration.getDatabase(), "mysql");
        jdbi.registerArgumentFactory(new UUIDArgumentFactory());

        UserProfileDao userProfileDao = jdbi.onDemand(UserProfileDao.class);
        DataPointDao dataPointDao = jdbi.onDemand(DataPointDao.class);

        // Create database schema:
        userProfileDao.createTable();
        dataPointDao.createTable();

        // Build environment:
        environment.jersey().register(new WelcomeResource());
        environment.jersey().register(new UserProfileResource(userProfileDao));
        environment.jersey().register(new DataResource(dataPointDao));
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
                new EpvServerAuthenticator(configuration.getUserName(), configuration.getSha256Password()),
                "DEFAULT REALM",
                String.class)));
    }
}
