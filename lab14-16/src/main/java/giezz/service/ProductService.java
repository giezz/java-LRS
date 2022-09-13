package giezz.service;

import giezz.model.Product;
import giezz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Page<Product> pageGetAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void remove(Product product) {
        productRepository.delete(product);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public void edit(Product product) {
        productRepository.save(product);
    }

    public List<Product> findTop3() {
        return productRepository
                .findAll(new Sort(Sort.Direction.DESC, "count"))
                .stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    @Deprecated
    private List<Product> filteredList;

    @Deprecated
    public ProductService setFilteredList(List<Product> listForFilter) {
        this.filteredList = productRepository.findAll();
        return this;
    }

    @Deprecated
    public ProductService filterByTitle(String title) {
        filteredList = filteredList.stream().
                filter(product -> product.getTitle()
                        .toLowerCase(Locale.ROOT)
                        .contains(title.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
        return this;
    }

    @Deprecated
    public ProductService filterByMaxPrice(double maxPrice) {
        filteredList = filteredList.stream().filter(product -> product.getPrice() <= maxPrice).
                collect(Collectors.toList());
        return this;
    }

    @Deprecated
    public ProductService filterByMinPrice(double minPrice) {
        filteredList = filteredList.stream().filter(product -> product.getPrice() >= minPrice).
                collect(Collectors.toList());
        return this;
    }

    @Deprecated
    public List<Product> getFilteredList() {
        return filteredList;
    }

    public Page<Product> getFilteredList(String title, int minValue, int maxValue, Pageable pageable) {
        return productRepository.findAll(
                Specification.where(ProductSpecification.minValue(minValue))
                        .and(ProductSpecification.maxValue(maxValue))
                        .and(ProductSpecification.hasTitle(title.toLowerCase(Locale.ROOT))), pageable
        );
    }

    public static class ProductSpecification {

        public static Specification<Product> hasTitle(String title) {
            return (r, q, cb) ->
                    cb.like(cb.lower(r.get("title")), "%" + title + "%");
        }

        public static Specification<Product> minValue(int min) {
            return (r, q, cb) ->
                    cb.greaterThanOrEqualTo(r.get("price"), min);
        }

        public static Specification<Product> maxValue(int max) {
            return (r, q, cb) ->
                    cb.lessThanOrEqualTo(r.get("price"), max);
        }
    }
}