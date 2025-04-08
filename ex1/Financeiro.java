interface Juros {
    double calcularJurosCompostos(double principal, double taxa, int periodo);
}

interface AmortizacaoRequerJuros {
    void gerarPlanoAmortizacao(double principal, double taxa, int periodo, int parcelas);
}

class CalculadoraJuros implements Juros {
    @Override
    public double calcularJurosCompostos(double principal, double taxa, int periodo) {
        return principal * Math.pow(1 + taxa, periodo);
    }
}

class PlanoAmortizacao implements AmortizacaoRequerJuros {
    private final Juros calculadoraJuros;

    public PlanoAmortizacao(Juros calculadoraJuros) {
        this.calculadoraJuros = calculadoraJuros;
    }

    @Override
    public void gerarPlanoAmortizacao(double principal, double taxa, int periodo, int parcelas) {
        double amortizacao = principal / parcelas; 
        double saldoDevedor = principal;

        System.out.println("===== PLANO DE AMORTIZAÇÃO =====");
        for (int i = 1; i <= parcelas; i++) {
            double juros = saldoDevedor * taxa; 
            double valorParcela = amortizacao + juros; 
            saldoDevedor -= amortizacao; 

            System.out.printf("Parcela %d: R$ %.2f (Amortização: R$ %.2f, Juros: R$ %.2f, Divida Restante: R$ %.2f)%n",
                    i, valorParcela, amortizacao, juros, saldoDevedor);
        }
    }
}

public class Financeiro {
    public static void main(String[] args) {
        Juros calculadoraJuros = new CalculadoraJuros();
        AmortizacaoRequerJuros plano = new PlanoAmortizacao(calculadoraJuros);

        double valorEmprestimo = 10000.0;
        double taxaJuros = 0.02; 
        int periodoMeses = 12;
        int parcelas = 12;

        plano.gerarPlanoAmortizacao(valorEmprestimo, taxaJuros, periodoMeses, parcelas);
    }
}
