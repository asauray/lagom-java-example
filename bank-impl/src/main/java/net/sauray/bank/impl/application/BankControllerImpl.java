package net.sauray.bank.impl.application;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import akka.japi.Pair;
import com.google.inject.Inject;

import net.sauray.bank.api.application.*;
import net.sauray.bank.api.domain.events.*;

import net.sauray.bank.impl.domain.commands.*;
import net.sauray.bank.impl.domain.events.*;
import net.sauray.bank.impl.domain.BankAccountEntity;

public class BankControllerImpl implements BankController {

  private final PersistentEntityRegistry registry;

  @Inject
  public BankControllerImpl(PersistentEntityRegistry registry) {
    this.registry = registry;
    this.registry.register(BankAccountEntity.class);
  }


  @Override
  public Topic<BankEvent> bankEvents() {
    return TopicProducer.taggedStreamWithOffset(BankPrivateEvent.TAG, (tag, offset) -> 
      registry.eventStream(tag, offset).map(eventAndOffset -> {
        var privateEvent = eventAndOffset.first();
        var privateEventOffset = eventAndOffset.second();
        var publicEvent = net.sauray.bank.impl.domain.ISO.privateToPublic(privateEvent);
        return Pair.create(publicEvent, privateEventOffset); 
      })
    ); 
  }


  @Override
  public ServiceCall<WithdrawRequest, MoneyWithdrawnEvent> withdrawMoney() {
    return request -> {
      PersistentEntityRef<BankCommand> entityRef = registry.refFor(BankAccountEntity.class, request.fromAccountId);
      return entityRef.ask(new WithdrawMoneyCommand(1l)).thenApply(result -> new MoneyWithdrawnEvent(result.amountCents, request.fromAccountId, request.toAccountId));
     };
  }

}
