package net.sauray.bank.api.application;

import lombok.Value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;


@Value
@JsonDeserialize
public final class WithdrawRequest {

  public final Long amountCents;
  public final String fromAccountId;
  public final String toAccountId;

  @JsonCreator
  public WithdrawRequest(
    @JsonProperty("amount") Long amountCents,
    @JsonProperty("from") String fromAccountId,
    @JsonProperty("to") String toAccountId
  ) {
    this.amountCents = Preconditions.checkNotNull(amountCents, "amount");
    this.fromAccountId = Preconditions.checkNotNull(fromAccountId, "from");
    this.toAccountId = Preconditions.checkNotNull(toAccountId, "to");
  }

}
