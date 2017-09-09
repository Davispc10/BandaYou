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

import com.algaworks.bandayou.model.Festa;
import com.algaworks.bandayou.repository.Festas;


@Controller
@RequestMapping("/festas")
public class FestasController {
	@Autowired
	private Festas festas;

	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("LisFesta");
		mv.addObject("festas",festas.findAll());
	return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmFesta");
		mv.addObject(new Festa());
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Festa festa, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("FrmFesta");
		mv.addObject("festas", festas.findAll());
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.festas.save(festa);
			return new ModelAndView("redirect:festas");
		}catch(Exception e){return mv;}
		
	}
	
	@RequestMapping(value ="/excluir/{idFesta}")
	public String excluirFestaByPathVariable(@PathVariable Long idFesta, HttpServletRequest request, 
					HttpServletResponse response) {
		this.festas.delete(idFesta);
		return "redirect:/festas";
	}
	
	@RequestMapping("/alterar/{idFesta}")
	public ModelAndView alterarFestaByPathVariable(@PathVariable Long idFesta, HttpServletRequest request, 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmFesta");
		mv.addObject("festas",festas.findAll());
		Festa festa = festas.findOne(idFesta);
		mv.addObject(festa);
		return mv;
	}
	
	@RequestMapping(value="{idFesta}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idFesta, RedirectAttributes attributes) {
		festas.delete(idFesta);
		attributes.addFlashAttribute("mensagem", "Festa excluída com sucesso!");
		return "redirect:/festas";
	}

}
