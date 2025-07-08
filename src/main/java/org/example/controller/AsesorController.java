package org.example.controller;

import org.example.dao.AsesorDAO;
import org.example.model.Asesor;


import java.util.List;

public class AsesorController {
    private AsesorDAO asesorDAO = new AsesorDAO();

    public void setAsesor(Asesor asesor) {
        asesorDAO.setAsesor(asesor);
    }
    public void updateAsesor(Asesor asesor) {
        asesorDAO.updateAsesor(asesor);
    }

    public List<Asesor> getAsesores(){
        return asesorDAO.getAsesores();
    }
    public Asesor getAsesorById(int id){
        return asesorDAO.getAsesorById(id);
    }
    public Asesor getAsesorByCorreo(String correo){
        return asesorDAO.getAsesorByCorreo(correo);
    }

    public void deleteAsesor(int id){
        asesorDAO.deleteAsesor(id);
    }
}
