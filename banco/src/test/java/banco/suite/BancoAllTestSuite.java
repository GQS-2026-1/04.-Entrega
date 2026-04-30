package banco.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    banco.dominio.ClienteTest.class,
    // banco.dominio.ContaTest.class,
    // banco.dominio.ContaCorrenteTest.class,
    // banco.dominio.ContaPoupancaTest.class
})
public class BancoAllTestSuite {
}