package br.com.devedojo.demo.adpter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringBootEssentialsAdapter implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
//        phmar.setFallbackPageable(new PageRequest(0,5));
        Pageable defaultPageable = PageRequest.of(0, 5);
        phmar.setFallbackPageable(defaultPageable);
        argumentResolvers.add(phmar);
    }
}
