package chuck.norris.facts;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller("/")
public class IcndbController {

    private final IcndbClient icndbClient;
    private final ObjectMapper mapper;

    public IcndbController(IcndbClient icndbClient,
                           ObjectMapper mapper) {
        this.icndbClient = icndbClient;
        this.mapper = mapper;
    }

    @Get("/joke")
    public Fact getFact() {
        System.out.println("============================ IcndbController.getFact ============================");
        return icndbClient
                .getRandomFact()
                .orElse(null);
    }

    @Get("/joke2")
    public Fact getFact2() throws IOException {
        System.out.println("============================ IcndbController.getFact2 ============================");

        URL url = new URL("https://api.icndb.com/jokes/random/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        con.setInstanceFollowRedirects(true);

        int status = con.getResponseCode();
        System.out.println(status);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        System.out.println(content.toString());

        return mapper.readValue(content.toString(), Fact.class);
    }
}
