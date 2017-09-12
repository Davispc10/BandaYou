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

import com.algaworks.bandayou.model.Banda;
import com.algaworks.bandayou.repository.Bandas;

@Controller
@RequestMapping("/bandas")
public class BandaController {
	@Autowired
	private Bandas bandas;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("LisBanda");
		mv.addObject("bandas", bandas.findAll());
	    return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmBanda");
		mv.addObject(new Banda());
		return mv;
	}
	
	@PostMapping("")
	public String salvar(@Validated Banda banda, Errors erros, RedirectAttributes redirectAttributes){
		//ModelAndView mv = new ModelAndView("FrmBanda");
		//mv.addObject("bandas", bandas.findAll());
		if(erros.hasErrors()){
			return "FrmBanda";
		}
		//try {
		this.bandas.save(banda);
		redirectAttributes.addFlashAttribute("mensagem", "Banda salva com sucesso!");
		return "redirect:/bandas";
		//} catch(Exception e) {return mv;}		
	}	

	@RequestMapping(value ="/excluir/{idBanda}")
	public String excluirBandaByPathVariable(@PathVariable Long idBanda, HttpServletRequest request, 
			HttpServletResponse response) {
		this.bandas.delete(idBanda);
		//attributes.addFlashAttribute("mensagem", "Banda excluída com sucesso!");
		return "redirect:/bandas";
	}
	
	@RequestMapping("/alterar/{idBanda}")
	public ModelAndView alterarBandaByPathVariable(@PathVariable Long idBanda, HttpServletRequest request, 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmBanda");
		mv.addObject("bandas", bandas.findAll());
		Banda banda = bandas.findOne(idBanda);
		mv.addObject(banda);
		return mv;
	}
	
	@RequestMapping(value="{idBanda}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idBanda, RedirectAttributes attributes) {
		bandas.delete(idBanda);
		attributes.addFlashAttribute("mensagem", "Banda excluída com sucesso!");
		return "redirect:/bandas";
	}
}
