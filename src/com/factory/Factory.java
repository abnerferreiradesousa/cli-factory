package com.factory;

import java.util.Scanner;

/**
 * Classe principal que gerencia todo o funcionamento da aplicação CLI Factory.
 */
public class Factory {

  public static void main(String[] args) {
    String optionChoosed = null;
    boolean continueMenu = true;

    FactoryInterativeCli factoryInterativeCli = new FactoryInterativeCli();
    try (Scanner sc = new Scanner(System.in)) {
      System.out
          .println("========================================================================");
      System.out
          .println("####################### BEM-VINDO AO CLI FACTORY #######################");
      System.out
          .println("========================================================================");
      while (continueMenu) {
        System.out.println("[1] - Registrar uma nova ordem de produção");
        System.out.println("[2] - Listar todas as ordens de produção");
        System.out.println("[3] - Atualizar o status de conclusão de uma ordem de produção");
        System.out.println("[4] - Ver relatórios de produção");
        System.out.println("[5] - Há recursos para produzir o produto especificado");
        System.out.println("[6] - Sair");

        if (sc.hasNext()) {
          optionChoosed = sc.next();
          sc.nextLine();
        }

        switch (optionChoosed) {
          case "1":
            factoryInterativeCli.registerOrderProductConsole(sc);
            break;
          case "2":
            factoryInterativeCli.listAllOrdersProductConsole();
            break;
          case "3":
            factoryInterativeCli.updateStatusIsDoneConsole(sc);
            break;
          case "4":
            factoryInterativeCli.listAllOrdersProductByStatusConsole(sc);
            break;
          case "5":
            factoryInterativeCli.checkIfThereIsEnoughResourcesConsole(sc);
            break;
          case "6":
            continueMenu = false;
            break;
          default:
            System.out.println("Escolha uma opção válida!");
            break;
        }
      }

      System.out
          .println("========================================================================");
      System.out
          .println("+++++++++++++++++++++++++++++++++ FIM ++++++++++++++++++++++++++++++++++");
      System.out
          .println("========================================================================");
    } catch (Exception ex) {
      System.out.println("Erro: " + ex);
      System.out.println("Por favor, reinicie o programa.");
    }
  }
}

