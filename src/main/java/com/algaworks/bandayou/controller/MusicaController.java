 package com.algaworks.bandayou.controller;

import java.util.List;

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
import com.algaworks.bandayou.model.Musica;
import com.algaworks.bandayou.repository.Bandas;
import com.algaworks.bandayou.repository.Musicas;

@Controller
@RequestMapping("/musicas")
public class MusicaController {
	@Autowired
	private Musicas musicas;
	@Autowired
	private Bandas bandas;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("LisMusica");
		mv.addObject("musicas", musicas.findAll());
	    return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmMusica");
		List<Banda> allbandas = bandas.findAll();
		mv.addObject(new Musica());		
		mv.addObject("allBandas", allbandas);
		return mv;
	}
	
	@PostMapping("")
	public String salvar(@Validated Musica musica, Errors erros, RedirectAttributes redirectAttributes) {
		//ModelAndView mv = new ModelAndView("FrmMusica");
		//mv.addObject("musicas", musicas.findAll());
		if(erros.hasErrors()){
			return "FrmMusica";
		}
		//try {
			this.musicas.save(musica);
			redirectAttributes.addFlashAttribute("mensagem", "Música salva com sucesso!");
			return "redirect:/musicas";
		//} catch(Exception e){return mv;}		
	}
	
	@RequestMapping(value ="/excluir/{idMusica}")
	public String excluirMusicaByPathVariable(@PathVariable Long idMusica, HttpServletRequest request, 
					HttpServletResponse response) {
		this.musicas.delete(idMusica);
		return "redirect:/musicas";
	}
	
	@RequestMapping("{idMusica}")
	public ModelAndView alterarMusicaByPathVariable(@PathVariable("idMusica") Long idMusica, HttpServletRequest request, 
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("FrmMusica");
		//mv.addObject("musicas", musicas.findAll());
		Musica musica = musicas.findOne(idMusica);
		mv.addObject(musica);
		List<Banda> allbandas = bandas.findAll();
		mv.addObject("allBandas", allbandas);
		//redirectAttributes.addFlashAttribute("mensagem", "Música Editada com sucesso!");
		//return "redirect:/musicas";
		return mv;
	}
	
	@RequestMapping(value="{idMusica}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idMusica, RedirectAttributes attributes) {
		musicas.delete(idMusica);
		attributes.addFlashAttribute("mensagem", "Música excluída com sucesso!");
		return "redirect:/musicas";
	}
}