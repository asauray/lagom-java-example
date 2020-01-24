package net.sauray.bank.impl.domain.state;

import com.google.common.base.Preconditions;

import lombok.Value;

@Value
public class BankAccountState {

  public final Long amountCents;

  public BankAccountState(Long amountCents) {
    this.amountCents = Preconditions.checkNotNull(amountCents, "amount");
  }

  public BankAccountState withAmount(Long amountCents) {
    return new BankAccountState(amountCents);
  }

}
