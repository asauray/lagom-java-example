package net.sauray.bank.impl.domain.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MoneyWithdrawnPrivateEvent.class, name="money-withdrawn")
})
public interface BankPrivateEvent extends Jsonable, AggregateEvent<BankPrivateEvent>{

  AggregateEventShards<BankPrivateEvent> TAG = AggregateEventTag.sharded(BankPrivateEvent.class, 4);

  default public AggregateEventTagger<BankPrivateEvent> aggregateTag() {
    return TAG;
  }
}
