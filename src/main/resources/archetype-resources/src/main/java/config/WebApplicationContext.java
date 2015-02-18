package config;

import java.util.ArrayList;
import java.util.List;
import ml.rugal.sshcommon.springmvc.method.annotation.FormModelMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
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
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer.favorPathExtension(false).favorParameter(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(messageConverter);
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver()
//    {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    @Bean
    public AbstractHandlerMapping defaultAnnotationHandlerMapping()
    {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setUseSuffixPatternMatch(false);
        return mapping;
    }
}
