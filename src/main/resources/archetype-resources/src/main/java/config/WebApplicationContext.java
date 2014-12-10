package config;

import java.util.ArrayList;
import java.util.List;
import ml.rugal.sshcommon.springmvc.method.annotation.FormModelMethodArgumentResolver;
import ml.rugal.sshcommon.springmvc.method.annotation.RequestJsonParamMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Java based Web context configuration class.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages =
{
    "rugal.sample.controller"
})
public class WebApplicationContext extends WebMvcConfigurerAdapter
{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(new FormModelMethodArgumentResolver());
        argumentResolvers.add(new RequestJsonParamMethodArgumentResolver());
    }

    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(mappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer.favorPathExtension(false).favorParameter(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("html", MediaType.TEXT_HTML);
        configurer.mediaType("js", MediaType.valueOf("text/javascript"));
        configurer.mediaType("xls", MediaType.valueOf("application/vnd.ms-excel"));
        configurer.mediaType("csv", MediaType.valueOf("text/csv"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public HttpMessageConverter mappingJackson2HttpMessageConverter()
    {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return messageConverter;
    }

//    @Bean
    public HandlerAdapter annotationMethodHandlerAdapter()
    {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public AbstractHandlerMapping defaultAnnotationHandlerMapping()
    {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setUseSuffixPatternMatch(false);
        return mapping;
    }
//    @Bean
//    public InternalResourceViewResolver viewResolver()
//    {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

}
