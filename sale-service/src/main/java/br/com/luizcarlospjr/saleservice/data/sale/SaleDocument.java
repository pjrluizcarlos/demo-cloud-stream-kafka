package br.com.luizcarlospjr.saleservice.data.sale;

import br.com.luizcarlospjr.saleservice.controller.sale.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class SaleDocument {

    @Id
    private String id;

    private String productId;
    private Long quantity;
    private SaleStatus status;

}
