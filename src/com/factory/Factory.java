package com.factory;

import java.util.Scanner;

public class Factory {

  public static void main(String[] args) {
    String nameProduct = null;
    Integer quantityProduct = null;
    String deadlineProduct = null;

    try (Scanner sc = new Scanner(System.in)) {
      do {
        System.out.println("Informe o nome do produto a ser produzido: ");
        nameProduct = sc.nextLine();
      } while (nameProduct.isBlank());

      do {
        System.out.println("Informe a quantidade a ser produzida: ");
        quantityProduct = sc.nextInt();
      } while (quantityProduct.intValue() <= 0);

      do {
        System.out.println("Informe a data de entrega desejada no formado dd/mm/yyyy: ");
        deadlineProduct = sc.next();
      } while (deadlineProduct.isBlank());

      System.out
          .println(String.format("%s %s %s", deadlineProduct, quantityProduct, deadlineProduct));

    } catch (Exception ex) {
      System.out.println("ERRO: " + ex.getMessage());
    }
  }

}
