package sample.dropwizard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

@Provider
public class ResponseTimingFilter implements ClientRequestFilter, ClientResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseTimingFilter.class);
    private static final String REQUEST_START_KEY = "x-seb-request-date";

    private final ResponseTimingTargets targets = new ResponseTimingTargets();

//    public ResponseTimingFilter(ResponseTimingTargets targets) {
//        this.targets = targets;
//    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
        final Optional<String> targetName = targets.findMatchingName(requestContext.getUri());
        if (targetName.isPresent()) {
            try {
                long requestStartMillis = Long.parseLong(requestContext.getHeaders().getFirst(REQUEST_START_KEY).toString());
                long responseMillis = System.currentTimeMillis() - requestStartMillis;
                LOG.info("Response time for {}: {}", targetName.get(), responseMillis);
            } catch (Exception e) {
                LOG.warn("Unable to establish response time", e);
            }
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        final Optional<String> targetName = targets.findMatchingName(requestContext.getUri());
        if (targetName.isPresent()) {
            requestContext.getHeaders().putSingle(REQUEST_START_KEY, String.valueOf(System.currentTimeMillis()));
        }
    }
}
