import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int matricula;
    private String nome;
    private String endereco;
    private String modalidade;

    // Construtor vazio
    public Cliente() {
    }

    // Construtor com parâmetros
    public Cliente(int matricula, String nome, String endereco, String modalidade) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.modalidade = modalidade;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    // Método para inserir um novo cliente no banco de dados
    public void inserir() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "seu_usuario", "sua_senha")) {
            String sql = "INSERT INTO clientes (nome, endereco, modalidade) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nome);
            stmt.setString(2, this.endereco);
            stmt.setString(3, this.modalidade);
            stmt.executeUpdate();

            // Obter a matrícula gerada pelo banco de dados
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.matricula = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para editar um cliente existente no banco de dados
    public void editar() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "seu_usuario", "sua_senha")) {
            String sql = "UPDATE clientes SET nome = ?, endereco = ?, modalidade = ? WHERE matricula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.nome);
            stmt.setString(2, this.endereco);
            stmt.setString(3, this.modalidade);
            stmt.setInt(4, this.matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um cliente do banco de dados
    public void excluir() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "seu_usuario", "sua_senha")) {
            String sql = "DELETE FROM clientes WHERE matricula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os clientes do banco de dados
    public static List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "seu_usuario", "sua_senha")) {
            String sql = "SELECT * FROM clientes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setMatricula(rs.getInt("matricula"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setModalidade(rs.getString("modalidade"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
