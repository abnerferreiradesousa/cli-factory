package com.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Essa classe é responsável principalmente pela interação com o usuário para reunião dos dados
 * necessários para as respectivas operações, e essas por sua vez acontecem na classe
 * FactoryManager.
 */
public class FactoryInterativeCli {

  private FactoryManager fm;

  /**
   * Contructor inicializando FactoryManager.
   */
  public FactoryInterativeCli() {
    this.fm = new FactoryManager();
  }

  /**
   * Gerencia qual listagem será feita no console usando o status da ordem de produção, ou seja, ou
   * ele lista pelas ordens "Em andamento" ou pelas "Concluídas".
   * 
   * @param sc Scanner para recebimento do dados enviados pelo usuário.
   */
  public void listAllOrdersProductByStatusConsole(Scanner sc) {
    String optionChoosed = null;
    boolean continueMenu = true;

    do {
      System.out.println("Listar as ordens:\n[1] - Em andamento\n[2] - Concluído\n[3] - Sair");
      optionChoosed = sc.next();

      switch (optionChoosed) {
        case "1":
          this.listAllOrdersProductIsNotDoneConsole();
          break;
        case "2":
          this.listAllOrdersProductIsDoneConsole();
          break;
        case "3":
          continueMenu = false;
          break;
        default:
          System.out.println("Escolha uma opção válida!");
          break;
      }
    } while (continueMenu);
  }

  /**
   * Lista todos as ordens de produção que estão em andamento.
   */
  public void listAllOrdersProductIsNotDoneConsole() {
    List<OrderProductDTO> ordersProducts = this.fm.getProductManufactureList().stream()
        .filter((p) -> !p.getStatus()).collect(Collectors.toList());

    System.out.println("-------------------------------------------------------------------------");
    System.out.printf("| %-18s | %-14s | %-14s | %-14s |\n", "Nome", "Quantidade", "Prazo",
        "Status");
    System.out.println("-------------------------------------------------------------------------");
    for (OrderProductDTO orderProduct : ordersProducts) {
      System.out.printf("| %-18s | %-14d | %-14s | %-14s |\n", orderProduct.getName(),
          orderProduct.getQuantity(), orderProduct.getDeadline(), "Em andamento");
    }
    System.out.println("-------------------------------------------------------------------------");
  }

  /**
   * Lista todos as ordens de produção que estão concluídas.
   */
  public void listAllOrdersProductIsDoneConsole() {
    List<OrderProductDTO> ordersProducts = this.fm.getProductManufactureList().stream()
        .filter((p) -> p.getStatus()).collect(Collectors.toList());

    System.out.println("-------------------------------------------------------------------------");
    System.out.printf("| %-18s | %-14s | %-14s | %-14s |\n", "Nome", "Quantidade", "Prazo",
        "Status");
    System.out.println("-------------------------------------------------------------------------");
    for (OrderProductDTO orderProduct : ordersProducts) {
      System.out.printf("| %-18s | %-14d | %-14s | %-14s |\n", orderProduct.getName(),
          orderProduct.getQuantity(), orderProduct.getDeadline(), "Finalizada");
    }
    System.out.println("-------------------------------------------------------------------------");
  }

