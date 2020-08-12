package model.entities;

import java.io.Serializable;
import java.util.Date;

public class AnotacoesC implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String usuario_criar;
	private String email;
	private String telefone;
	private Date dataAtual_criar;
	private String problema_criar;
	private String situacao_criar;
	private String desSituacao_criar;
	private String numeroChamdo_criar;

	private EscolasWell escolasWell;
	
	
	public AnotacoesC(){
	}
	


	public AnotacoesC(Integer id, String usuario_criar, String email, String telefone, Date dataAtual_criar, String problema_criar,
			String situacao_criar, String desSituacao_criar, String numeroChamdo_criar,EscolasWell escolasWell) {
		super();
		this.id = id;
		this.usuario_criar = usuario_criar;
		this.email = email;
		this.telefone = telefone;
		this.dataAtual_criar = dataAtual_criar;
		this.problema_criar = problema_criar;
		this.situacao_criar = situacao_criar;
		this.desSituacao_criar = desSituacao_criar;
		this.numeroChamdo_criar = numeroChamdo_criar;
		this.escolasWell = escolasWell;
	}

	


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUsuario_criar() {
		return usuario_criar;
	}



	public void setUsuario_criar(String usuario_criar) {
		this.usuario_criar = usuario_criar;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public Date getDataAtual_criar() {
		return dataAtual_criar;
	}



	public void setDataAtual_criar(Date dataAtual_criar) {
		this.dataAtual_criar = dataAtual_criar;
	}



	public String getProblema_criar() {
		return problema_criar;
	}



	public void setProblema_criar(String problema_criar) {
		this.problema_criar = problema_criar;
	}



	public String getSituacao_criar() {
		return situacao_criar;
	}



	public void setSituacao_criar(String situacao_criar) {
		this.situacao_criar = situacao_criar;
	}



	public String getDesSituacao_criar() {
		return desSituacao_criar;
	}



	public void setDesSituacao_criar(String desSituacao_criar) {
		this.desSituacao_criar = desSituacao_criar;
	}



	public String getNumeroChamdo_criar() {
		return numeroChamdo_criar;
	}



	public void setNumeroChamdo_criar(String numeroChamdo_criar) {
		this.numeroChamdo_criar = numeroChamdo_criar;
	}



	public EscolasWell getEscolasWell() {
		return escolasWell;
	}



	public void setEscolasWell(EscolasWell escolasWell) {
		this.escolasWell = escolasWell;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAtual_criar == null) ? 0 : dataAtual_criar.hashCode());
		result = prime * result + ((desSituacao_criar == null) ? 0 : desSituacao_criar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((escolasWell == null) ? 0 : escolasWell.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroChamdo_criar == null) ? 0 : numeroChamdo_criar.hashCode());
		result = prime * result + ((problema_criar == null) ? 0 : problema_criar.hashCode());
		result = prime * result + ((situacao_criar == null) ? 0 : situacao_criar.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((usuario_criar == null) ? 0 : usuario_criar.hashCode());
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
		AnotacoesC other = (AnotacoesC) obj;
		
		if (dataAtual_criar == null) {
			if (other.dataAtual_criar != null)
				return false;
		} else if (!dataAtual_criar.equals(other.dataAtual_criar))
			return false;
		if (desSituacao_criar == null) {
			if (other.desSituacao_criar != null)
				return false;
		} else if (!desSituacao_criar.equals(other.desSituacao_criar))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (escolasWell == null) {
			if (other.escolasWell != null)
				return false;
		} else if (!escolasWell.equals(other.escolasWell))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroChamdo_criar == null) {
			if (other.numeroChamdo_criar != null)
				return false;
		} else if (!numeroChamdo_criar.equals(other.numeroChamdo_criar))
			return false;
		if (problema_criar == null) {
			if (other.problema_criar != null)
				return false;
		} else if (!problema_criar.equals(other.problema_criar))
			return false;
		if (situacao_criar == null) {
			if (other.situacao_criar != null)
				return false;
		} else if (!situacao_criar.equals(other.situacao_criar))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (usuario_criar == null) {
			if (other.usuario_criar != null)
				return false;
		} else if (!usuario_criar.equals(other.usuario_criar))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AnotacoesC [id=" + id + ", usuario_criar=" + usuario_criar + ", email=" + email + ", telefone="
				+ telefone + ", dataAtual_criar=" + dataAtual_criar + ", problema_criar=" + problema_criar
				+ ", situacao_criar=" + situacao_criar + ", desSituacao_criar=" + desSituacao_criar
				+ ", numeroChamdo_criar=" + numeroChamdo_criar +  ", escolasWell="
				+ escolasWell + "]";
	}


}

