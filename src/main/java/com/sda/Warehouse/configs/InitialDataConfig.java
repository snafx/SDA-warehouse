package com.sda.Warehouse.configs;

import com.sda.Warehouse.models.Category;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * Created by mytek on 2017-09-02.
 */

@Configuration
@Profile("dev")
public class InitialDataConfig {

    private JpaCategoryRepository jpaCategoryRepository;

    private JpaProductRepository jpaProductRepository;

    private JpaUserRepository userRepository;

    @Autowired
    public InitialDataConfig(JpaCategoryRepository jpaCategoryRepository, JpaProductRepository jpaProductRepository, JpaUserRepository userRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaProductRepository = jpaProductRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        userRepository.save(new User("Jan", "Kowalski", "kowalski@wp.pl", "qwerty123", "warehouseman", false));
        userRepository.save(new User("Jan", "Kowalski", "a@a.com", "a", "warehouseman", false));
        userRepository.save(new User("Marcin", "Kowalski", "abc@xyz.com", "abc", "warehouseman", false));

        Category category = new Category("it");
        Category category2 = new Category("cook");
        Category category3 = new Category("novel");
        Category category4 = new Category("young");
        Category category5 = new Category("children");
        Category category6 = new Category("poetry");
        Category category7 = new Category("fantasy");
        Category category8 = new Category("school");

        jpaCategoryRepository.save(category);
        jpaCategoryRepository.save(category2);
        jpaCategoryRepository.save(category3);
        jpaCategoryRepository.save(category4);
        jpaCategoryRepository.save(category5);
        jpaCategoryRepository.save(category6);
        jpaCategoryRepository.save(category7);
        jpaCategoryRepository.save(category8);

        Product product = new Product("Java podstawy",
                "Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, "Cay S. Horstman","9788328324800",69);

        Product product1 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category,"David Flanganan", "978-83-283-0623-3", 47.50);

        Product product2 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5,"Janina Porazińska","978-83-280-3885-1", 15.54);
        Product product3 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5,"Janina Porazińska", "978-83-10-12986-4", 9.99);

        Product product4 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3,"Wojciech Miłoszewski", "9788328044234", 32.00);

        Product product5 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3,"Leszek Herman", "978-8-3287-0395-7", 20.22);

        jpaProductRepository.save(product);
        jpaProductRepository.save(product1);
        jpaProductRepository.save(product2);
        jpaProductRepository.save(product3);
        jpaProductRepository.save(product4);
        jpaProductRepository.save(product5);

    }
}
