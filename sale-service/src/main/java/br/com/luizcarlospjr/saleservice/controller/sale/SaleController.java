package br.com.luizcarlospjr.saleservice.controller.sale;

import br.com.luizcarlospjr.saleservice.data.sale.SaleDAO;
import br.com.luizcarlospjr.saleservice.data.sale.SaleDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.luizcarlospjr.saleservice.controller.sale.SaleStatus.VERIFICANDO_ESTOQUE;

@RestController
@RequestMapping("/api/v2/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleDAO dao;
    private final StreamBridge streamBridge;

    @PostMapping
    public SaleDocument save(@RequestBody SaleDocument input) {
        SaleDocument created = dao.save(input.withStatus(VERIFICANDO_ESTOQUE));
        streamBridge.send("SaleProducer-out-0", created);

        return created;
    }

    @GetMapping
    public List<SaleDocument> findAll() {
        return dao.findAll();
    }

}
