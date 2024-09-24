import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        BancoDeDados bancoDeDados = new BancoDeDados();  // Instanciação da conexão com o banco
        Caixa caixa = new Caixa();
        Movimentacao mov = new Movimentacao();

        // Variáveis para entradas
        double dizimo = 0, oferta = 0,
        arrecadacaoFesta = 0, totalEntradas = 0,
        doacao = 0, cMatriz = 0, dFixa = 0, dEvento = 0,
        saldoFinal = 0, totalSaidas = 0,
        saldoAtual = 0, saldoAnterior = 0;
        String tipoArrecadacao = "", data_lancamento;
        Scanner scanner = new Scanner(System.in);
        int opcaoMenu, anoReferente = 0, mesReferente = 0;

        System.out.print("Bem vindo!\n\n");
        for (;;) {
            // Entrada de Menu
            System.out.println("MENU");
            System.out.println("1. Ver caixa");
            System.out.println("2. Lançar dados");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            opcaoMenu = scanner.nextInt();

            if (opcaoMenu == 1) {
                System.out.print("\nInforme o ano de referencia: ");
                anoReferente = scanner.nextInt();
                System.out.println("\nCaixa referente ao ano de " + anoReferente + "\n");
                caixa.pegarCaixa(anoReferente);
            } else if (opcaoMenu == 2) {
                //dizimo
                System.out.print("Informe o valor de entrada de dízimo: R$ ");
                dizimo = scanner.nextDouble();
                //oferta
                System.out.print("Informe o valor de entrada de ofertas: R$ ");
                oferta = scanner.nextDouble();
                //arrecadacoes festa
                System.out.print("Informe o valor de entrada das arrecadoações em eventos: R$ ");
                arrecadacaoFesta = scanner.nextDouble();
                //doacao
                System.out.print("Informe o valor de entrada de doações: R$ ");
                doacao = scanner.nextDouble();
                //despesa fixa
                System.out.print("Informe o valor das despesas fixas: R$ ");
                dFixa = scanner.nextDouble();
                //despesa evento
                System.out.print("Informe o valor das dispesas de Evento: R$ ");
                dEvento = scanner.nextDouble();

                caixa.pegarUltimoSaldo();
                mesReferente = caixa.mesReferente + 1;
                anoReferente = caixa.anoReferente;
                saldoAnterior = caixa.saldoAtual;

                //Cálculo total de entradas
                totalEntradas = dizimo + oferta + arrecadacaoFesta + doacao;

                //Cálculo total de saidas
                totalSaidas = dFixa + dEvento + cMatriz;

                //Cálculo de 20% do dízimo
                cMatriz = dizimo * 0.20;
                System.out.printf("A contribuição de 20%% para Matriz é: R$ %.2f\n", cMatriz);

                //Subtração de 20% do dízimo das entradas
                totalEntradas -= cMatriz;

                //Cálculo do saldo final
                saldoFinal = totalEntradas - totalSaidas;
                saldoAtual = saldoAnterior + saldoFinal;

                //Exibir o saldo e status
                System.out.println("\n--- Resultado Final ---");
                System.out.printf("Total de Entradas (com 20%% do dízimo subtraído): R$ %.2f\n", totalEntradas);
                System.out.printf("Total de Saídas: R$ %.2f\n", totalSaidas);
                System.out.printf("Saldo Final: R$ %.2f\n", saldoFinal);

                mov.inserirDados(
                    dizimo,
                    oferta,
                    arrecadacaoFesta,
                    doacao,
                    cMatriz,
                    dFixa,
                    dEvento,
                    mesReferente,
                    anoReferente
                );
                caixa.inserirDados(
                    totalEntradas,
                    totalSaidas,
                    saldoFinal,
                    mesReferente,
                    anoReferente,
                    saldoAtual,
                    saldoAnterior
                );

                if (saldoFinal > 0) {
                    System.out.println("O caixa ficou positivo.");
                } else if (saldoFinal < 0) {
                    System.out.println("O caixa ficou negativo.");
                } else {
                    System.out.println("O caixa ficou zerado.");
                }
            } else if (opcaoMenu == 3) {
                return ;
            } else {
                System.out.println("\nOpção inválida!\n\n");
            }
        }
    }
}
