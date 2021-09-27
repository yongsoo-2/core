package hello.core.beandenfinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void DefinitionTest(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for( String key : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(key);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("bean Name : " + beanDefinitionNames + " beanDefinition :" + beanDefinition);
            }
        }

    }
}
