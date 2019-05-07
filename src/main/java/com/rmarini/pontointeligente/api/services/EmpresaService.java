package com.rmarini.pontointeligente.api.services;

import java.util.Optional;

import com.rmarini.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscaPorCnpj(String cnpj);

	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa.
	 */
	Empresa persistir(Empresa empresa);
}
