package br.com.cleilsonadrade.springproductapi.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonadrade.springproductapi.dtos.ProductRecordDTO;
import br.com.cleilsonadrade.springproductapi.models.ProductModel;
import br.com.cleilsonadrade.springproductapi.repositories.ProductRepository;
import jakarta.validation.Valid;

@RequestMapping("/products")
@RestController
public class ProductController {
  @Autowired
  ProductRepository productRepository;

  @PostMapping
  public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDTO, productModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
  }

  @GetMapping
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

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOneProduct(@PathVariable UUID id) {
    Optional<ProductModel> productO = productRepository.findById(id);
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));

    return ResponseEntity.status(HttpStatus.OK).body(productO.get());
  }

  @PutMapping("/{id}")
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

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
    Optional<ProductModel> productO = productRepository.findById(id);
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    productRepository.delete(productO.get());

    return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
  }
}
