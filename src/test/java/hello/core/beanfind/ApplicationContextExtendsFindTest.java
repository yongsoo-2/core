package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void findBeanByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    void findBeanByParentTypeByBeanName(){
        DiscountPolicy fixDiscountPolicy = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    void findBeanBySubType(){
        FixDiscountPolicy fixDiscountPolicy = ac.getBean(FixDiscountPolicy.class);
        assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    void findAllBean(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet()){
            System.out.println("Bean Name : "+key +" value : " + beansOfType.get(key));
        }
    }


    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }
}
