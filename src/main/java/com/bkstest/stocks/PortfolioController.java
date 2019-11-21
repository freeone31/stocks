package com.bkstest.stocks;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {

    @PostMapping(value = "/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Portfolio portfolio(@RequestBody Asset asset) throws Exception {
        return new IexCloud().getPortfolio(asset);
    }
}
