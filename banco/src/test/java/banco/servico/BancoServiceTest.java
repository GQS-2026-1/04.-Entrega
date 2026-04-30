package banco.servico;

import banco.dominio.Cliente;
import banco.dominio.Conta;
import banco.dominio.ContaCorrente;
import banco.excecoes.ClienteNaoEncontradoException;
import banco.excecoes.ContaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BancoServiceTest {

    private BancoService bancoService;

    @BeforeEach
    void setUp() {
        bancoService = new BancoService();
    }

    @Test
    void deveCadastrarEBuscarClientePorId() {
        Cliente cliente = new Cliente("Cleiton", "12345678901");

        bancoService.cadastrarCliente(cliente);

        Cliente clienteEncontrado = bancoService.buscarClientePorId(cliente.getId());

        assertNotNull(clienteEncontrado);
        assertEquals(cliente.getId(), clienteEncontrado.getId());
        assertEquals("Cleiton", clienteEncontrado.getNome());
        assertEquals("12345678901", clienteEncontrado.getCpf());
    }

    @Test
    void deveLancarExcecaoAoBuscarClienteInexistente() {
        ClienteNaoEncontradoException exception = assertThrows(
                ClienteNaoEncontradoException.class,
                () -> bancoService.buscarClientePorId("id-inexistente")
        );

        assertEquals(
                "Cliente nao encontrado com id: id-inexistente",
                exception.getMessage()
        );
    }

    @Test
    void deveCadastrarEBuscarContaPorNumero() {
        Cliente cliente = new Cliente("Henrique", "12345678901");
        Conta conta = new ContaCorrente("12345", cliente, BigDecimal.valueOf(100));

        bancoService.cadastrarConta(conta);

        Conta contaEncontrada = bancoService.buscarContaPorNumero("12345");

        assertNotNull(contaEncontrada);
        assertEquals("12345", contaEncontrada.getNumero());
        assertEquals(cliente, contaEncontrada.getTitular());
        assertEquals(BigDecimal.valueOf(100), contaEncontrada.getSaldo());
    }

    @Test
    void deveLancarExcecaoAoBuscarContaInexistente() {
        ContaNaoEncontradaException exception = assertThrows(
                ContaNaoEncontradaException.class,
                () -> bancoService.buscarContaPorNumero("00000")
        );

        assertEquals(
                "Conta nao encontrada com numero: 00000",
                exception.getMessage()
        );
    }
}