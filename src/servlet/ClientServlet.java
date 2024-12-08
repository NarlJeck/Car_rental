package servlet;

import dto.clientDto.ClientDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;
import service.impl.ClientServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    ClientService clientService = ClientServiceImpl.getINSTANCE();



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try(PrintWriter writer = response.getWriter()) {

            String title = "Database Demo";
            String docType = "<!DOCTYPE html>";
            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");

            List<ClientDto> all = clientService.findAll();
            writer.write("<br>" + all + "</br>");

            ClientDto byId = clientService.findById(1L);
            writer.write("<br>" + byId + "</br>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

