package com.projetos.cartorioos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetos.cartorioos.dto.Certidao;
import com.projetos.cartorioos.service.CertidaoService;

@RestController
public class CertidaoResource {
	
	@Autowired
	CertidaoService certidaoService;
	
	@GetMapping("/api/certidao")
	public List<Certidao> getAllCertidao() {
		List<Certidao> certidoes = certidaoService.getAllCertidao();		
		return certidoes;
	}
}
