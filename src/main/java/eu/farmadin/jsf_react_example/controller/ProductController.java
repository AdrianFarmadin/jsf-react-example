package eu.farmadin.jsf_react_example.controller;

import eu.farmadin.jsf_react_example.persistance.dao.ProductRepository;
import eu.farmadin.jsf_react_example.persistance.model.Product;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ELBeanName(value = "productController")
@Named("productController")
@RequestScoped
@Join(path = "/new-product", to = "/product-form.xhtml")
public class ProductController {

    @Inject
    private ProductRepository productRepository;

    private Product product = new Product();

    public String save() {
        productRepository.save(product);
        product = new Product();
        return "/product/product-list.xhtml?faces-redirect=true";
    }

    public Product getProduct() {
        return product;
    }
}
