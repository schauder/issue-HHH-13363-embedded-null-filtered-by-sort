package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    EntityManager entityManager;

    // fails
    @Test
    public void sortingByEmptyNestedFieldsShouldReturnAllProducts() {
        entityManager.persist(new Product("FUBAR", null));

		List<Product> products = productRepository.findAll(Sort.by("vendorInfo.vendor.address.street"));
        assertEquals("Repository should return demo product", 1, products.size());
    }

    @Test
    public void sortingShouldReturnAllProducts() {
		VendorAddress address = entityManager.merge(new VendorAddress("FooCity", "12345", "FooStreet", "1"));
		Vendor vendor = entityManager.merge(new Vendor("ACME", address));
        entityManager.merge(new Product("FUBAR", new Product.VendorInfo(vendor, "Best buddy")));

		List<Product> products = productRepository.findAll(Sort.by("vendorInfo.vendor.address.street"));
        assertEquals("Repository should return demo product", 1, products.size());
    }

}