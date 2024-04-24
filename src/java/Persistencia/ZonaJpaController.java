/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MIGUEL SAM
 */
public class ZonaJpaController implements Serializable {

    public ZonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ZonaJpaController() { //AGREGAMOS ESTE CONSTRUCTOR Y SUS LINEAS DE CODIGO
        emf=Persistence.createEntityManagerFactory("PartidoPU");
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Zona zona) throws PreexistingEntityException, Exception {
        if (zona.getBoletoList() == null) {
            zona.setBoletoList(new ArrayList<Boleto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Boleto> attachedBoletoList = new ArrayList<Boleto>();
            for (Boleto boletoListBoletoToAttach : zona.getBoletoList()) {
                boletoListBoletoToAttach = em.getReference(boletoListBoletoToAttach.getClass(), boletoListBoletoToAttach.getCodboleto());
                attachedBoletoList.add(boletoListBoletoToAttach);
            }
            zona.setBoletoList(attachedBoletoList);
            em.persist(zona);
            for (Boleto boletoListBoleto : zona.getBoletoList()) {
                Zona oldCodzonaOfBoletoListBoleto = boletoListBoleto.getCodzona();
                boletoListBoleto.setCodzona(zona);
                boletoListBoleto = em.merge(boletoListBoleto);
                if (oldCodzonaOfBoletoListBoleto != null) {
                    oldCodzonaOfBoletoListBoleto.getBoletoList().remove(boletoListBoleto);
                    oldCodzonaOfBoletoListBoleto = em.merge(oldCodzonaOfBoletoListBoleto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findZona(zona.getCodzona()) != null) {
                throw new PreexistingEntityException("Zona " + zona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Zona zona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zona persistentZona = em.find(Zona.class, zona.getCodzona());
            List<Boleto> boletoListOld = persistentZona.getBoletoList();
            List<Boleto> boletoListNew = zona.getBoletoList();
            List<String> illegalOrphanMessages = null;
            for (Boleto boletoListOldBoleto : boletoListOld) {
                if (!boletoListNew.contains(boletoListOldBoleto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Boleto " + boletoListOldBoleto + " since its codzona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Boleto> attachedBoletoListNew = new ArrayList<Boleto>();
            for (Boleto boletoListNewBoletoToAttach : boletoListNew) {
                boletoListNewBoletoToAttach = em.getReference(boletoListNewBoletoToAttach.getClass(), boletoListNewBoletoToAttach.getCodboleto());
                attachedBoletoListNew.add(boletoListNewBoletoToAttach);
            }
            boletoListNew = attachedBoletoListNew;
            zona.setBoletoList(boletoListNew);
            zona = em.merge(zona);
            for (Boleto boletoListNewBoleto : boletoListNew) {
                if (!boletoListOld.contains(boletoListNewBoleto)) {
                    Zona oldCodzonaOfBoletoListNewBoleto = boletoListNewBoleto.getCodzona();
                    boletoListNewBoleto.setCodzona(zona);
                    boletoListNewBoleto = em.merge(boletoListNewBoleto);
                    if (oldCodzonaOfBoletoListNewBoleto != null && !oldCodzonaOfBoletoListNewBoleto.equals(zona)) {
                        oldCodzonaOfBoletoListNewBoleto.getBoletoList().remove(boletoListNewBoleto);
                        oldCodzonaOfBoletoListNewBoleto = em.merge(oldCodzonaOfBoletoListNewBoleto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = zona.getCodzona();
                if (findZona(id) == null) {
                    throw new NonexistentEntityException("The zona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zona zona;
            try {
                zona = em.getReference(Zona.class, id);
                zona.getCodzona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Boleto> boletoListOrphanCheck = zona.getBoletoList();
            for (Boleto boletoListOrphanCheckBoleto : boletoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Zona (" + zona + ") cannot be destroyed since the Boleto " + boletoListOrphanCheckBoleto + " in its boletoList field has a non-nullable codzona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(zona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Zona> findZonaEntities() {
        return findZonaEntities(true, -1, -1);
    }

    public List<Zona> findZonaEntities(int maxResults, int firstResult) {
        return findZonaEntities(false, maxResults, firstResult);
    }

    private List<Zona> findZonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Zona.class));
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

    public Zona findZona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zona.class, id);
        } finally {
            em.close();
        }
    }

    public int getZonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Zona> rt = cq.from(Zona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
