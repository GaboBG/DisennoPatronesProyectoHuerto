package org.example.controller;

import org.example.dao.ActividadDAO;
import org.example.dao.RecomendacionesDAO;
import org.example.model.Actividad;
import org.example.model.Recomendaciones;

import java.util.List;

public class RecomendacionesController {

    private RecomendacionesDAO recomendacionesDAO = new RecomendacionesDAO();

    public void setRecomendacion(Recomendaciones recomendaciones) {
        recomendacionesDAO.setRecomendacion(recomendaciones);
    }
    public void updateRecomendacion(Recomendaciones recomendaciones) {
        recomendacionesDAO.updateRecomendacion(recomendaciones);
    }

    public List<Recomendaciones> getRecomendaciones() {
        return recomendacionesDAO.getRecomendaciones();
    }
    public Recomendaciones getRecomendacionById(int id){
        return recomendacionesDAO.getRecomendacionById(id);
    }

    public void deleteRecomendacion(int id){
        recomendacionesDAO.deleteRecomendacion(id);
    }

}
