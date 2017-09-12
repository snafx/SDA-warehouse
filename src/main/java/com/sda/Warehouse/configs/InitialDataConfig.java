package com.sda.Warehouse.configs;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.models.Category;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.Role;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import com.sda.Warehouse.repositories.JpaRoleRepository;
import com.sda.Warehouse.repositories.JpaUserRepository;
import com.sda.Warehouse.services.UserService;
import com.sda.Warehouse.models.UserOrder;
import com.sda.Warehouse.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
//@Profile("dev")
public class InitialDataConfig {

    private JpaCategoryRepository jpaCategoryRepository;

    private JpaProductRepository jpaProductRepository;

    private JpaBookAuthorRepository jpaBookAuthorRepository;

    private JpaUserRepository userRepository;

    private JpaRoleRepository jpaRoleRepository;

    private UserService userService;

    private JpaUserOrderRepository jpaUserOrderRepository;

    private JpaOrderDetailsRepository jpaOrderDetailsRepository;

    @Autowired
    public InitialDataConfig(JpaCategoryRepository jpaCategoryRepository, JpaRoleRepository jpaRoleRepository, JpaProductRepository jpaProductRepository, JpaUserRepository userRepository, UserService userService, JpaUserOrderRepository jpaUserOrderRepository,
                             JpaOrderDetailsRepository jpaOrderDetailsRepository,
                             JpaBookAuthorRepository jpaBookAuthorRepository) {
            this.jpaCategoryRepository = jpaCategoryRepository;
            this.jpaProductRepository = jpaProductRepository;
            this.userRepository = userRepository;
            this.jpaRoleRepository = jpaRoleRepository;
            this.userService = userService;
            this.jpaUserOrderRepository = jpaUserOrderRepository;
            this.jpaOrderDetailsRepository = jpaOrderDetailsRepository;
            this.jpaBookAuthorRepository = jpaBookAuthorRepository;
    }

