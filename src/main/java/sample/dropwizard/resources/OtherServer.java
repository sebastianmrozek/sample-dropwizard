package sample.dropwizard.resources;

import org.apache.commons.lang3.RandomStringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("other")
public class OtherServer {

    @GET
    @Path("hardwork")
    public String doHardWork() {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 200) {
            String randomString = RandomStringUtils.randomAlphabetic(5);
            if (randomString.contains("abc")) {
                builder.append(randomString);
            }
        }
        return builder.append(System.lineSeparator()).toString();

    }

    @GET
    @Path("morework")
    public String doMoreHardWork() {
        return doHardWork() + doHardWork() + doHardWork();
    }
}
