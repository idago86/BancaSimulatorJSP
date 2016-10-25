/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AdminDao;
import entitiesDB.AdminDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import model.Admin;


/**
 *
 * @author Israel Dago
 */

public class AdminService {
    private Context ctx;
    private UserTransaction utx;
    private EntityManagerFactory emf;    
    private AdminDao adminDao;
    
    private AdminService() {
        try {
            ctx = new InitialContext();
            utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            emf = ((EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName")).getEntityManagerFactory();
            adminDao = new AdminDao(utx, emf);
        } catch (Exception ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static final class SingletonHolder{
        private static final AdminService SINGLETON = new AdminService();
    }
    
    public static AdminService getInstance(){return SingletonHolder.SINGLETON;}
    
    
    public boolean register(Admin admin){
        AdminDB rez = adminDao.findAdminByUsername(admin.getUsername());
        if(rez==null){
            try {
                rez = new AdminDB(0, admin.getUsername(), admin.getParola());
                adminDao.create(rez);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    
    public Admin login(Admin admin){
        AdminDB rez = adminDao.findAdminByUsername(admin.getUsername());
        if(rez!=null){
            if(admin.getParola().equals(rez.getParola())){
                Admin logged = new Admin();
                logged.setId(rez.getId());
                logged.setUsername(rez.getUsername());
                logged.setParola(rez.getParola());
                return logged;
            }
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
