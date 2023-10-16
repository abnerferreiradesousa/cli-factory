package com.factory;

import java.util.Random;

/**
 * Gerencia os recursos da fábrica.
 */
public class FactoryResource {
  /**
   * Mensagem do Abner: Não ficou claro pra mim quais os critérios da disponibildade ou não de
   * recursos. Então, implementei um método que gerasse aleatoriamente resultados sobre a
   * disponibilidade(true) ou não(false) de recursos para simular o comportamento de checagem de
   * recursos.
   * 
   * @return True se existir recursos suficientes, do contrário, false.
   */
  public boolean isThereEnoughResources() {
    Random random = new Random();
    int randomNumber = random.nextInt(10) + 1;
    return randomNumber % 2 == 0;
  }
}
