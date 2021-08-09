package br.com.luizcarlospjr.productservice.controller.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class SaleEvent {

    private String id;
    private String productId;
    private Long quantity;
    private SaleStatus status;

}