  /**
   * Lista todos as ordens de produção indepedente de qualquer coisa.
   */
  public void listAllOrdersProductConsole() {
    ArrayList<OrderProductDTO> ordersProducts = this.fm.getProductManufactureList();

    System.out.println("--------------------------------------------------------");
    System.out.printf("| %-18s | %-14s | %-14s |\n", "Nome", "Quantidade", "Prazo");
    System.out.println("--------------------------------------------------------");
    for (OrderProductDTO orderProduct : ordersProducts) {
      System.out.printf("| %-18s | %-14d | %-14s |\n", orderProduct.getName(),
          orderProduct.getQuantity(), orderProduct.getDeadline());
    }
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Reúne os dados necessários para registrar uma nova ordem de produção.
   */
  public void registerOrderProductConsole(Scanner sc) {
    // Pra todas as entradas abaixo serão considerados dados inválidos:
    // 1 - Espaços em branco.
    // 2 - Não preencher nada.

    String nameProduct =
        this.helperGetNameConsole(sc, "Informe o nome do produto a ser produzido: ");

    String quantityProduct =
        this.helperGetQuantityConsole(sc, "Informe a quantidade a ser produzida: ");

    String deadlineProduct = this.helperGetDeadlineProductConsole(sc,
        "Informe a data de entrega desejada no formado dd/mm/yyyy: ");

    OrderProductDTO orderProduct =
        new OrderProductDTO(nameProduct, Long.valueOf(quantityProduct), deadlineProduct);

    fm.registerOrderProduct(orderProduct);
    System.out.println("Registro feito com sucesso!");
  }

  /**
   * Checa se existe recursos suficientes para produzir um determinado ordem de produto.
   * 
   * @param sc Scanner para recebimento do dados enviados pelo usuário.
   */
  public void checkIfThereIsEnoughResourcesConsole(Scanner sc) {
    String nameProduct = this.helperGetNameConsole(sc, "Informe o nome do produto: ");
    this.fm.checkIfThereIsEnoughResources(nameProduct);
  }

  /**
   * Reúne informações para atualizar o status de uma ordem de produção para: Em andamento ou
   * Concluído.
   * 
   * @param sc Scanner para recebimento do dados enviados pelo usuário.
   */
  public void updateStatusIsDoneConsole(Scanner sc) {
    String optionChoosed = null;
    boolean continueMenu = true;
    boolean newStatus = false;

    String nameProduct =
        this.helperGetNameConsole(sc, "Informe o nome do produto a ser atualizado o status: ");

    do {
      System.out.println("A ordem de produto em questão foi concluída?\n[1] - Sim\n[2] - Não");
      optionChoosed = sc.next();

      switch (optionChoosed) {
        case "1":
          newStatus = true;
          continueMenu = false;
          break;
        case "2":
          newStatus = false;
          continueMenu = false;
          break;
        default:
          System.out.println("Escolha uma opção válida!");
          break;
      }
    } while (continueMenu);

    this.fm.updateStatusIsDone(nameProduct, newStatus);
  }

  /**
   * Método auxiliar que trata de validar o nome do produto. Formato válido: String que contenha
   * somente letras ou letras e números. Nessa entrada passar somente números é um dado inválido.
   * 
   * @param sc Scanner usado para receber as informações enviadas pelo usuaŕio.
   * @param message Mensagem a ser exibida para o usuário.
   * @return O nome do produto.
   */
  public String helperGetNameConsole(Scanner sc, String message) {
    String nameProduct = null;
    boolean validateNameProduct = false;

    do {
      System.out.println(message);
      nameProduct = sc.nextLine();
      validateNameProduct = nameProduct.isBlank() || isOnlyNumbers(nameProduct)
          || !isOnlyLettersOrLettersAndNumbers(nameProduct);
    } while (validateNameProduct);

    return nameProduct;
  }

  /**
   * Método auxiliar que trata de validar a quantidade do produto. Formato válido: Somente números
   * inteiros positivos; ou seja, qualquer outro dado que não seja um número inteiro positivo(1, 2,
   * 3, ...) é inválido.
   * 
   * @param sc Scanner usado para receber as informações enviadas pelo usuaŕio.
   * @param message Mensagem a ser exibida para o usuário.
   * @return A quantidade do produto.
   */
  public String helperGetQuantityConsole(Scanner sc, String message) {
    String quantityProduct = null;
    boolean validateQuantityProduct = false;

    do {
      System.out.println(message);
      quantityProduct = sc.nextLine();
      validateQuantityProduct = quantityProduct.isBlank() || !isOnlyNumbers(quantityProduct)
          || Long.valueOf(quantityProduct) <= 0;
    } while (validateQuantityProduct);

    return quantityProduct;
  }

  /**
   * Método auxiliar que trata de validar o prazo para fabricação do produto. Formato válido:
   * Somente a data no formato dd/MM/yyyy. O ordenador do serviço deve fornecer ao menos um dia de
   * prazo, ou seja, datas já vencidas e a data do dia em que está sendo feito o pedido também serão
   * consideradas um dados inválidos.
   * 
   * @param sc Scanner usado para receber as informações enviadas pelo usuaŕio.
   * @param message Mensagem a ser exibida para o usuário.
   * @return O prazo do produto.
   */
  public String helperGetDeadlineProductConsole(Scanner sc, String message) {
    String deadlineProduct = null;
    boolean validateDeadlineProduct = false;

    do {
      System.out.println(message);
      deadlineProduct = sc.nextLine();
      validateDeadlineProduct = deadlineProduct.isBlank() || !checkDateFormat(deadlineProduct)
          || !isDateValid(deadlineProduct);
    } while (validateDeadlineProduct);

    return deadlineProduct;
  }

  /**
   * Checa a data de modo que ela não pode ser uma data já vencida ou atual, ou seja, precisa ser de
   * pelo menos um dia de prazo.
   * 
   * @param dateStr Data a ser validada.
   * @return true se a data for válida, do contrário, falso.
   */
  public static boolean isDateValid(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dateToCheck = LocalDate.parse(dateStr, formatter);
    LocalDate currentDate = LocalDate.now();

    if (dateToCheck.isBefore(currentDate)) {
      System.out.println("Dados inválidos: data vencida.");
      return false;
    } else if (dateToCheck.isEqual(currentDate)) {
      System.out.println("Dados inválidos: data de hoje, deve ser dado no mínimo 1 dia de prazo.");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checa se uma string contém somente números.
   * 
   * @param str String a ser verificada.
   * @return True se a string conter somente números, do contrário, false.
   */
  public static boolean isOnlyNumbers(String str) {
    return str.matches("[0-9]*");
  }

  /**
   * Checa se uma string contém somente letras ou letras com números.
   * 
   * @param str String a ser verificada.
   * @return True se a string conter somente letras, ou letras com números, do contrário, false.
   */
  public static boolean isOnlyLettersOrLettersAndNumbers(String str) {
    return str.matches("^[a-zA-Z0-9\\s]*$");
  }

  /**
   * Checa se uma string está no formato dd/MM/yyyy.
   * 
   * @param date String a ser verificada.
   * @return True se a string estiver no formato correto, do contrário, false.
   */
  public static boolean checkDateFormat(String date) {
    return date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
  }

  public FactoryManager getFm() {
    return fm;
  }
}
