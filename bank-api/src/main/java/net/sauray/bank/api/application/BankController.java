package net.sauray.bank.api.application;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.Service.topic;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;
import com.lightbend.lagom.javadsl.api.transport.Method;

import net.sauray.bank.api.domain.events.*;

/**
 * The Hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Hello.
 */
public interface BankController extends Service {

  /**
   * Example: curl http://localhost:9000/api/hello/Alice
   */
  ServiceCall<WithdrawRequest, MoneyWithdrawnEvent> withdrawMoney();


  /**
   * This gets published to Kafka.
   */
  Topic<BankEvent> bankEvents();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("bank").withCalls(
       restCall(Method.POST, "/api/widthdraw",  this::withdrawMoney)
      ).withTopics(
          topic("bank-events", this::bankEvents)
          // Kafka partitions messages, messages within the same partition will
          // be delivered in order, to ensure that all messages for the same user
          // go to the same partition (and hence are delivered in order with respect
          // to that user), we configure a partition key strategy that extracts the
          // name as the partition key.
          .withProperty(KafkaProperties.partitionKeyStrategy(), BankEvent::getAccountId)
        ).withAutoAcl(true);
    // @formatter:on
  }
}
