package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.impl.CarServiceImpl;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/main")
public class CarServlet extends HttpServlet {
    private static final String MAIN_PATH = "main";

    private final CarService carService = CarServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setAttribute("cars",carService.findAll());

      request.getRequestDispatcher(JspHelper.getPath(MAIN_PATH))
              .forward(request,response);


    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
