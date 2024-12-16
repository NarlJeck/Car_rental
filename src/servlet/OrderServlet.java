package servlet;

import dto.clientDto.ClientDto;
import entity.client.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.JspHelper;
import util.Path;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        var client = session.getAttribute("client");
        if(client!=null){
            req.setAttribute("client",client);
        }else
            req.setAttribute("clientName","Гость");
        req.getRequestDispatcher(JspHelper.getPath(Path.ORDER.getPath()))
                .forward(req,resp);


    }
}
