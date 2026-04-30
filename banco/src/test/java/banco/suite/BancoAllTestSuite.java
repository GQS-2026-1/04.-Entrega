package banco.suite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
@Suite
@SelectClasses({
    banco.dominio.ClienteTest.class,
    banco.dominio.ContaCorrenteTest.class,
    banco.dominio.ContaPoupancaTest.class
})
public class BancoAllTestSuite { }