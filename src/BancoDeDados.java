import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoDeDados {

    // Conexão com o Banco de Dados MariaDB
    private static final String URL = "jdbc:mariadb://localhost:3306/igreja_db";
    private static final String USER = "root";  // Usuário do MariaDB
    private static final String PASSWORD = "7931852456";  // Senha do MariaDB

    public Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
        return conn;
    }

    // Método para inserir dados no banco
    public void inserirDados(
        double dizimo,
        double oferta,
        double arrecadacao,
        String tipoArrecadacao,
        double despesas,
        double saldoFinal,
        String data_lancamento
    ) {
        String sql = "INSERT INTO caixa " +
        "(dizimo, oferta, arrecadacao, tipo_arrecadacao, despesas, saldo_final, data_lancamento)" +
        " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, dizimo);
            stmt.setDouble(2, oferta);
            stmt.setDouble(3, arrecadacao);
            stmt.setString(4, tipoArrecadacao);
            stmt.setDouble(5, despesas);
            stmt.setDouble(6, saldoFinal);
            stmt.setString(7, data_lancamento);

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco!");
            e.printStackTrace();
        }
    }
}
