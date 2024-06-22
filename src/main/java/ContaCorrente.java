public class ContaCorrente extends Conta {


    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    protected String retornarTipoDeConta() {
        return "Corrente";
    }
}
