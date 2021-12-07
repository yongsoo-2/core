package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {


    @Test
    void filterScan(){

    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = )
    )
    static class ComponentFilterAppConfig {


    }


}
