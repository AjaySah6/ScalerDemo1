package com.dev_ajay.scalerdemo1.Service;

import com.dev_ajay.scalerdemo1.Models.Category;
import com.dev_ajay.scalerdemo1.Models.Product;
import com.dev_ajay.scalerdemo1.Repositories.CategoryRepository;
import com.dev_ajay.scalerdemo1.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            // Handle the case where no product is found
            // (e.g., throw an exception or return null)
            return null;  // Example: return null if not found
        }
    }

    @Override
    public Product createProduct(String title, String description, double price, String image, String categoryTitle) {
        Product product = new Product(); //creating new product because in ProductRepository class -> save(); takes product entity to save the product in to DB
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
    //  product.setCategory(category); but we need to check if category given in parameter is new already exists in DB
    // If category found in DB, then we will not create new category in DB.
    // If category is not found in the DB, a new category will be created because of cascade persist type

        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);
        if(categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = newCategory;
            // categoryFromDatabase = categoryRepository.save(newCategory);
        }

        product.setCategory(categoryFromDatabase);

       // List<Product> productsTemp = categoryFromDatabase.getProducts();

        return productRepository.save(product);

    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(Category::getTitle).toList();
    }

    @Override
    public Product updateProduct(Long id, String title, double price, String description, String image, String category) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

}
