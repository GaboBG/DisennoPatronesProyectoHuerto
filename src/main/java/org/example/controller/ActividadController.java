package org.example.controller;

import org.example.dao.ActividadDAO;
import org.example.model.Actividad;

import java.util.List;

public class ActividadController {

    private ActividadDAO actividadDAO = new ActividadDAO();

    public void setActividad(Actividad actividad) {
        actividadDAO.setActividad(actividad);
    }
    public void updateActividad(Actividad actividad) {
        actividadDAO.updateActividad(actividad);
    }

    public List<Actividad> getActividad(){
        return actividadDAO.getActividades();
    }
    public Actividad getActividadById(int id){
        return actividadDAO.getActividadById(id);
    }

    public Actividad getActividadByName(String name){
        return actividadDAO.getActividadByName(name);
    }

    public void deleteActividad(int id){
        actividadDAO.deleteActividad(id);
    }

}
