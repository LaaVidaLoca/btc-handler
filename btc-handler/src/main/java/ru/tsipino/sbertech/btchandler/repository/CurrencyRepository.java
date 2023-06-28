package ru.tsipino.sbertech.btchandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsipino.sbertech.btchandler.models.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
}
