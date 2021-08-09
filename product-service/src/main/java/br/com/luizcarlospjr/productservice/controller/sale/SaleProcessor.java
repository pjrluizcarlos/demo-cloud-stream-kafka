package br.com.luizcarlospjr.productservice.controller.sale;

import br.com.luizcarlospjr.productservice.data.product.ProductDocument;
import br.com.luizcarlospjr.productservice.data.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class SaleProcessor implements Function<SaleEvent, SaleEvent> {

    private final ProductRepository repository;

    @Override
    public SaleEvent apply(SaleEvent event) {
        ProductDocument document = repository.findById(event.getProductId())
                .orElseThrow(() -> new RuntimeException(format("There is no product with ID [%s]", event.getProductId())));

        long remainingQuantityAfterSale = document.getStockQuantity() - event.getQuantity();

        if (remainingQuantityAfterSale < 0) {
            return event.withStatus(SaleStatus.ESTOQUE_INSUFICIENTE);
        }

        repository.save(document.withStockQuantity(remainingQuantityAfterSale));

        return event.withStatus(SaleStatus.A_CAMINHO);
    }

}