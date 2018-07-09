package eu.farmadin.jsf_react_example.persistance;

import eu.farmadin.jsf_react_example.persistance.dao.ProductRepository;
import eu.farmadin.jsf_react_example.persistance.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.math.BigDecimal;

@Startup
@Singleton
@TransactionManagement(value=TransactionManagementType.BEAN)
public class DbInitialiser {

    @Inject
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        insertProucts();
    }

    private void insertProucts() {
        Product product = new Product("Product1", new BigDecimal(12));
        productRepository.save(product);
    }
}
