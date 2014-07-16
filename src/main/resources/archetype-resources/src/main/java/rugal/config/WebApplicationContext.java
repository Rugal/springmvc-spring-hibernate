/*
 * Copyright 2014 e563642.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rugal.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import rugal.common.springmvc.method.annotation.FormModelMethodArgumentResolver;
import rugal.common.springmvc.method.annotation.RequestJsonParamMethodArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"rugal.sample.controller"})
public class WebApplicationContext extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new FormModelMethodArgumentResolver());
        argumentResolvers.add(new RequestJsonParamMethodArgumentResolver());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("html", MediaType.TEXT_HTML);
        configurer.mediaType("js", MediaType.valueOf("text/javascript"));
        configurer.mediaType("xls", MediaType.valueOf("application/vnd.ms-excel"));
        configurer.mediaType("csv", MediaType.valueOf("text/csv"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.valueOf("text/javascript"));
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(messageConverter);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public HandlerAdapter annotationMethodHandlerAdapter() {
        return new AnnotationMethodHandlerAdapter();
    }

    @Bean
    public AbstractDetectingUrlHandlerMapping defaultAnnotationHandlerMapping() {
        DefaultAnnotationHandlerMapping mapping = new DefaultAnnotationHandlerMapping();
        mapping.setUseDefaultSuffixPattern(false);
        return mapping;
    }
}
