package chuck.norris.facts;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class IcndbController {

    private final IcndbClient icndbClient;

    public IcndbController(IcndbClient icndbClient) {
        this.icndbClient = icndbClient;
    }

    @Get("/joke")
    public Fact getFact() {
        System.out.println("============================ IcndbController.getFact ============================");
        return icndbClient
                .getRandomFact()
                .orElse(null);
    }
}
