package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.hibernate.mapping.Value;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Om.class)
public class OmConverter implements Converter {

	// @Inject
	private OmRepository omRepository;

	public OmConverter() {
		omRepository = CDIServiceLocator.getBean(OmRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Om retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = omRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Om om = (Om) value;
			return om.getId() == null ? null : om.getId().toString();
		}

		return "";
	}

}
