package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Endereco;

@Stateless
public class EnderecoDAO {

    @PersistenceContext(unitName = "personPU")
    private EntityManager em;

    public void save(Endereco endereco) {
        em.persist(endereco);
    }

    public void update(Endereco endereco) {
        em.merge(endereco);
    }

    public void delete(Endereco endereco) {
        em.remove(em.merge(endereco));
    }

    public Endereco findById(int id) {
        return em.find(Endereco.class, id);
    }

    public List<Endereco> findAll() {
        return em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
    }
}