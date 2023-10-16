package com.factory;

/**
 * Classe modelo de uma ordem de produção contendo nome do produto, quantidade a ser produzida, e
 * prazo para finalização dessa produção e status para indicar se a atual ordem de proução foi
 * concluída ou não.
 */
public class OrderProductDTO {

  private String name;
  private long quantity;
  private String deadline;
  private boolean status;

  public OrderProductDTO() {}

  public OrderProductDTO(String name, long quantity, String deadline) {
    this.name = name;
    this.quantity = quantity;
    this.deadline = deadline;
    this.status = false;
  }

  /**
   * 
   * Constructor recebendo valores passados via parâmetro para inicialização do atributos. Onde
   * também a variável status é inicializada com false.
   * 
   * @param name
   * @param quantity
   * @param deadline
   */
  public OrderProductDTO(String name, long quantity, String deadline, boolean status) {
    this.name = name;
    this.quantity = quantity;
    this.deadline = deadline;
    this.status = status;
  }

  /*
   * Abaixo são todos getters e setters do atributos da classe.
   */

  public boolean getStatus() {
    return status;
  }

  public String getName() {
    return name;
  }

  public long getQuantity() {
    return quantity;
  }

  public String getDeadline() {
    return deadline;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return String.format(
        "\n{ \"name\": \"%s\", \"quantity\": \"%s\", \"deadline\": \"%s\", \"status\": \"%s\" }\n",
        getName(), getQuantity(), getDeadline(), getStatus());
  }
}
