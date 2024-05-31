package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 100, nullable = false)
    private String logradouro;

    private int numero;

    @Column(length = 8, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    
    public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public String getEstado() {
		return estado;
	}
    
    public void setEstado(String estado) {
		this.estado = estado;
	}
    
    public String getCidade() {
		return cidade;
	}
    
    public void setCidade(String cidade) {
		this.cidade = cidade;
	}
    
    public String getLogradouro() {
		return logradouro;
	}
    
    public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
    
    public int getNumero() {
		return numero;
	}
    
    public void setNumero(int numero) {
		this.numero = numero;
	}
    
    public String getCep() {
		return cep;
	}
    
    public void setCep(String cep) {
		this.cep = cep;
	}
    
     public Pessoa getPessoa() {
		return pessoa;
	}
     
     public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
