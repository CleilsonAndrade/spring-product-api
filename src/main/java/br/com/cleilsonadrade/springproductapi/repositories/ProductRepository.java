package br.com.cleilsonadrade.springproductapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cleilsonadrade.springproductapi.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}