package ru.tsipino.sbertech.btchandler.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsipino.sbertech.btchandler.entity.Currency;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
  List<Currency> findAllByParameters(CurrencyParameters parameters);
}
