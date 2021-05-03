package com.projetos.cartorioos.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetos.cartorioos.entity.Cartorio;
import com.projetos.cartorioos.entity.Certidao;
import com.projetos.cartorioos.repository.CartorioRepository;
import com.projetos.cartorioos.repository.CertidaoRepository;

@Controller
public class CartorioController {
	
	@Autowired
	CartorioRepository cartorioRepository;
	
	@Autowired
	CertidaoRepository certidaoRepository;
	
	ModelAndView mv;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/novo-cartorio")
	public ModelAndView cadastraCartorio() {
		mv = new ModelAndView();
		mv.setViewName("/cartorio/novo-cartorio");
		mv.addObject("cartorio", new Cartorio());
		return mv;
	}
	
	@PostMapping("/cartorio")
	public String createCartorio(@ModelAttribute Cartorio cartorio, @RequestParam("certidao") List<String> listaCertidoes,
			RedirectAttributes redirectAttributes) {
		//convertendo List<String> em List<Certidao>
		List<Certidao> certidoes = new ArrayList<>();
		Certidao certidao;
		for (String nome : listaCertidoes) {
			certidao = new Certidao();
			certidao.setNome(nome);
			
			//relacionando a certidao com o cartorio
			certidao.setCartorio(cartorio);
			certidoes.add(certidao);	
		}
		
		//relacionando o cartorio com as certidoes
		cartorio.setCertidoes(certidoes);
		
		cartorioRepository.save(cartorio);		
		
		redirectAttributes.addFlashAttribute("msg", "Cartório adicionado com sucesso!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		
		
		return "redirect:novo-cartorio";
	}
	
	@GetMapping("/cartorio")
	public ModelAndView cartorios() {
		mv = new ModelAndView("cartorio/todos-cartorios");
		List<Cartorio> cartorios = (List<Cartorio>) cartorioRepository.findAll();
		mv.addObject("cartorios", cartorios);
		
		return mv;
	}
	
	@GetMapping("/cartorio/delete")
	public ModelAndView formDeleteCartorio() {
		mv = new ModelAndView("cartorio/form-delete-cartorio-id");
		mv.addObject("cartorio", new Cartorio());
		return mv;
	}
	
	@PostMapping("/cartorio/delete")
	public String deleteCartorio(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		//procura Cartorio pelo id
		Optional<Cartorio> cartorio =  cartorioRepository.findById(id);
		
		//irá deletar as certidões associadas ao cartório também
		cartorioRepository.delete(cartorio.get());
		
		redirectAttributes.addFlashAttribute("msg", "Cartório removido com sucesso!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/cartorio/delete";
	}
	
	@GetMapping("/cartorio/altera-cartorio-search")
	public String formBuscaAlteraCartorio() {
		return "cartorio/form-altera-search-id";
	}
	
	@GetMapping("/cartorio/altera")
	public ModelAndView formDeleteCartorio(@RequestParam Long id) {
		mv = new ModelAndView("cartorio/form-altera-cartorio");
		Optional<Cartorio> cartorio = cartorioRepository.findById(id);
		mv.addObject("cartorio", cartorio.get());
		mv.addObject("certidoes", cartorio.get().getCertidoes());
		return mv;
	}
	
	@PostMapping("/cartorio/altera")
	public String alteraCartorio(@ModelAttribute Cartorio cartorio, 
			@RequestParam(name = "certidao", defaultValue = "") List<String> listaNomesCertidoes, RedirectAttributes redirectAttributes) {
		
		//lógica de alteração quando tiver novos elementos
		if(!(listaNomesCertidoes.isEmpty() || listaNomesCertidoes.size() == 0)) {
			Certidao certidao;
			//adiciona nova certidão
			for (String nome : listaNomesCertidoes) { 
				certidao = new Certidao();
				certidao.setNome(nome);
				cartorio.getCertidoes().add(certidao);
			}
		}
		

		for (Certidao certidao : cartorio.getCertidoes()) {
			//faz as certidoes ficarem relacionadas com o cartorio
			certidao.setCartorio(cartorio);
		}
		//remove as certidões vazias
		for (int i = 0; i < cartorio.getCertidoes().size(); i++) {			
			//lógica quando tiver menos elementos
			
			if(cartorio.getCertidoes().get(i).getNome() == null || cartorio.getCertidoes().get(i).getNome().isBlank()) {
				//remove a certidão da lista
				certidaoRepository.delete(cartorio.getCertidoes().remove(i));
				i--;
			}  
		}
		
		redirectAttributes.addFlashAttribute("msg", "Cartório alterado com sucesso!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		cartorioRepository.save(cartorio);
		
		return "redirect:/cartorio/altera-cartorio-search";
	}
}
