package com.rmarini.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rmarini.pontointeligente.api.entities.Empresa;
import com.rmarini.pontointeligente.api.entities.Funcionario;
import com.rmarini.pontointeligente.api.enums.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String EMAIL = "email@teste.com";
	private static final String CPF = "88569874566";

	@Before
	public void setup() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);

		assertEquals(EMAIL, funcionario.getEmail());
	}

	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);

		assertEquals(CPF, funcionario.getCpf());
	}

	@Test
	public void testBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarFuncionarioPorEmailOuCpfParaEmailInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "email@invalido.com");

		assertNotNull(funcionario);
	}

	@Test
	public void testBuscarFuncionarioPorEmailOuCpfParaCpfInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("5542365487", EMAIL);

		assertNotNull(funcionario);
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Zé das Cove");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha("123456");
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		funcionario.setQtdHorasTrabalhoDia(54F);
		funcionario.setQtdHorasAlmoco(1F);
		funcionario.setValorHora(BigDecimal.valueOf(23D));
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de Exemplo");
		empresa.setCnpj("98775566322145");
		return empresa;
	}

}
