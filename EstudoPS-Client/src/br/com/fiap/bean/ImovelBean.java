package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.service.ImovelService;
import br.com.fiap.to.Imovel;

@ManagedBean
public class ImovelBean {
	
	private Imovel imovel;
	private ImovelService service;
	
	@PostConstruct
	private void init() {
		imovel = new Imovel();
		service = new ImovelService();
	}
	
	public void cadastrar() {
		FacesMessage msg;
		try {
			service.cadastrar(imovel);
			msg = new FacesMessage("Sucesso!");
		}catch (Exception erro) {
			erro.printStackTrace();
			msg = new FacesMessage("Erro!");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}	
}
