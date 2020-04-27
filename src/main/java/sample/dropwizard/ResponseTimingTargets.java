package sample.dropwizard;

import java.net.URI;
import java.util.Optional;

public class ResponseTimingTargets {

    public Optional<String> findMatchingName(URI uri) {
        if (uri.getPath().contains("morework")) {
            return Optional.of("seb API hardwork");
        }
        return Optional.empty();
    }
}
