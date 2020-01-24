package net.sauray.bank.impl.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

import lombok.Value;

@Value
public class MoneyWithdrawnPrivateEvent implements BankPrivateEvent {

  public final Long amountCents;

  @JsonCreator
  public MoneyWithdrawnPrivateEvent(
      @JsonProperty("amount") Long amountCents
  ) {
    this.amountCents = Preconditions.checkNotNull(amountCents);
  }

  

}
