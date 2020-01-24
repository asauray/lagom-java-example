package net.sauray.bank.api.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value
public final class MoneyWithdrawnEvent implements BankEvent {

  public final Long amountCents;
  public final String name;
  public final String message;

  @JsonCreator
  public MoneyWithdrawnEvent(
    @JsonProperty("amount") Long amountCents, 
    @JsonProperty("name") String name,
    @JsonProperty("message") String message) 
  {
    this.amountCents = Preconditions.checkNotNull(amountCents, "amount");
    this.name = Preconditions.checkNotNull(name, "name");
    this.message = Preconditions.checkNotNull(name, "message");
  }

  @Override
  public String getName() {
    return name;
  }
}
