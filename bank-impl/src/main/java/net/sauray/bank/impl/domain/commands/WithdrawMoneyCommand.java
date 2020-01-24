package net.sauray.bank.impl.domain.commands;

import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import net.sauray.bank.impl.domain.events.MoneyWithdrawnPrivateEvent;

public class WithdrawMoneyCommand implements BankCommand, PersistentEntity.ReplyType<MoneyWithdrawnPrivateEvent> {

  public final Long amountCents;

  public WithdrawMoneyCommand(Long amountCents) {
    this.amountCents = Preconditions.checkNotNull(amountCents, amountCents);
  }

}
