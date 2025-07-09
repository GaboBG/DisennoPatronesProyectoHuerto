package org.example.controller;

import org.example.dao.AdminDAO;
import org.example.dao.AsesorDAO;
import org.example.model.Admin;
import org.example.model.Asesor;

import java.util.List;

public class AdminController {
    private AdminDAO adminDAO = new AdminDAO();

    public void setAdmin(Admin admin) {
        adminDAO.setAdmin(admin);
    }
    public void updateAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    public List<Admin> getAdmins(){
        return adminDAO.getAdmins();
    }
    public Admin getAdminById(int id){
        return adminDAO.getAdminById(id);
    }
    public Admin getAdminByCorreo(String correo){
        return adminDAO.getAdminByCorreo(correo);
    }

    public void deleteAdmin(int id){
        adminDAO.deleteAdmin(id);
    }
}
