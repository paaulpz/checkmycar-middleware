package com.paula.checkmc.service;
import java.util.List;

import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.service.impl.ModeloServiceImpl;

public class ModeloServiceTest {
    private ModeloService service = null;

    public ModeloServiceTest() {
        this.service = new ModeloServiceImpl();
    }
   
  
   
    public void testFindByMarca(Long marcaId) {
        List<Modelo> modelos = service.findByMarca(marcaId);
        System.out.println("Modelos encontrados para la marca: " + modelos.size());
    }

    public static void main(String[] args) {
        ModeloServiceTest test = new ModeloServiceTest();
        test.testFindByMarca(1L);
    }
}