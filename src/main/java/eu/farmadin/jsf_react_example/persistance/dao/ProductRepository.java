package eu.farmadin.jsf_react_example.persistance.dao;

import eu.farmadin.jsf_react_example.persistance.model.Product;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Repository
public interface ProductRepository extends EntityRepository<Product, Long> {
}
