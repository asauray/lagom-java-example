package net.sauray.bank.impl.application;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import net.sauray.bank.api.application.BankController;


public class BankModule extends AbstractModule implements ServiceGuiceSupport {

  @Override
  protected void configure() {
    bindService(BankController.class, BankControllerImpl.class);
  }


}
