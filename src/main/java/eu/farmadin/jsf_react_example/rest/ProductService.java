package eu.farmadin.jsf_react_example.rest;


import eu.farmadin.jsf_react_example.dto.ProductList;
import eu.farmadin.jsf_react_example.persistance.dao.ProductRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/product")
@RolesAllowed({"manager"})
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getAllProducts() {
        ProductList list = new ProductList();
        list.setProducts(productRepository.findAll());
        return Response.status(200).entity(list).build();

    }
}
