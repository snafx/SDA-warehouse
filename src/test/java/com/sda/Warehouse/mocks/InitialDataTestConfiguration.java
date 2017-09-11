package com.sda.Warehouse.mocks;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.models.Category;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaBookAuthorRepository;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("test")
public class InitialDataTestConfiguration {

    private JpaCategoryRepository jpaCategoryRepository;

    private JpaProductRepository jpaProductRepository;

    private JpaBookAuthorRepository jpaBookAuthorRepository;

    private JpaUserRepository userRepository;

    @Autowired
    public InitialDataTestConfiguration(JpaCategoryRepository jpaCategoryRepository, JpaProductRepository jpaProductRepository,JpaBookAuthorRepository jpaBookAuthorRepository, JpaUserRepository userRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaProductRepository = jpaProductRepository;
        this.jpaBookAuthorRepository = jpaBookAuthorRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        userRepository.save(new User("TestUserName", "TestUserLastName", "test@test.pl", "TestPassword", "TestRole", false));
        userRepository.save(new User("TestUserName2", "TestUserLastName2", "test2@test.pl", "TestPassword2", "TestRole2", false));
        userRepository.save(new User("TestUserName3", "TestUserLastName3", "test3@test.pl", "TestPassword3", "TestRole3", false));

        Category category = new Category("TestCategory");
        Category category2 = new Category("TestCategory2");
        Category category3 = new Category("TestCategory3");

        jpaCategoryRepository.save(category);
        jpaCategoryRepository.save(category2);
        jpaCategoryRepository.save(category3);

        BookAuthor bookAuthor = new BookAuthor("TestBookAuthor");
        BookAuthor bookAuthor2 = new BookAuthor("TestBookAuthor2");
        BookAuthor bookAuthor3 = new BookAuthor("TestBookAuthor3");

        jpaBookAuthorRepository.save(bookAuthor);
        jpaBookAuthorRepository.save(bookAuthor2);
        jpaBookAuthorRepository.save(bookAuthor3);

        Product product = new Product("TestName", "TestDesc", "TestLocation", 10,
                "TestUrl", category, bookAuthor, "10000000000000", 100);
        Product product2 = new Product("TestName2", "TestDesc2", "TestLocation2", 100,
                "TestUr2", category2, bookAuthor2, "2000000000000", 200);
        Product product3 = new Product("TestName3", "TestDesc3", "TestLocation3", 100,
                "TestUr3", category2, bookAuthor3, "3000000000000", 300);



        jpaProductRepository.save(product);
        jpaProductRepository.save(product2);
        jpaProductRepository.save(product3);
    }
}
