package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Diex;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Diex.class)
public class DiexConverter implements Converter {

	//@Inject
	private DiexRepository diexRepository;
	
	public DiexConverter() {
		diexRepository = CDIServiceLocator.getBean(DiexRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Diex retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = diexRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			return ((Diex) value).getId().toString();
		}
		return "";
	}

}
