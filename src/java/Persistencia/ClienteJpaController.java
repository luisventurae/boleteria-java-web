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

/**
 *
 * @author MIGUEL SAM
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getBoletoList() == null) {
            cliente.setBoletoList(new ArrayList<Boleto>());
        }
        if (cliente.getReciboList() == null) {
            cliente.setReciboList(new ArrayList<Recibo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Boleto> attachedBoletoList = new ArrayList<Boleto>();
            for (Boleto boletoListBoletoToAttach : cliente.getBoletoList()) {
                boletoListBoletoToAttach = em.getReference(boletoListBoletoToAttach.getClass(), boletoListBoletoToAttach.getCodboleto());
                attachedBoletoList.add(boletoListBoletoToAttach);
            }
            cliente.setBoletoList(attachedBoletoList);
            List<Recibo> attachedReciboList = new ArrayList<Recibo>();
            for (Recibo reciboListReciboToAttach : cliente.getReciboList()) {
                reciboListReciboToAttach = em.getReference(reciboListReciboToAttach.getClass(), reciboListReciboToAttach.getCodrecibo());
                attachedReciboList.add(reciboListReciboToAttach);
            }
            cliente.setReciboList(attachedReciboList);
            em.persist(cliente);
            for (Boleto boletoListBoleto : cliente.getBoletoList()) {
                Cliente oldDniOfBoletoListBoleto = boletoListBoleto.getDni();
                boletoListBoleto.setDni(cliente);
                boletoListBoleto = em.merge(boletoListBoleto);
                if (oldDniOfBoletoListBoleto != null) {
                    oldDniOfBoletoListBoleto.getBoletoList().remove(boletoListBoleto);
                    oldDniOfBoletoListBoleto = em.merge(oldDniOfBoletoListBoleto);
                }
            }
            for (Recibo reciboListRecibo : cliente.getReciboList()) {
                Cliente oldDniOfReciboListRecibo = reciboListRecibo.getDni();
                reciboListRecibo.setDni(cliente);
                reciboListRecibo = em.merge(reciboListRecibo);
                if (oldDniOfReciboListRecibo != null) {
                    oldDniOfReciboListRecibo.getReciboList().remove(reciboListRecibo);
                    oldDniOfReciboListRecibo = em.merge(oldDniOfReciboListRecibo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getDni()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getDni());
            List<Boleto> boletoListOld = persistentCliente.getBoletoList();
            List<Boleto> boletoListNew = cliente.getBoletoList();
            List<Recibo> reciboListOld = persistentCliente.getReciboList();
            List<Recibo> reciboListNew = cliente.getReciboList();
            List<String> illegalOrphanMessages = null;
            for (Boleto boletoListOldBoleto : boletoListOld) {
                if (!boletoListNew.contains(boletoListOldBoleto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Boleto " + boletoListOldBoleto + " since its dni field is not nullable.");
                }
            }
            for (Recibo reciboListOldRecibo : reciboListOld) {
                if (!reciboListNew.contains(reciboListOldRecibo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Recibo " + reciboListOldRecibo + " since its dni field is not nullable.");
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
            cliente.setBoletoList(boletoListNew);
            List<Recibo> attachedReciboListNew = new ArrayList<Recibo>();
            for (Recibo reciboListNewReciboToAttach : reciboListNew) {
                reciboListNewReciboToAttach = em.getReference(reciboListNewReciboToAttach.getClass(), reciboListNewReciboToAttach.getCodrecibo());
                attachedReciboListNew.add(reciboListNewReciboToAttach);
            }
            reciboListNew = attachedReciboListNew;
            cliente.setReciboList(reciboListNew);
            cliente = em.merge(cliente);
            for (Boleto boletoListNewBoleto : boletoListNew) {
                if (!boletoListOld.contains(boletoListNewBoleto)) {
                    Cliente oldDniOfBoletoListNewBoleto = boletoListNewBoleto.getDni();
                    boletoListNewBoleto.setDni(cliente);
                    boletoListNewBoleto = em.merge(boletoListNewBoleto);
                    if (oldDniOfBoletoListNewBoleto != null && !oldDniOfBoletoListNewBoleto.equals(cliente)) {
                        oldDniOfBoletoListNewBoleto.getBoletoList().remove(boletoListNewBoleto);
                        oldDniOfBoletoListNewBoleto = em.merge(oldDniOfBoletoListNewBoleto);
                    }
                }
            }
            for (Recibo reciboListNewRecibo : reciboListNew) {
                if (!reciboListOld.contains(reciboListNewRecibo)) {
                    Cliente oldDniOfReciboListNewRecibo = reciboListNewRecibo.getDni();
                    reciboListNewRecibo.setDni(cliente);
                    reciboListNewRecibo = em.merge(reciboListNewRecibo);
                    if (oldDniOfReciboListNewRecibo != null && !oldDniOfReciboListNewRecibo.equals(cliente)) {
                        oldDniOfReciboListNewRecibo.getReciboList().remove(reciboListNewRecibo);
                        oldDniOfReciboListNewRecibo = em.merge(oldDniOfReciboListNewRecibo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getDni();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Boleto> boletoListOrphanCheck = cliente.getBoletoList();
            for (Boleto boletoListOrphanCheckBoleto : boletoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Boleto " + boletoListOrphanCheckBoleto + " in its boletoList field has a non-nullable dni field.");
            }
            List<Recibo> reciboListOrphanCheck = cliente.getReciboList();
            for (Recibo reciboListOrphanCheckRecibo : reciboListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Recibo " + reciboListOrphanCheckRecibo + " in its reciboList field has a non-nullable dni field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
