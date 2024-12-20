package servlet;

import dto.clientDto.CreateRegistrationClientDto;
import exception.ValidationException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;
import service.impl.ClientServiceImpl;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.LOGIN;
import static util.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(JspHelper.getPath(REGISTRATION))
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(REGISTRATION));

        CreateRegistrationClientDto clientDto = CreateRegistrationClientDto.builder()
                .fullName(req.getParameter("full_name"))
                .phoneNumber(req.getParameter("phone_number"))
                .email(req.getParameter("email"))
                .residentialAddress(req.getParameter("residential_address"))
                .password(req.getParameter("password"))
                .build();
        try {
            clientService.create(clientDto);
            resp.sendRedirect(LOGIN);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }
}
