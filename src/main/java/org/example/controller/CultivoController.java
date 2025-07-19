package org.example.controller;

import org.example.dao.CultivoDAO;
import org.example.dao.PlantaDAO;
import org.example.model.Cultivo;
import org.example.model.Planta;

import java.util.List;

public class CultivoController {

    private CultivoDAO cultivoDAO = new CultivoDAO();

    public void setCultivo(Cultivo cultivo) {
        cultivoDAO.setCultivo(cultivo);
    }
    public void updateCultivo(Cultivo cultivo) {
        cultivoDAO.updateCultivo(cultivo);
    }

    public List<Cultivo> getCultivo(){
        return cultivoDAO.getCultivos();
    }
    public Cultivo getCultivoById(int id){
        return cultivoDAO.getCultivoById(id);
    }

    public Cultivo getCultivoByName(String name){
        return cultivoDAO.getCultivoByName(name);
    }

    public void deleteCultivo(int id){
        cultivoDAO.deleteCultivo(id);
    }

}
