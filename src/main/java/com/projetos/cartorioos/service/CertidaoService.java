package com.projetos.cartorioos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.cartorioos.dto.Cartorio;
import com.projetos.cartorioos.dto.Certidao;
import com.projetos.cartorioos.repository.CertidaoRepository;

@Service
public class CertidaoService  {

	@Autowired
	CertidaoRepository certidaoRepository;
	
	public List<Certidao> getAllCertidao() {
		//Objetos temporários para ajudar na conversão de classes do tipo Entity para DTO
		Certidao certidaoTemp = new Certidao();
		Cartorio cartorioTemp = new Cartorio();
		
		Iterable<com.projetos.cartorioos.entity.Certidao> certidoes;
		List<Certidao> certidoesDto = new ArrayList<>();
		
		certidoes =  certidaoRepository.findAll();
		
		//montando resposta (convertendo classes)
		certidoes.forEach(certidao ->{
			cartorioTemp.setId(certidao.getCartorio().getId());
			cartorioTemp.setNome(certidao.getCartorio().getNome());
			cartorioTemp.setEndereco(certidao.getCartorio().getEndereco());
			
			certidaoTemp.setId(certidao.getId());
			certidaoTemp.setNome(certidao.getNome());
			certidaoTemp.setCartorio(cartorioTemp);
			
			 certidoesDto.add(certidaoTemp);
			
		});
		
		return certidoesDto;
	}
}
