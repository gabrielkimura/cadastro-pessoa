package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Pessoa;

import java.util.List;

@Stateless
public class PessoaDAO {

    @PersistenceContext(unitName = "personPU")
    private EntityManager em;

    public void save(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public void update(Pessoa pessoa) {
        em.merge(pessoa);
    }

    public void delete(Pessoa pessoa) {
        em.remove(em.merge(pessoa));
    }

    public Pessoa findById(int id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }
}
