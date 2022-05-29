package pl.mniwicki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mniwicki.credit.Greeter;
import pl.mniwicki.productcatalog.MapProductStorage;
import pl.mniwicki.productcatalog.ProductCatalog;
import pl.mniwicki.productcatalog.ProductStorage;
import pl.mniwicki.sales.AvailableProducts;
import pl.mniwicki.sales.CartStorage;
import pl.mniwicki.sales.Sales;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    Greeter createGreeter() {
        return new Greeter();
    }

    @Bean
    ProductStorage lestCreateProductStorage() {
        MapProductStorage mapProductStorage = new MapProductStorage();
        return mapProductStorage;
    }

    @Bean
    ProductCatalog createProductCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);

        String productId = productCatalog.addProduct("logo-set", "nice one");
        productCatalog.assignImage(productId, "http://niceImage");
        productCatalog.assignPrice(productId, BigDecimal.TEN);
        productCatalog.publish(productId);

        String productId2 = productCatalog.addProduct("logo-set-2", "even nicer one");
        productCatalog.assignImage(productId2, "http://niceImage");
        productCatalog.assignPrice(productId2, BigDecimal.valueOf(20.20));
        productCatalog.publish(productId2);

        return productCatalog;
    }

    @Bean
    Sales createSales() {
        return new Sales(new AvailableProducts(Collections.emptyList()), new CartStorage());
    }
}
