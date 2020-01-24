package net.sauray.bank.api.domain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value
public final class MoneyWithdrawnEvent implements BankEvent {

  public final Long amountCents;
  public final String accountId;

  @JsonCreator
  public MoneyWithdrawnEvent(
    @JsonProperty("amount") Long amountCents, 
    @JsonProperty("accountId") String accountId,
    @JsonProperty("message") String message) 
  {
    this.amountCents = Preconditions.checkNotNull(amountCents, "amount");
    this.accountId = Preconditions.checkNotNull(accountId, "accountId");
  }

  @Override
  public String getAccountId() {
    return accountId;
  }
}
