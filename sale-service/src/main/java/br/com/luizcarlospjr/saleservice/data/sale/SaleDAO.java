package br.com.luizcarlospjr.saleservice.data.sale;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleDAO extends MongoRepository<SaleDocument, String> { }
