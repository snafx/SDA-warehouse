package com.sda.Warehouse.models;

import com.sda.Warehouse.repositories.JpaBookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class BookAuthorFormatter implements Formatter<BookAuthor> {

    @Autowired
    JpaBookAuthorRepository bookAuthor;

    @Override
    public BookAuthor parse(String s, Locale locale) throws ParseException {
        Long id = Long.valueOf(s);
        return this.bookAuthor.findOne(id); //return Type object form DB
    }

    @Override
    public String print(BookAuthor bookAuthor, Locale locale) {
        return (bookAuthor != null ? bookAuthor.getId().toString() : "");
    }
}
