package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;
import service.impl.ClientServiceImpl;
import service.impl.RoleServiceImpl;

import java.io.IOException;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    ClientService clientService = ClientServiceImpl.getInstance();
    RoleServiceImpl roleService = RoleServiceImpl.getInstance();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    public void destroy() {
        super.destroy();
    }
}

