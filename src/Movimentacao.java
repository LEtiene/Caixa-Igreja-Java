import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Movimentacao {
    BancoDeDados banco = new BancoDeDados();

    // Método para inserir dados no banco
    public void inserirDados(
        double dizimo,
        double oferta,
        double arrecadacaoFesta,
        double doacao,
        double cMatriz,
        double dFixa,
        double dEvento,
        int mesReferente,
        int anoReferente
    ) {
        String sql = "INSERT INTO movimentacoes " +
        "(ENTRADA_DIZIMO, OFERTAS, ARRECADACOES_FESTA, DOACAO, CONTRIBUICAO_MATRIZ, DISPESA_FIXA, DISPESA_EVENTO, MES_REFERENTE, ANO_REFERENTE)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, dizimo);
            stmt.setDouble(2, oferta);
            stmt.setDouble(3, arrecadacaoFesta);
            stmt.setDouble(4, doacao);
            stmt.setDouble(5, cMatriz);
            stmt.setDouble(6, dFixa);
            stmt.setDouble(7, dEvento);
            stmt.setInt(8, mesReferente);
            stmt.setInt(9, anoReferente);

            stmt.executeUpdate();
            // System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco!");
            e.printStackTrace();
        }
    }

}
