package com.algaworks.bandayou.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.bandayou.model.Usuario;
import com.algaworks.bandayou.repository.Usuarios;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private Usuarios usuarios;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaUsuarios");
		mv.addObject("usuarios",usuarios.findAll());
	return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmUsuarios");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Usuario usuario, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("FrmUsuarios");
		mv.addObject("usuarios", usuarios.findAll());
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.usuarios.save(usuario);
			return new ModelAndView("redirect:usuarios");
		}catch(Exception e){return mv;}
		
	}
	
	/*@PostMapping("")
	public String salvar(Usuario usuario) {
		this.usuarios.save(usuario);
		return "redirect:/usuarios";
	}*/
	
	@RequestMapping(value ="/excluir/{idUsuario}")
	public String excluirConvidadoByPathVariable(@PathVariable Long idUsuario, HttpServletRequest request, 
					HttpServletResponse response) {
		this.usuarios.delete(idUsuario);
		return "redirect:/usuarios";
		/*ModelAndView mv = new ModelAndView("ListaConvidados");
		String message = "Convidado foi apagado com sucesso.";
		mv.addObject("message", message);
		mv.addObject(new Convidado());
		mv.addObject("convidados", convidados.findAll());
		return mv;*/
	}
	
	@RequestMapping("/alterar/{idUsuario}")
	public ModelAndView alterarConvidadoByPathVariable(@PathVariable Long idUsuario, HttpServletRequest request, 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmUsuarios");
		mv.addObject("usuarios",usuarios.findAll());
		Usuario usuario = usuarios.findOne(idUsuario);
		mv.addObject(usuario);
		return mv;
	}
	
	@RequestMapping(value="{idUsuario}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idUsuario, RedirectAttributes attributes) {
		usuarios.delete(idUsuario);
		attributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");
		return "redirect:/usuarios";
	}


}
