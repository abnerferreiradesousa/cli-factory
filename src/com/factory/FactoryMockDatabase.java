package com.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Essa classe lida com a leitura e escrita de dados no arquivo data.json.
 */
public class FactoryMockDatabase {

  private File jsonFile;
  private ObjectMapper objectMapper;

  /**
   * Contructor inicializando os atributos que serão usados nos métodos abaixo.
   */
  public FactoryMockDatabase() {
    this.jsonFile = new File("data.json");
    this.objectMapper = new ObjectMapper();
  }

  /**
   * Persiste os dados vindos no parâmetro content no arquivo data.json.
   * 
   * @param content Conteúdo que será persistido.
   */
  public void persistData(List<OrderProductDTO> content) {
    try {
      objectMapper.writeValue(jsonFile, content);
      System.out.println("Persistido no banco de dados mockado.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Ler o conteúdo presente no arquivo data.json.
   * 
   * @return Retorna o conteúdo lido no arquivo data.json.
   */
  public ArrayList<OrderProductDTO> readData() {
    try {
      ArrayList<OrderProductDTO> ordersProduct = objectMapper.readValue(jsonFile, objectMapper
          .getTypeFactory().constructCollectionType(ArrayList.class, OrderProductDTO.class));
      return ordersProduct;
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<OrderProductDTO>();
    }
  }

  /**
   * Getter do jsonFile.
   * 
   * @return o jsonFile.
   */
  public File getJsonFile() {
    return jsonFile;
  }
}
