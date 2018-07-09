package eu.farmadin.jsf_react_example.controller;

import eu.farmadin.jsf_react_example.persistance.dao.ProductRepository;
import eu.farmadin.jsf_react_example.persistance.model.Product;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ELBeanName(value = "listProducts")
@Named("listProducts")
@RequestScoped
@Join(path = "/product", to = "/product-list.xhtml")
public class ListProductsController implements Serializable {


    @Inject
    private ProductRepository productRepository;

    private List<Product> products;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
        products = productRepository.findAll();
    }

    public List<Product> getProducts() {
        return products;
    }

    public String delete(Product product) {
        productRepository.remove(product);
        loadData();
        return null;
    }
}
