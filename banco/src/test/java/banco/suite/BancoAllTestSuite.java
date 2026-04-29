package banco.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ClienteTest.class,
    ContaTest.class,
    ContaCorrenteTest.class,
    ContaPoupancaTest.class
})
public class BancoAllTestSuite {
}