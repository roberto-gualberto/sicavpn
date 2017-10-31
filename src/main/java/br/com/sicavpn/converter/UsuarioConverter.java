package br.com.sicavpn.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.CidadeRepository;
import br.com.sicavpn.repository.EstadoRepository;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	//@Inject
	private UsuarioRepository usuarioRepository;
	
	public UsuarioConverter() {
		usuarioRepository = CDIServiceLocator.getBean(UsuarioRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Usuario retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = usuarioRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		
		return "";
	}

}
