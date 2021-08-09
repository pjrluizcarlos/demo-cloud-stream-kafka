package br.com.luizcarlospjr.productservice.controller.product;

import br.com.luizcarlospjr.productservice.data.product.ProductRepository;
import br.com.luizcarlospjr.productservice.data.product.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository dao;

    @GetMapping
    public List<ProductDocument> findAll() {
        return dao.findAll();
    }

    @PostMapping
    public ProductDocument save(@RequestBody ProductDocument toCreate) {
        return dao.save(toCreate);
    }

}
