package sample.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sample.dropwizard.resources.OtherServer;
import sample.dropwizard.resources.ProxyingResource;

public class SampleDropwizardApplication extends Application<SampleDropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SampleDropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "Sample Dropwizard";
    }

    @Override
    public void initialize(final Bootstrap<SampleDropwizardConfiguration> bootstrap) {
    }

    @Override
    public void run(final SampleDropwizardConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(ResponseTimingFilter.class);
        environment.jersey().register(ProxyingResource.class);
        environment.jersey().register(OtherServer.class);
        // TODO: implement application
    }

}
