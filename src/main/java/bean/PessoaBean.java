package bean;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import model.Endereco;
import model.Pessoa;

@ManagedBean(name = "pessoaBean")
@SessionScoped
public class PessoaBean {

	private static final Logger LOGGER = Logger.getLogger(PessoaBean.class.getName());

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("personPU");
	private EntityManager em = emf.createEntityManager();

	private Pessoa pessoa = new Pessoa();
	private Endereco endereco = new Endereco();

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void save() {
		try {
			em.getTransaction().begin();

			if (endereco.getEstado() != null && !endereco.getEstado().isEmpty() && endereco.getCidade() != null
					&& !endereco.getCidade().isEmpty() && endereco.getLogradouro() != null
					&& !endereco.getLogradouro().isEmpty() && endereco.getCep() != null
					&& !endereco.getCep().isEmpty()) {

				if (pessoa.getEnderecos() == null) {
					pessoa.setEnderecos(new ArrayList<>());
				}
				endereco.setPessoa(pessoa);
				pessoa.getEnderecos().add(endereco);
			}

			if (pessoa.getId() != 0 && Objects.nonNull(pessoa.getId())) {
				pessoa = em.merge(pessoa);
			} else {
				em.persist(pessoa);
				em.flush();
			}

			for (Endereco endereco : pessoa.getEnderecos()) {
				endereco.setPessoa(pessoa);
				if (endereco.getId() == 0) {
					em.persist(endereco);
				} else {
					em.merge(endereco);
				}
			}

			em.getTransaction().commit();
			pessoa = new Pessoa();
			endereco = new Endereco();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			LOGGER.log(Level.SEVERE, "Error saving pessoa", e);
			e.printStackTrace();
		}
	}

	public void edit(Pessoa pessoa) {
		this.pessoa = pessoa;
		this.endereco = new Endereco();
	}

	public void delete(Pessoa pessoa) {
		try {
			em.getTransaction().begin();
			em.remove(em.contains(pessoa) ? pessoa : em.merge(pessoa));
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void newPessoa() {
		pessoa = new Pessoa();
		endereco = new Endereco();
	}

	public void addEndereco() {
		if (pessoa.getEnderecos() == null) {
			pessoa.setEnderecos(new ArrayList<>());
		}
		endereco.setPessoa(pessoa);
		pessoa.getEnderecos().add(endereco);
		endereco = new Endereco();
	}

	public void removeEndereco(Endereco endereco) {
		pessoa.getEnderecos().remove(endereco);
	}

	public void consultar() {
	    try {
	        pessoa = em.createQuery("SELECT p FROM Pessoa p WHERE p.nome LIKE :nome", Pessoa.class)
	                          .setParameter("nome", "%" + pessoa.getNome() + "%")
	                          .getSingleResult();
	        if (pessoa != null) {
	            LOGGER.info("Pessoa encontrada com sucesso: " + pessoa);
	            LOGGER.info("ID da pessoa encontrada: " + pessoa.getId());
	        } else {
	            LOGGER.info("Nenhuma pessoa encontrada com o nome: " + pessoa.getNome());
	        }
	    } catch (NoResultException e) {
	        pessoa = new Pessoa();
	        LOGGER.info("Nenhuma pessoa encontrada com o nome: " + pessoa.getNome());
	    } catch (Exception e) {
	        LOGGER.log(Level.SEVERE, "Erro ao consultar pessoa", e);
	        e.printStackTrace();
	    }
	}

	public void deleteAll() {
		try {
			em.getTransaction().begin();

			if (Objects.nonNull(pessoa.getId()) && pessoa.getId() != 0) {
				pessoa = em.merge(pessoa);
				em.remove(pessoa);
				em.getTransaction().commit();
				pessoa = new Pessoa();
				endereco = new Endereco();
			} else {
				LOGGER.warning("A pessoa não possui uma ID válida, portanto não pode ser excluída.");
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				LOGGER.log(Level.SEVERE, "Error deleting pessoa", e);
				e.printStackTrace();
			}
		}

	}
}
