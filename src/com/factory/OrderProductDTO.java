package com.factory;

public class OrderProductDTO {

  private String name;
  private Integer quantity;
  private String deadline;
  private boolean isDone = false;

  public OrderProductDTO(String name, Integer quantity, String deadline) {
    this.name = name;
    this.quantity = quantity;
    this.deadline = deadline;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone() {
    this.isDone = !isDone;
  }

  public String getName() {
    return name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public String getDeadline() {
    return deadline;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }
}
