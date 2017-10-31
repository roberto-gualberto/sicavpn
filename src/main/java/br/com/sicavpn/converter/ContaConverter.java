package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter {

	//@Inject
	private ContaRepository contaRepository;
	
	public ContaConverter() {
		contaRepository = CDIServiceLocator.getBean(ContaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Conta retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = contaRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Conta conta = (Conta) value;
			return conta.getId() == null ? null : conta.getId().toString();
		}
		
		return "";
	}

}
