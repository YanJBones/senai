<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShoesHappy - Cadastro de Clientes</title>
</head>
<body>
    <h1>ShoesHappy - Cadastro de Clientes</h1>
    
    <h2>Novo Cliente</h2>
    <form action="cliente" method="post">
        <input type="hidden" name="acao" value="inserir">
        Nome: <input type="text" name="nome" required><br>
        Endereço: <input type="text" name="endereco" required><br>
        Modalidade: <input type="text" name="modalidade" required><br>
        <input type="submit" value="Cadastrar">
    </form>
    
    <h2>Lista de Clientes</h2>
    <table border="1">
        <tr>
            <th>Matrícula</th>
            <th>Nome</th>
            <th>Endereço</th>
            <th>Modalidade</th>
            <th>Ação</th>
        </tr>
        <c:forEach items="${clientes}" var="cliente">
            <tr>
                <td>${cliente.matricula}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.endereco}</td>
                <td>${cliente.modalidade}</td>
                <td>
                    <a href="editarCliente.jsp?matricula=${cliente.matricula}">Editar</a>
                    <a href="cliente?acao=excluir&matricula=${cliente.matricula}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
