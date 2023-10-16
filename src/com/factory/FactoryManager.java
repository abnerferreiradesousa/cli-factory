package com.factory;

import java.util.ArrayList;

/**
 * Realiza as operações da fábrica, ou seja, cria, lista, atualiza, etc.
 */
public class FactoryManager {

  /**
   * Lista de todas as ordens de produção.
   */
  private ArrayList<OrderProductDTO> productManufactureList;
  private FactoryMockDatabase dbMock;

  /**
   * Constructor que inicializa a lista de ordens de produção.
   */
  public FactoryManager() {
    this.productManufactureList = new ArrayList<>();
    this.dbMock = new FactoryMockDatabase();

    // Esse código lida com carregamento dos dados do arquivo data.json, caso não exista o arquivo
    // ele o cria com um Array vazio.
    if (this.dbMock.getJsonFile().exists()) {
      this.productManufactureList = this.dbMock.readData();
    } else {
      this.dbMock.persistData(new ArrayList<OrderProductDTO>());
    }
  }

  /**
   * Getter para pegar a lista de ordens produção.
   * 
   * @return Lista de ordens produção.
   */
  public ArrayList<OrderProductDTO> getProductManufactureList() {
    return productManufactureList;
  }

  /**
   * Gerencia e chama os métodos necessários para checar se existe recursos suficientes para
   * executar uma determinada ordem de produção.
   * 
   * @param nameProduct Nome do produto da ordem de produção a ser verificada a existência de
   *        recursos para produção.
   */
  public void checkIfThereIsEnoughResources(String nameProduct) {
    OrderProductDTO orderProduct = this.productManufactureList.stream()
        .filter((p) -> p.getName().equals(nameProduct)).findAny().orElse(null);

    if (orderProduct == null) {
      System.out.println("Ordem não encontrada!");
      return;
    }

    boolean resultAboutResources = new FactoryResource().isThereEnoughResources();
    if (resultAboutResources) {
      System.out.printf("Há recursos suficientes para a produção de %s unidade(s) %s!\n",
          orderProduct.getQuantity(), orderProduct.getName());
      return;
    }

    System.out.printf("Não há recursos suficientes para a produção de %s unidade(s) %s!\n",
        orderProduct.getQuantity(), orderProduct.getName());
  }

  /**
   * Registra uma nova ordem de produção na lista de ordens de produção. E depois os dados
   * atualizados são persistidos no arquivo data.json
   * 
   * @param productManufacture Nova ordem de produção.
   */
  public void registerOrderProduct(OrderProductDTO productManufacture) {
    this.productManufactureList.add(productManufacture);
    this.dbMock.persistData(this.getProductManufactureList());
  }

  /**
   * Busca a ordem de produção que será atualizada para "Concluída" ou para "Em andamento". E depois
   * os dados atualizados são persistidos no arquivo data.json
   * 
   * @param nameProduct Nome do produto a ser atualizado.
   * @param isFinished Booleano true para "Concluída" e false para "Em andamento".
   */
  public void updateStatusIsDone(String nameProduct, boolean newStatus) {
    OrderProductDTO orderProduct = this.productManufactureList.stream()
        .filter((p) -> p.getName().equals(nameProduct)).findAny().orElse(null);

    if (orderProduct == null) {
      System.out.println("Ordem não encontrada!");
      return;
    }

    orderProduct.setStatus(newStatus);
    this.dbMock.persistData(this.getProductManufactureList());
  }
}
