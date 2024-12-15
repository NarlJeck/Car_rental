package servlet;

import dto.clientDto.CreateRegistrationClientDto;
import dto.clientDto.RoleDto;
import exception.ValidationException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;
import service.RoleService;
import service.impl.ClientServiceImpl;
import service.impl.RoleServiceImpl;
import util.JspHelper;
import util.Path;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(JspHelper.getPath(Path.REGISTRATION.getPath()))
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(Path.REGISTRATION.getPath()));

        CreateRegistrationClientDto clientDto = CreateRegistrationClientDto.builder()
                .fullName(req.getParameter("full_name"))
                .phoneNumber(req.getParameter("phone_number"))
                .email(req.getParameter("email"))
                .residentialAddress(req.getParameter("residential_address"))
                .password(req.getParameter("password"))
                .build();
        try{
            clientService.create(clientDto);
            resp.sendRedirect("/login");
        }catch (ValidationException exception){
            req.setAttribute("errors",exception.getErrors());
            doGet(req,resp);
        }

    }
}
