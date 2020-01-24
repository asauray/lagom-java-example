package net.sauray.bank.impl.domain;

import net.sauray.bank.api.domain.events.BankEvent;
import net.sauray.bank.api.domain.events.MoneyWithdrawnEvent;
import net.sauray.bank.impl.domain.events.BankPrivateEvent;
import net.sauray.bank.impl.domain.events.MoneyWithdrawnPrivateEvent;

public class ISO {

  public static BankEvent privateToPublic(BankPrivateEvent privateEvent) {
    if(privateEvent instanceof MoneyWithdrawnPrivateEvent) {
      MoneyWithdrawnPrivateEvent moneyWithdrawnPrivateEvent = (MoneyWithdrawnPrivateEvent) privateEvent;
      return new MoneyWithdrawnEvent(moneyWithdrawnPrivateEvent.amountCents, "", "");
    } else {
      throw new IllegalArgumentException();
    }
  }

}
