package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    void findBeanByTypeDuplicate() {

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    void findBeanByTypeDuplicate2() {
        ac.getBean("memberRepository1", MemberRepository.class);
    }

    @Test
    void findAllBeanByType(){
        Map<String, MemberRepository> beans = ac.getBeansOfType(MemberRepository.class);
        for(String key : beans.keySet()){
            System.out.println("beanName : " + key + ", bean : " + beans.get(key));
        }
        System.out.println(beans);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
