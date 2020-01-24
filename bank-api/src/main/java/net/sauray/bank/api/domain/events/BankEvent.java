package net.sauray.bank.api.domain.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MoneyWithdrawnEvent.class, name = "money-withdrawn")
})
public interface BankEvent {
  String getName();
}
