package service;

import model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p ) {
        products.add(p);
    }
    public List<Product> findAll() {
        return products;
    }
}
