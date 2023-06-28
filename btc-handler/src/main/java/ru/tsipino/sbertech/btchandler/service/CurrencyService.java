package ru.tsipino.sbertech.btchandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tsipino.sbertech.btchandler.HTMLCurrencyParser;
import ru.tsipino.sbertech.btchandler.HTMLExtractor;
import ru.tsipino.sbertech.btchandler.models.Currency;
import ru.tsipino.sbertech.btchandler.models.CurrencyParameters;
import ru.tsipino.sbertech.btchandler.repository.CurrencyRepository;
import ru.tsipino.sbertech.btchandler.repository.ParametersRepository;

import java.io.IOException;
import java.util.List;

@Component // будем вызывать в UpdateCurrencyScheduler, НО в принципе что аннотация сервис что компонент одна фигня
public class CurrencyService implements ICurrencyService {

    private ParametersRepository parametersRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(ParametersRepository parametersRepository, CurrencyRepository currencyRepository) {
        this.parametersRepository = parametersRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void updateCurrencies() {
        List<CurrencyParameters> parametersList = parametersRepository.findAll();
        parametersList.stream().forEach(item -> {
            String html = HTMLExtractor.getHTML(item.getUrl());
            Double value = HTMLCurrencyParser.getCurrencyValue(html);
            currencyRepository.save(Currency.builder().value(value).parameters(item).build());
        });

    }
}
