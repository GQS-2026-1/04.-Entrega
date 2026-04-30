package banco.dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ClienteTest {

    @Test
    public void deveCriarClienteValido() {
        Cliente cliente = new Cliente("João", "12345678901");

        assertNotNull(cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertTrue(cliente.getContas().isEmpty());
    }

    @Test
    public void deveLancarErroParaNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("", "12345678901");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("   ", "12345678901");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "12345678901");
        });
    }

    @Test
    public void deveLancarErroParaCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Maria", "123");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Maria", null);
        });
    }

    @Test
    public void listaDeContasDeveSerSomenteLeitura() {
        Cliente cliente = new Cliente("João", "12345678901");

        assertThrows(UnsupportedOperationException.class, () -> {
            cliente.getContas().add(null);
        });
    }
}