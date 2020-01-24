package net.sauray.hello.impl;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

import com.lightbend.lagom.javadsl.api.ServiceCall;

import org.junit.Test;

import net.sauray.bank.api.application.BankController;
import net.sauray.bank.api.application.WithdrawRequest;
import net.sauray.bank.api.domain.events.MoneyWithdrawnEvent;

public class BankServiceTest {

  @Test
  public void shouldStorePersonalizedGreeting() throws Exception {
    withServer(defaultSetup().withCassandra(), server -> {
      BankController controller = server.client(BankController.class);
      WithdrawRequest request = new WithdrawRequest(10l, "account-1", "account-2");
      MoneyWithdrawnEvent event = controller.withdrawMoney().invoke(request).toCompletableFuture().get();
      System.out.println(event.amountCents);

    });
  }

}
