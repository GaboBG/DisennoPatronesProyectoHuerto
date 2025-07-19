package org.example.controller;


import org.example.dao.PlantaDAO;
import org.example.model.Planta;

import java.util.List;


public class PlantaController {

    private PlantaDAO plantaDAO = new PlantaDAO();

    public void setPlanta(Planta planta) {
        plantaDAO.setPlanta(planta);
    }
    public void updatePlanta(Planta planta) {
        plantaDAO.updatePlanta(planta);
    }

    public List<Planta> getPlanta(){
        return plantaDAO.getPlantas();
    }
    public Planta getPlantaById(int id){
        return plantaDAO.getPlantaById(id);
    }

    public Planta getPlantaByName(String name){
        return plantaDAO.getPlantaByName(name);
    }

    public void deletePlanta(int id){
        plantaDAO.deletePlanta(id);
    }

}
