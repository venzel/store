package br.com.venzel.store.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.venzel.store.modules.order.entities.Order;
import br.com.venzel.store.modules.order.entities.OrderItem;
import br.com.venzel.store.modules.order.repositories.OrderItemRepository;
import br.com.venzel.store.modules.order.repositories.OrderRepository;
import br.com.venzel.store.modules.payment.entities.Payment;
import br.com.venzel.store.modules.payment.entities.PaymentCard;
import br.com.venzel.store.modules.payment.entities.types.PaymentState;
import br.com.venzel.store.modules.payment.repositories.PaymentRepository;
import br.com.venzel.store.modules.product.entities.Category;
import br.com.venzel.store.modules.product.entities.Product;
import br.com.venzel.store.modules.product.repositories.CategoryRepository;
import br.com.venzel.store.modules.product.repositories.ProductRepository;
import br.com.venzel.store.modules.user.entities.Address;
import br.com.venzel.store.modules.user.entities.City;
import br.com.venzel.store.modules.user.entities.State;
import br.com.venzel.store.modules.user.entities.User;
import br.com.venzel.store.modules.user.entities.types.UserType;
import br.com.venzel.store.modules.user.providers.hash_provider.HashProvider;
import br.com.venzel.store.modules.user.repositories.AddressRepository;
import br.com.venzel.store.modules.user.repositories.CityRepository;
import br.com.venzel.store.modules.user.repositories.StateRepository;
import br.com.venzel.store.modules.user.repositories.UserRepository;

@Configuration
public class DataInstanceConfig implements CommandLineRunner {

    /* Repositories injecteds */
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Qualifier("mockHashProvider")
    @Autowired
    private HashProvider hashProvider;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    /* Auto execute method */

    @Override
    public void run(String... args) throws Exception {
        
        /* Category */

        Category ct_1 = new Category("cereais");
        Category ct_2 = new Category("padaria");
        Category ct_3 = new Category("mercearia");

        /* Product */

        Product pt_1 = new Product("feijao", 10.21);
        Product pt_2 = new Product("arroz", 7.44);
        Product pt_3 = new Product("cuzcuz", 3.76);

        /* User */

        User us_1 = new User("Tiago Rizzo", "tiago@gmail.com", hashProvider.generateHash("tiago"), UserType.LEGAL_PERSON);
        User us_2 = new User("Alex Moura", "alex@gmail.com", hashProvider.generateHash("alex"), UserType.PHYSICAL_PERSON);
        User us_3 = new User("Liz Venzel", "liz@gmail.com", hashProvider.generateHash("liz"), UserType.LEGAL_PERSON);

        /* Order */

        Order or_1 = new Order(us_1);
        Order or_2 = new Order(us_2);
        Order or_3 = new Order(us_3);

        /* Payment */

        Payment pg_1 = new PaymentCard(PaymentState.PENDING, or_1, 3);
        or_1.setPayment(pg_1);

        Payment pg_2 = new PaymentCard(PaymentState.PENDING, or_2, 6);
        or_2.setPayment(pg_2);

        Payment pg_3 = new PaymentCard(PaymentState.PENDING, or_3, 12);
        or_3.setPayment(pg_3);

        us_1.getOrders().addAll(Arrays.asList(or_1, or_2));

        /* State */

        State st_1 = new State("SP");
        State st_2 = new State("RJ");
        State st_3 = new State("PE");
        State st_4 = new State("PB");

        /* City */

        City cy_1 = new City("Bauru", st_1);
        City cy_2 = new City("Campinas", st_1);
        City cy_3 = new City("Macae", st_2);
        City cy4 = new City("Recife", st_3);
        City cy5 = new City("Campina Grande", st_4);

        /* Address */

        Address ad_1 = new Address("Rua 13 de maio", "213", null, null, "58429077", us_1, cy_1);
        Address ad_2 = new Address("Rua Pedro II", "34", null, null, "58429077", us_2, cy_2);
        Address ad_3 = new Address("Rua Afonso Campos", "90", null, null, "58429077", us_1, cy_3);
        Address ad_4 = new Address("Avenida Santa Cruz", "102", null, null, "58429077", us_3, cy4);
        Address ad_5 = new Address("Avenida Santa Cruz", "102", null, null, "58429077", us_1, cy4);

        /* OrdeItem */

        OrderItem oi_1 = new OrderItem(or_1, pt_1, 0.00, 3, 11.21);
        OrderItem oi_2 = new OrderItem(or_1, pt_3, 0.00, 2, 7.45);
        OrderItem oi_3 = new OrderItem(or_2, pt_2, 10.00, 4, 4.28);

        or_1.getItens().addAll(Arrays.asList(oi_1, oi_2));
        or_2.getItens().addAll(Arrays.asList(oi_3));

        pt_1.getItens().addAll(Arrays.asList(oi_1));
        pt_2.getItens().addAll(Arrays.asList(oi_3));
        pt_3.getItens().addAll(Arrays.asList(oi_2));

        /* User : Add all address */

        us_1.getAdresses().addAll(Arrays.asList(ad_1, ad_2));
        us_2.getAdresses().addAll(Arrays.asList(ad_3));
        us_3.getAdresses().addAll(Arrays.asList(ad_4));
        us_3.getAdresses().addAll(Arrays.asList(ad_5));

        /* User : Add all telephones */

        us_1.getTelephones().addAll(Arrays.asList("3332020", "89122311", "32001222"));
        us_2.getTelephones().addAll(Arrays.asList("3012123", "44122314", "72315522"));
        us_3.getTelephones().addAll(Arrays.asList("6323442", "32454423", "43334768"));

        /* Category : Add all products */

        ct_1.getProducts().addAll(Arrays.asList(pt_1, pt_2, pt_3));
        ct_2.getProducts().addAll(Arrays.asList(pt_2));

        /* */

        pt_1.getCategories().addAll(Arrays.asList(ct_1));
        pt_2.getCategories().addAll(Arrays.asList(ct_1, ct_2));
        pt_3.getCategories().addAll(Arrays.asList(ct_1));

        /* Product : Add all categories */

        st_1.getCities().addAll(Arrays.asList(cy_1, cy_2));
        st_2.getCities().addAll(Arrays.asList(cy_3));
        st_3.getCities().addAll(Arrays.asList(cy4));
        st_4.getCities().addAll(Arrays.asList(cy5));

        /* Repositories : Add all */

        categoryRepository.saveAll(Arrays.asList(ct_1, ct_2, ct_3));
        productRepository.saveAll(Arrays.asList(pt_1, pt_2, pt_3));
        userRepository.saveAll(Arrays.asList(us_1, us_2, us_3));
        stateRepository.saveAll(Arrays.asList(st_1, st_2, st_3, st_4));
        cityRepository.saveAll(Arrays.asList(cy_1, cy_2, cy_3, cy4, cy5));
        addressRepository.saveAll(Arrays.asList(ad_1, ad_2, ad_3, ad_4, ad_5));
        orderRepository.saveAll(Arrays.asList(or_1, or_2, or_3));
        paymentRepository.saveAll(Arrays.asList(pg_1, pg_2, pg_3));
        orderItemRepository.saveAll(Arrays.asList(oi_1, oi_2, oi_3));
    }
}
