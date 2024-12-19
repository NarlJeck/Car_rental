package servlet;

import dto.clientDto.ClientDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.ClientService;
import service.impl.ClientServiceImpl;
import util.JspHelper;
import util.Path;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(Path.LOGIN.getPath()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        clientService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(
                        client -> onLoginSuccess(client, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("/login?error&email=" + request.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(ClientDto client, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("client", client);
        response.sendRedirect("/main");
    }
}
