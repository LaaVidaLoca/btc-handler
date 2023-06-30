package ru.tsipino.sbertech.btchandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsipino.sbertech.btchandler.util.HTMLCurrencyParser;
import ru.tsipino.sbertech.btchandler.util.HTMLExtractor;
import ru.tsipino.sbertech.btchandler.entity.Currency;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;
import ru.tsipino.sbertech.btchandler.repository.CurrencyRepository;
import ru.tsipino.sbertech.btchandler.repository.ParametersRepository;

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
