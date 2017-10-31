package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Grupo;
import br.com.sicavpn.repository.GrupoRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	//@Inject
	private GrupoRepository grupoRepository;
	
	public GrupoConverter() {
		grupoRepository = CDIServiceLocator.getBean(GrupoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Grupo retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = grupoRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			return ((Grupo) value).getId().toString();
		}
		return "";
	}

}
