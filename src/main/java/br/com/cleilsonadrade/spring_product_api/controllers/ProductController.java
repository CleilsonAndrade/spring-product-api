package br.com.cleilsonadrade.spring_product_api.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonadrade.spring_product_api.dtos.ProductRecordDTO;
import br.com.cleilsonadrade.spring_product_api.models.ProductModel;
import br.com.cleilsonadrade.spring_product_api.repositories.ProductRepository;
import jakarta.validation.Valid;

@RestController
public class ProductController {
  @Autowired
  ProductRepository productRepository;

  @PostMapping("/products")
  public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDTO, productModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
  }

  @GetMapping("/products")
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    List<ProductModel> productsList = productRepository.findAll();

    if (!productsList.isEmpty()) {
      for (ProductModel product : productsList) {
        UUID id = product.getId();
        product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
      }

      return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Object> getOneProduct(@PathVariable UUID id) {
    Optional<ProductModel> productO = productRepository.findById(id);
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));

    return ResponseEntity.status(HttpStatus.OK).body(productO.get());
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Object> updateProduct(@PathVariable UUID id,
      @RequestBody @Valid ProductRecordDTO productRecordDTO) {
    Optional<ProductModel> productO = productRepository.findById(id);
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    var productModel = productO.get();
    BeanUtils.copyProperties(productRecordDTO, productModel);

    return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
    Optional<ProductModel> productO = productRepository.findById(id);
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    productRepository.delete(productO.get());

    return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
  }
}