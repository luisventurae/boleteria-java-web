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
public class BoletoJpaController implements Serializable {

    public BoletoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Boleto boleto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente dni = boleto.getDni();
            if (dni != null) {
                dni = em.getReference(dni.getClass(), dni.getDni());
                boleto.setDni(dni);
            }
            Zona codzona = boleto.getCodzona();
            if (codzona != null) {
                codzona = em.getReference(codzona.getClass(), codzona.getCodzona());
                boleto.setCodzona(codzona);
            }
            em.persist(boleto);
            if (dni != null) {
                dni.getBoletoList().add(boleto);
                dni = em.merge(dni);
            }
            if (codzona != null) {
                codzona.getBoletoList().add(boleto);
                codzona = em.merge(codzona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBoleto(boleto.getCodboleto()) != null) {
                throw new PreexistingEntityException("Boleto " + boleto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Boleto boleto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Boleto persistentBoleto = em.find(Boleto.class, boleto.getCodboleto());
            Cliente dniOld = persistentBoleto.getDni();
            Cliente dniNew = boleto.getDni();
            Zona codzonaOld = persistentBoleto.getCodzona();
            Zona codzonaNew = boleto.getCodzona();
            if (dniNew != null) {
                dniNew = em.getReference(dniNew.getClass(), dniNew.getDni());
                boleto.setDni(dniNew);
            }
            if (codzonaNew != null) {
                codzonaNew = em.getReference(codzonaNew.getClass(), codzonaNew.getCodzona());
                boleto.setCodzona(codzonaNew);
            }
            boleto = em.merge(boleto);
            if (dniOld != null && !dniOld.equals(dniNew)) {
                dniOld.getBoletoList().remove(boleto);
                dniOld = em.merge(dniOld);
            }
            if (dniNew != null && !dniNew.equals(dniOld)) {
                dniNew.getBoletoList().add(boleto);
                dniNew = em.merge(dniNew);
            }
            if (codzonaOld != null && !codzonaOld.equals(codzonaNew)) {
                codzonaOld.getBoletoList().remove(boleto);
                codzonaOld = em.merge(codzonaOld);
            }
            if (codzonaNew != null && !codzonaNew.equals(codzonaOld)) {
                codzonaNew.getBoletoList().add(boleto);
                codzonaNew = em.merge(codzonaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = boleto.getCodboleto();
                if (findBoleto(id) == null) {
                    throw new NonexistentEntityException("The boleto with id " + id + " no longer exists.");
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
            Boleto boleto;
            try {
                boleto = em.getReference(Boleto.class, id);
                boleto.getCodboleto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The boleto with id " + id + " no longer exists.", enfe);
            }
            Cliente dni = boleto.getDni();
            if (dni != null) {
                dni.getBoletoList().remove(boleto);
                dni = em.merge(dni);
            }
            Zona codzona = boleto.getCodzona();
            if (codzona != null) {
                codzona.getBoletoList().remove(boleto);
                codzona = em.merge(codzona);
            }
            em.remove(boleto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Boleto> findBoletoEntities() {
        return findBoletoEntities(true, -1, -1);
    }

    public List<Boleto> findBoletoEntities(int maxResults, int firstResult) {
        return findBoletoEntities(false, maxResults, firstResult);
    }

    private List<Boleto> findBoletoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Boleto.class));
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

    public Boleto findBoleto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Boleto.class, id);
        } finally {
            em.close();
        }
    }

    public int getBoletoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Boleto> rt = cq.from(Boleto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
