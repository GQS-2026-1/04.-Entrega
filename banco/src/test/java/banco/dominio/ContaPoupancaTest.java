package banco.dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import banco.excecoes.SaldoInsuficienteException;
import banco.excecoes.ValorInvalidoException;

public class ContaPoupancaTest {

    private ContaPoupanca conta;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Kaio Jorge Cristiano Messi JR", "12345678900");
        conta = new ContaPoupanca(
                "001",
                cliente,
                new BigDecimal("1000.00"));
    }

    @Test
    void deveRealizarSaqueSemTarifa() {

        conta.sacar(new BigDecimal("200.00"));

        assertEquals(
                new BigDecimal("800.00"),
                conta.getSaldo());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoForInsuficiente() {

        assertThrows(
                SaldoInsuficienteException.class,
                () -> conta.sacar(new BigDecimal("1500.00")));
    }

    @Test
    void deveRenderComTaxaValida() {

        conta.render(new BigDecimal("10"));

        assertEquals(
                new BigDecimal("1100.000000"),
                conta.getSaldo().setScale(6));
    }

    @Test
    void deveManterSaldoComTaxaZero() {

        conta.render(BigDecimal.ZERO);

        assertEquals(
                new BigDecimal("1000.000000"),
                conta.getSaldo().setScale(6));
    }

    @Test
    void deveLancarExcecaoQuandoTaxaForNegativa() {

        assertThrows(
                ValorInvalidoException.class,
                () -> conta.render(new BigDecimal("-5")));
    }
}