package banco.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import banco.dominio.ClienteTest;
import banco.dominio.ContaCorrenteTest;
import banco.dominio.ContaPoupancaTest;


@Suite
@SelectClasses({
    ClienteTest.class,
    ContaCorrenteTest.class,
    ContaPoupancaTest.class
})
public class BancoAllTestSuite {
}