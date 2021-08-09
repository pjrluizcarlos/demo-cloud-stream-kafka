package br.com.luizcarlospjr.productservice.data.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class ProductDocument {

    @Id
    private String id;

    private String name;
    private Long stockQuantity;

}
