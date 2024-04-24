/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MIGUEL SAM
 */
public class ReciboJpaController implements Serializable {

    public ReciboJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recibo recibo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente dni = recibo.getDni();
            if (dni != null) {
                dni = em.getReference(dni.getClass(), dni.getDni());
                recibo.setDni(dni);
            }
            em.persist(recibo);
            if (dni != null) {
                dni.getReciboList().add(recibo);
                dni = em.merge(dni);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRecibo(recibo.getCodrecibo()) != null) {
                throw new PreexistingEntityException("Recibo " + recibo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recibo recibo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recibo persistentRecibo = em.find(Recibo.class, recibo.getCodrecibo());
            Cliente dniOld = persistentRecibo.getDni();
            Cliente dniNew = recibo.getDni();
            if (dniNew != null) {
                dniNew = em.getReference(dniNew.getClass(), dniNew.getDni());
                recibo.setDni(dniNew);
            }
            recibo = em.merge(recibo);
            if (dniOld != null && !dniOld.equals(dniNew)) {
                dniOld.getReciboList().remove(recibo);
                dniOld = em.merge(dniOld);
            }
            if (dniNew != null && !dniNew.equals(dniOld)) {
                dniNew.getReciboList().add(recibo);
                dniNew = em.merge(dniNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = recibo.getCodrecibo();
                if (findRecibo(id) == null) {
                    throw new NonexistentEntityException("The recibo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recibo recibo;
            try {
                recibo = em.getReference(Recibo.class, id);
                recibo.getCodrecibo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recibo with id " + id + " no longer exists.", enfe);
            }
            Cliente dni = recibo.getDni();
            if (dni != null) {
                dni.getReciboList().remove(recibo);
                dni = em.merge(dni);
            }
            em.remove(recibo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recibo> findReciboEntities() {
        return findReciboEntities(true, -1, -1);
    }

    public List<Recibo> findReciboEntities(int maxResults, int firstResult) {
        return findReciboEntities(false, maxResults, firstResult);
    }

    private List<Recibo> findReciboEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recibo.class));
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

    public Recibo findRecibo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recibo.class, id);
        } finally {
            em.close();
        }
    }

    public int getReciboCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recibo> rt = cq.from(Recibo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
