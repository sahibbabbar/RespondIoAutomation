package api;


import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonApiTest {
    public ObjectMapper mapper;

    public CommonApiTest() {
        mapper = new ObjectMapper();
    }


//    String body = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(booking);
//    System.out.println(body);
}
