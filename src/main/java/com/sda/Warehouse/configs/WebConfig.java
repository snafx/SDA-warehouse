package com.sda.Warehouse.configs;

import com.sda.Warehouse.models.CategoryFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//mozliwe dodatkowe adnotacje
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired //Without autowire, this solution may not work
    private CategoryFormatter categoryFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(categoryFormatter);
    }
}
