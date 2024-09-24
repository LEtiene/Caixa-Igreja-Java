import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Caixa {
    public double saldoAtual = 0;
    public int mesReferente = 0;
    public int anoReferente = 0;

    public void pegarCaixa(int anoReferente) {
        BancoDeDados banco = new BancoDeDados();
        String sql = "SELECT * FROM caixa";

        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 4. Executar a consulta
            ResultSet rs = stmt.executeQuery();

            System.out.println("Ano\tMes\tResultado\tEntrada\tSaída\tS.Atual\tS.Anterior");
            // 5. Processar os resultados
            while (rs.next()) {
                int id = rs.getInt("ID");
                double saldoAtual = rs.getDouble("SALDO_ATUAL");
                int mesReferente = rs.getInt("MES_REFERENTE");
                double saldoAnterior = rs.getDouble("SALDO_ANTERIOR");
                double entrada = rs.getDouble("ENTRADA");
                double saida = rs.getDouble("SAIDA");
                double resultado = rs.getDouble("RESULTADO");

                System.out.println(
                    anoReferente + "\t" +
                    mesReferente + "\t" +
                    resultado + "\t\t" +
                    entrada + "\t" +
                    saida + "\t" +
                    saldoAtual + "\t" +
                    saldoAnterior +
                    "\n"
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco!");
            e.printStackTrace();
        }
    }

    public void inserirDados(
        double entrada,
        double saida,
        double resultado,
        int mesReferente,
        int anoReferente,
        double saldoAtual,
        double saldoAnterior
    ) {
        BancoDeDados banco = new BancoDeDados();
        String sql = "INSERT INTO caixa " +
        "(ENTRADA, SAIDA, RESULTADO, MES_REFERENTE, ANO_REFERENTE, SALDO_ATUAL, SALDO_ANTERIOR)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, entrada);
            stmt.setDouble(2, saida);
            stmt.setDouble(3, resultado);
            stmt.setInt(4, mesReferente);
            stmt.setInt(5, anoReferente);
            stmt.setDouble(6, saldoAtual);
            stmt.setDouble(7, saldoAnterior);

            stmt.executeUpdate();
            // System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco!");
            e.printStackTrace();
        }
    }

    public void pegarUltimoSaldo() {
        BancoDeDados banco = new BancoDeDados();
        String sql = "SELECT * FROM igreja_db.caixa ORDER BY ID DESC LIMIT 1;";

        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // 4. Executar a consulta
            ResultSet rs = stmt.executeQuery();

            System.out.println("Ano\tMes\tResultado\tEntrada\tSaída\tS.Atual\tS.Anterior");
            // 5. Processar os resultados
            while (rs.next()) {
                this.saldoAtual = rs.getDouble("SALDO_ATUAL");
                this.anoReferente = rs.getInt("ANO_REFERENTE");
                this.mesReferente = rs.getInt("MES_REFERENTE");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco!");
            e.printStackTrace();
        }
    }
}
