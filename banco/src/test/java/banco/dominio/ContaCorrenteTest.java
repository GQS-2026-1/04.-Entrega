package banco.dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import banco.excecoes.SaldoInsuficienteException;
import banco.excecoes.ValorInvalidoException;

@DisplayName("Testes da Classe ContaCorrente")
class ContaCorrenteTest {

    private ContaCorrente conta;
    private Cliente dummyCliente;

    @BeforeEach
    void setUp() {
        dummyCliente = new Cliente("João da Silva", "12345678901");
        conta = new ContaCorrente("1001-X", dummyCliente, new BigDecimal("100.00"));
    }

    @Test
    @DisplayName("Deve realizar depósito válido com sucesso")
    void testDepositoValido() {
        conta.depositar(new BigDecimal("50.00"));
        
        BigDecimal saldoEsperado = new BigDecimal("150.00");
        assertEquals(0, saldoEsperado.compareTo(conta.getSaldo()), "O saldo deve aumentar no exato valor do depósito");
    }

    @Test
    @DisplayName("Deve lançar ValorInvalidoException ao tentar depositar valor zero ou negativo")
    void testDepositoComValorInvalido() {
        assertThrows(ValorInvalidoException.class, () -> {
            conta.depositar(BigDecimal.ZERO);
        }, "Não deve permitir depósito de valor zero");

        assertThrows(ValorInvalidoException.class, () -> {
            conta.depositar(new BigDecimal("-10.00"));
        }, "Não deve permitir depósito de valor negativo");
    }

    @Test
    @DisplayName("Deve realizar saque com tarifa de R$ 1,00 descontada corretamente")
    void testSaqueComTarifa() {
        conta.sacar(new BigDecimal("49.00"));
        
        BigDecimal saldoEsperado = new BigDecimal("50.00");
        assertEquals(0, saldoEsperado.compareTo(conta.getSaldo()), "O saldo deve ser deduzido do valor do saque mais a tarifa de R$ 1,00");
    }

    @Test
    @DisplayName("Deve lançar SaldoInsuficienteException se o saque + tarifa ultrapassar o saldo")
    void testSaqueSemSaldoSuficiente() {
        assertThrows(SaldoInsuficienteException.class, () -> {
            conta.sacar(new BigDecimal("100.00"));
        }, "Deve lançar exceção pois faltará R$ 1,00 para cobrir a tarifa do banco");
    }

    @Test
    @DisplayName("Deve realizar transferência válida entre contas com sucesso")
    void testTransferenciaEntreContas() {
        Cliente clienteDestino = new Cliente("Maria Oliveira", "10987654321");
        ContaPoupanca contaDestino = new ContaPoupanca("2002-Y", clienteDestino, new BigDecimal("0.00"));
        
        conta.transferir(contaDestino, new BigDecimal("40.00"));
        
        BigDecimal saldoEsperadoOrigem = new BigDecimal("59.00");
        BigDecimal saldoEsperadoDestino = new BigDecimal("40.00");
        
        assertEquals(0, saldoEsperadoOrigem.compareTo(conta.getSaldo()), "O saldo da conta origem deve diminuir no valor da transferência + tarifa de R$ 1,00");
        assertEquals(0, saldoEsperadoDestino.compareTo(contaDestino.getSaldo()), "O saldo da conta destino deve aumentar no valor da transferência");
    }
    
    @Test
    @DisplayName("Deve lançar SaldoInsuficienteException ao tentar transferir mais do que o saldo disponível")
    void testTransferenciaSemSaldoSuficiente() {
        ContaPoupanca contaDestino = new ContaPoupanca("2002-Y", dummyCliente, new BigDecimal("0.00"));
        
        assertThrows(SaldoInsuficienteException.class, () -> {
            conta.transferir(contaDestino, new BigDecimal("150.00"));
        }, "Deve barrar a transferência se o saldo for menor que o valor solicitado");
    }
}