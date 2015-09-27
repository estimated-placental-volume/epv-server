package net.epv.epvserver.auth;

import org.junit.Ignore;

@Ignore
public class UserProfileApiIT {

//    private final String username = "epvapp";
//    private final String host = System.getenv("INTEGRATION_HOST");
//    private final int port = 8080;
//    private final String url = "http://" + host + ":" + port;
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void testCreateProfileWithOutcome() throws Exception {
//
//        Outcome outcome = new Outcome(UUID.randomUUID(), 0.0, 0.0, 1, 1, 1, Arrays.asList(0, 1, 2, 3, 4));
//        UserProfile profile = new UserProfile(UUID.randomUUID(), 0.0, 0.0, 0.0, 0.0, 0.0, outcome);
//
//        Response response = given().auth().basic(username, password).
//                and().
//                contentType("application/json").
//                body(mapper.writeValueAsString(profile)).
//                with().
//                post(url + "/user-profile/" + profile.getId());
//
//         String body = response.getBody().asString();
//         System.out.println(body);
//    }
}
