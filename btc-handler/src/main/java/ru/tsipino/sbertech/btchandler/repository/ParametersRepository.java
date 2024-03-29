package ru.tsipino.sbertech.btchandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;

@Repository
public interface ParametersRepository extends JpaRepository<CurrencyParameters, Long> {}
