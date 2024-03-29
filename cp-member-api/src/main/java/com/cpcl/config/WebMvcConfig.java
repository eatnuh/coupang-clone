package com.cpcl.config;

import com.cpcl.product.api.search.ProductSearchArgumentResolver;
import com.cpcl.review.api.search.ReviewSearchArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ProductSearchArgumentResolver());
        resolvers.add(new ReviewSearchArgumentResolver());
    }

}
