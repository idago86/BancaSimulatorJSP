/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.RollbackFailureException;
import entitiesDB.AdminDB;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Israel Dago
 */
public class AdminDao implements Serializable {

    public AdminDao(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AdminDB adminDB) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(adminDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AdminDB adminDB) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            adminDB = em.merge(adminDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = adminDB.getId();
                if (findAdminDB(id) == null) {
                    throw new NonexistentEntityException("The adminDB with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            AdminDB adminDB;
            try {
                adminDB = em.getReference(AdminDB.class, id);
                adminDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adminDB with id " + id + " no longer exists.", enfe);
            }
            em.remove(adminDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AdminDB> findAdminDBEntities() {
        return findAdminDBEntities(true, -1, -1);
    }

    public List<AdminDB> findAdminDBEntities(int maxResults, int firstResult) {
        return findAdminDBEntities(false, maxResults, firstResult);
    }

    private List<AdminDB> findAdminDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AdminDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AdminDB findAdminDB(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AdminDB.class, id);
        } finally {
            em.close();
        }
    }
    
    public AdminDB findAdminByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("AdminDB.findByUsername", AdminDB.class);
            q.setParameter("username", username);
            List<AdminDB> result = q.getResultList();
            return result.isEmpty()?null:result.get(0);
        } finally {
            em.close();
        }
    }

    public int getAdminDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AdminDB> rt = cq.from(AdminDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
