package com.rmarini.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rmarini.pontointeligente.api.entities.Empresa;
import com.rmarini.pontointeligente.api.entities.Funcionario;
import com.rmarini.pontointeligente.api.entities.Lancamento;
import com.rmarini.pontointeligente.api.enums.PerfilEnum;
import com.rmarini.pontointeligente.api.enums.TipoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private Long funcionarioId;

	@Before
	public void setup() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());

		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();

		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void testBuncarLancamentosPorFuncionarioId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);

		assertEquals(2, lancamentos.size());
	}

	@Test
	public void testBuncarLancamentosPorFuncionarioIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);

		assertEquals(2, lancamentos.getTotalElements());
	}

	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setDescricao("Teste de lançamento");
		lancamento.setLocalizacao("Home Office");
		lancamento.setFuncionario(funcionario);
		return lancamento;
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Zé das Cove");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha("123456");
		funcionario.setCpf("88565478");
		funcionario.setEmail("teste@email.com");
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
