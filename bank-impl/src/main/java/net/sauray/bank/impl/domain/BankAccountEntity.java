package net.sauray.bank.impl.domain;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

import net.sauray.bank.impl.domain.state.*;
import net.sauray.bank.impl.domain.events.*;
import net.sauray.bank.impl.domain.commands.*;


public class BankAccountEntity extends PersistentEntity<BankCommand, BankPrivateEvent, BankAccountState> {

  final BankAccountState DEFAULT_STATE = new BankAccountState(0l);

  @Override
  public PersistentEntity<BankCommand, BankPrivateEvent, BankAccountState>.Behavior initialBehavior(Optional<BankAccountState> snapshotState) {

    BehaviorBuilder builder = newBehaviorBuilder(DEFAULT_STATE);
    
    builder.setCommandHandler(WithdrawMoneyCommand.class, (cmd, ctx) -> 
      ctx.thenPersist(new MoneyWithdrawnPrivateEvent(cmd.amountCents), evt -> ctx.reply(evt))
    );

    builder.setEventHandler(
      MoneyWithdrawnPrivateEvent.class, 
      (event) -> new BankAccountState(state().amountCents + event.amountCents)
    );

    return builder.build();
  }
}
