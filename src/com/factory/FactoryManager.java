package com.factory;

import java.util.ArrayList;

public class FactoryManager {

  private ArrayList<OrderProductDTO> productManufactureList;

  public ArrayList<OrderProductDTO> getProductManufactureList() {
    return productManufactureList;
  }

  public void setProductManufactureList(OrderProductDTO productManufacture) {
    this.productManufactureList.add(productManufacture);
  }
}
