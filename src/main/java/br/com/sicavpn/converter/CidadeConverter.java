package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.repository.CidadeRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	//@Inject
	private CidadeRepository cidadeRepository;
	
	public CidadeConverter() {
		cidadeRepository = CDIServiceLocator.getBean(CidadeRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Cidade retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = cidadeRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			return ((Cidade) value).getId().toString();
		}
		return "";
	}

}
