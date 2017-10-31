package br.com.sicavpn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "om")
public class Om implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Estado estado;
	private Cidade cidade;
	private Integer numeroContas;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@NotBlank
	@Column(nullable = false, length = 40)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome == null ? null : nome.toUpperCase();
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cidade_id", nullable = false)
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@NotNull @Min(0) @Max(value = 15, message = "tem um valor muito alto")
	@Column(name="numero_contas", nullable = false, length = 5)
	public Integer getNumeroContas() {
		return numeroContas;
	}

	public void setNumeroContas(Integer numeroContas) {
		this.numeroContas = numeroContas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Om other = (Om) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
