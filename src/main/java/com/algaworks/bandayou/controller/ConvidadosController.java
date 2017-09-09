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

import com.algaworks.bandayou.model.Convidado;
import com.algaworks.bandayou.repository.Convidados;



@Controller
@RequestMapping("/convidados")
public class ConvidadosController {

	@Autowired
	private Convidados convidados;

	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaConvidados");
		mv.addObject("convidados",convidados.findAll());
		mv.addObject(new Convidado());
		return mv;
	}
	@PostMapping("")
	public ModelAndView salvar(@Validated Convidado convidado, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("ListaConvidados");
		mv.addObject("convidados", convidados.findAll());
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.convidados.save(convidado);
			return new ModelAndView("redirect:convidados");
		}catch(Exception e){return mv;}
		
	}
	
	@RequestMapping(value ="/excluir/{id}")
	public String excluirConvidadoByPathVariable(@PathVariable Long id, HttpServletRequest request, 
					HttpServletResponse response) {
		this.convidados.delete(id);
		return "redirect:/convidados";
		/*ModelAndView mv = new ModelAndView("ListaConvidados");
		String message = "Convidado foi apagado com sucesso.";
		mv.addObject("message", message);
		mv.addObject(new Convidado());
		mv.addObject("convidados", convidados.findAll());
		return mv;*/
	}
	
	@RequestMapping("/alterar/{id}")
	public ModelAndView alterarConvidadoByPathVariable(@PathVariable Long id, HttpServletRequest request, 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("ListaConvidados");
		mv.addObject("convidados",convidados.findAll());
		Convidado convidado = convidados.findOne(id);
		mv.addObject(convidado);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		convidados.delete(id);
		attributes.addFlashAttribute("mensagem", "Convidados excluído com sucesso!");
		return "redirect:/convidados";
	}

}
