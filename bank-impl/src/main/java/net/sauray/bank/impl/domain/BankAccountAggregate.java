package net.sauray.bank.impl.domain;

import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import net.sauray.bank.impl.domain.commands.BankCommand;

public class BankAccountAggregate {

  public static EntityTypeKey<BankCommand> ENTITY_TYPE_KEY =
    EntityTypeKey
      .create(BankCommand.class, "BankAccountAggregate");

}
