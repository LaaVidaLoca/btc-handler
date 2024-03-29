package ru.tsipino.sbertech.btchandler.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;
import ru.tsipino.sbertech.btchandler.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  Optional<Subscription> findFirstByChatIdAndParameters(Long chatId, CurrencyParameters parameters);

  List<Subscription> findAllByChatId(Long chatId);

  @Transactional
  void deleteByChatIdAndParameters(Long chatId, CurrencyParameters parameters);
}
