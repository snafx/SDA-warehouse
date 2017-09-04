package com.sda.Warehouse.models;

import com.sda.Warehouse.repositories.JpaCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class CategoryFormatter implements Formatter<Category> {

    @Autowired
    JpaCategoryRepository categoryRepository;

    @Override
    public Category parse(String s, Locale locale) throws ParseException {
        Integer id = Integer.valueOf(s);
        return this.categoryRepository.findOne(id);//return Type object form DB
    }

    @Override
    public String print(Category category, Locale locale) {
        return (category != null ? category.getId().toString() : "");
    }
}
