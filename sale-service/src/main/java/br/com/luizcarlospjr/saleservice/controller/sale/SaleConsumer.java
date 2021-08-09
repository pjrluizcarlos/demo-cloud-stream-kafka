package br.com.luizcarlospjr.saleservice.controller.sale;

import br.com.luizcarlospjr.saleservice.data.sale.SaleDAO;
import br.com.luizcarlospjr.saleservice.data.sale.SaleDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class SaleConsumer implements Consumer<SaleDocument> {

    private final SaleDAO dao;

    @Override
    public void accept(SaleDocument event) {
        dao.save(event);
    }

}
