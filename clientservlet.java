import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Listar todos os clientes do banco de dados
        List<Cliente> clientes = Cliente.listar();

        // Armazenar a lista de clientes no escopo da requisição para que a JSP possa acessá-la
        request.setAttribute("clientes", clientes);

        // Encaminhar a requisição para a JSP responsável por exibir os clientes
        request.getRequestDispatcher("/WEB-INF/views/lista_clientes.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar os parâmetros do formulário
        String acao = request.getParameter("acao");
        int matricula = Integer.parseInt(request.getParameter("matricula"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String modalidade = request.getParameter("modalidade");

        // Criar um objeto Cliente com os dados do formulário
        Cliente cliente = new Cliente(matricula, nome, endereco, modalidade);

        // Verificar a ação do formulário (inserir, editar ou excluir)
        if (acao.equals("inserir")) {
            // Inserir um novo cliente no banco de dados
            cliente.inserir();
        } else if (acao.equals("editar")) {
            // Editar um cliente existente no banco de dados
            cliente.editar();
        } else if (acao.equals("excluir")) {
            // Excluir um cliente do banco de dados
            cliente.excluir();
        }

        // Redirecionar a requisição para doGet para atualizar a lista de clientes exibida
        response.sendRedirect(request.getContextPath() + "/cliente");
    }
}
