package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.repository.CidadeRepository;
import br.com.sicavpn.repository.EstadoRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

	//@Inject
	private EstadoRepository estadoRepository;
	
	public EstadoConverter() {
		estadoRepository = CDIServiceLocator.getBean(EstadoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Estado retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = estadoRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null){
			return ((Estado) value).getId().toString();
		}
		return "";
	}

}
