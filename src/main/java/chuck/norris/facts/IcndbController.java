package chuck.norris.facts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.HashMap;
import java.util.Map;

@Controller("/")
public class IcndbController {

    private final IcndbClient icndbClient;
    private final ObjectMapper objectMapper;

    public IcndbController(IcndbClient icndbClient,
                           ObjectMapper objectMapper) {
        this.icndbClient = icndbClient;
        this.objectMapper = objectMapper;
    }

    @Get("/joke")
    public Map<String, Object> getFact() {
        System.out.println("============================ IcndbController.getFact ============================");
        Fact fact = icndbClient
                .getRandomFact()
                .orElse(null);

        String body = "";
        try {
            body = objectMapper.writeValueAsString(fact);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, Object> m = new HashMap<>();
        m.put("statusCode", 200);
        m.put("headers", null);
        m.put("body", body);

        return m;
    }
}
