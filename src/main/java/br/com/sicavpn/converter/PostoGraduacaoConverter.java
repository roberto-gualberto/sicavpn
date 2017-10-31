package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.PostoGraduacao;
import br.com.sicavpn.repository.PostoGraduacaoRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = PostoGraduacao.class)
public class PostoGraduacaoConverter implements Converter {

	// @Inject
	private PostoGraduacaoRepository postoGraduacaoRepository;

	public PostoGraduacaoConverter() {
		postoGraduacaoRepository = CDIServiceLocator.getBean(PostoGraduacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		PostoGraduacao retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = postoGraduacaoRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			return ((PostoGraduacao) value).getId().toString();
		}
		return "";
	}

}
