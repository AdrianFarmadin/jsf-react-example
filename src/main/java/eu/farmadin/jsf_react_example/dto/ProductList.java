package eu.farmadin.jsf_react_example.dto;

import eu.farmadin.jsf_react_example.persistance.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductList {

    private List<Product> products;
}