    @PostConstruct
    public void init() {


        User user = new User("Jan", "Kowalski", "kowalski123", "kowalski@wp.pl", "a", true);
        User user1 = new User("Jan", "Kowalski", "admin", "a@a.com", "admin", true);
        User user2 = new User("Marcin", "Kowalski", "ronaldo99", "abc@xyz.com", "aaaa", true);
        User user3 = new User("Michał", "Anioł", "malutki", "kkoko@xyz.com", "aniol", true);

            userRepository.save(user);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        Role warehouseman = new Role("Warehouseman");
        Role admin = new Role("Admin");
        Role office = new Role("Office");

        jpaRoleRepository.save(warehouseman);
        jpaRoleRepository.save(admin);
        jpaRoleRepository.save(office);
        userService.addRoleToUser(user, warehouseman);
        userService.addRoleToUser(user1, admin);
        userService.addRoleToUser(user2, office);
        userService.addRoleToUser(user3, warehouseman);

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


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


        BookAuthor bookAuthor = new BookAuthor("Cay S. Horstman");
        BookAuthor bookAuthor2 = new BookAuthor("David Flanganan");
        BookAuthor bookAuthor3 = new BookAuthor("Janina Porazińska");
        BookAuthor bookAuthor4 = new BookAuthor("Wojciech Miłoszewski");
        BookAuthor bookAuthor5 = new BookAuthor("Leszek Herman");
        BookAuthor bookAuthor6 = new BookAuthor("Adam Mickiewicz");
        BookAuthor bookAuthor7 = new BookAuthor("Katarzyna Bonda");
        BookAuthor bookAuthor8 = new BookAuthor("Jo Nesbo");

        jpaBookAuthorRepository.save(bookAuthor);
        jpaBookAuthorRepository.save(bookAuthor2);
        jpaBookAuthorRepository.save(bookAuthor3);
        jpaBookAuthorRepository.save(bookAuthor4);
        jpaBookAuthorRepository.save(bookAuthor5);
        jpaBookAuthorRepository.save(bookAuthor6);
        jpaBookAuthorRepository.save(bookAuthor7);
        jpaBookAuthorRepository.save(bookAuthor8);

        Product product = new Product("Java podstawy",
                "PIERWSZY PRODUKT Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, bookAuthor, "9788328324800", 69);

        Product product1 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                150,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category, bookAuthor2, "978-83-283-0623-3", 47.50);

        Product product2 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5, bookAuthor3, "978-83-280-3885-1123", 15.54);
        Product product3 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5, bookAuthor3, "978-83-10-12986-4", 9.99);

        Product product4 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                0,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor4, "978835428044234", 32.00);

        Product product5 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor5, "978-8-3287-0395-7", 20.22);


        Product product6 = new Product("Java podstawy",
                "PIERWSZY PRODUKT Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, bookAuthor, "978832854324800", 69);

        Product product7 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                150,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category, bookAuthor2, "978-83-234583-0623-3", 47.50);

        Product product8 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5, bookAuthor3, "978-83-280-3885-1", 15.54);
        Product product9 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5, bookAuthor3, "923478-83-10-12986-4", 9.99);

        Product product10 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                0,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor4, "9788354280441234", 32.00);

        Product product11 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor5, "978-8-32765487-0395-7", 20.22);

        Product product12 = new Product("Java podstawy",
                "PIERWSZY PRODUKT Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, bookAuthor, "9788326548324800", 69);

        Product product13 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                150,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category, bookAuthor2, "978-23483-283-0623-3", 47.50);

        Product product14 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5, bookAuthor3, "978-83-223480-3885-1", 15.54);
        Product product15 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5, bookAuthor3, "978-83-65410-12986-4", 9.99);

        Product product16 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                0,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor4, "9788326548044234", 32.00);

        Product product17 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor5, "978-8-3287-0234395-7", 20.22);

        Product product18 = new Product("Java podstawy",
                "PIERWSZY PRODUKT Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, bookAuthor, "9788328324234800", 69);

        Product product19 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                150,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category, bookAuthor2, "978-83-223483-0623-3", 47.50);

        Product product20 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5, bookAuthor3, "978-83-280-3888765-1", 15.54);
        Product product21 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5, bookAuthor3, "978-83-10-12986-487", 9.99);

        Product product22 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                0,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor4, "978832328044234", 32.00);

        Product product23 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor5, "978-8-3265487-0395-7", 20.22);

        Product product24 = new Product("Java podstawy",
                "PIERWSZY PRODUKT Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, bookAuthor, "978832836524800", 69);

        Product product25 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                150,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category, bookAuthor2, "978-83-2897683-0623-3", 47.50);

        Product product26 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5, bookAuthor3, "978-83-264580-3885-1", 15.54);
        Product product27 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5, bookAuthor3, "978-898673-10-12986-4", 9.99);

        Product product28 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                0,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor4, "9788325438044234", 32.00);

        Product product29 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor5, "978-8-3987287-0395-7", 20.22);

        Product product30 = new Product("Java podstawy",
                "PIERWSZY PRODUKT Najważniejszy praktyczny poradnik i podręcznik dla doświadczonych programistów dążących do " +
                        "doskonalenia swoich umiejętności w zakresie posługiwania się językiem Java.",
                "Rząd 1; miejsce 1",
                15,
                "https://static01.helion.com.pl/global/okladki/326x466/4dbd7b921aa2dafbf867c4ac097d6998,javp10.jpg",
                category, bookAuthor, "9788323128324800", 69);

        Product product31 = new Product("Java w pigułce",
                "Java to język programowania wybierany wszędzie tam, gdzie są wymagane najwyższe bezpieczeństwo i wydajność.",
                "Rząd 1; miejsce 2",
                150,
                "https://static01.helion.com.pl/global/okladki/326x466/06a3d95a2c11220eb256f2b29d604546,javpi6.jpg",
                category, bookAuthor2, "978-83-2097883-0623-3", 47.50);

        Product product32 = new Product("Pamiętnik czarnego noska",
                "Miś zwany Czarnym Noskiem bardzo nudził się na sklepowej witrynie, marzył, żeby jakieś miłe i grzeczne dziecko zabrało go do domu.",
                "Rząd 4; miejsce 1",
                15,
                "http://webimage.pl/pics/184/6/d9788328046184.jpg",
                category5, bookAuthor3, "978-8r4233-280-3885-1", 15.54);
        Product product33 = new Product("Szewczyk dratewka",
                "Znana baśń o szewczyku, który musi wykonać trzy trudne zadania, aby uwolnić pannę zamkniętą na wieży przez złą czarownicę. " +
                        "Pomagają mu w tym zaprzyjaźnione z nim zwierzęta.",
                "Rząd 4; miejsce 2",
                15,
                "http://webimage.pl/pics/864/9/d187777.jpg",
                category5, bookAuthor3, "978-83-1750-12986-4", 9.99);

        Product product34 = new Product("Inwazja",
                "Współczesna Polska, w której żyją: Roman, były żołnierz wojsk specjalnych i uczestnik wielu zagranicznych misji, " +
                        "Danuta kobieta kochająca luksus, ciężko pracująca i całkowicie uzależniona od ojca tyrana oraz Michał, " +
                        "bezrobotny historyk z żoną i dwójką dzieci.",
                "Rząd 2; miejsce 1",
                0,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor4, "2349788328044234", 32.00);

        Product product35 = new Product("Latarnia umarłych",
                "Akcja \"Latarni umarłych\" rozgrywa się w rok po wydarzeniach opisanych w \"Sedinum\" - wiadomość z podziemi.",
                "Rząd 2; miejsce 1",
                15,
                "http://webimage.pl/pics/234/4/d857398.jpg",
                category3, bookAuthor5, "978-8-67543287-0395-7", 20.22);

        jpaProductRepository.save(product);
        jpaProductRepository.save(product1);
        jpaProductRepository.save(product2);
        jpaProductRepository.save(product3);
        jpaProductRepository.save(product4);
        jpaProductRepository.save(product5);

        jpaProductRepository.save(product6);
        jpaProductRepository.save(product7);
        jpaProductRepository.save(product8);
        jpaProductRepository.save(product9);
        jpaProductRepository.save(product10);
        jpaProductRepository.save(product11);
        jpaProductRepository.save(product12);
        jpaProductRepository.save(product13);
        jpaProductRepository.save(product14);
        jpaProductRepository.save(product15);
        jpaProductRepository.save(product16);
        jpaProductRepository.save(product17);
        jpaProductRepository.save(product18);
        jpaProductRepository.save(product19);
        jpaProductRepository.save(product20);
        jpaProductRepository.save(product21);
        jpaProductRepository.save(product22);
        jpaProductRepository.save(product23);
        jpaProductRepository.save(product24);
        jpaProductRepository.save(product25);
        jpaProductRepository.save(product26);
        jpaProductRepository.save(product27);
        jpaProductRepository.save(product28);
        jpaProductRepository.save(product29);
        jpaProductRepository.save(product30);
        jpaProductRepository.save(product31);
        jpaProductRepository.save(product32);
        jpaProductRepository.save(product33);
        jpaProductRepository.save(product34);
        jpaProductRepository.save(product35);


        jpaUserOrderRepository.save(new UserOrder(user, "01/2017"));
        jpaUserOrderRepository.save(new UserOrder(user, "02/2017"));
        jpaUserOrderRepository.save(new UserOrder(user, "03/2017"));
        UserOrder sampleOrder = new UserOrder(user1, "04/2017");
        sampleOrder.setIsApproved(false);
        jpaUserOrderRepository.save(sampleOrder);
        jpaUserOrderRepository.save(new UserOrder(user2, "05/2017"));

        jpaUserOrderRepository.save(new UserOrder(user3, "06/2017"));
        jpaUserOrderRepository.save(new UserOrder(user3, "07/2017"));
        jpaUserOrderRepository.save(new UserOrder(user3, "08/2017"));
        jpaUserOrderRepository.save(new UserOrder(user3, "09/2017"));

    }
}
