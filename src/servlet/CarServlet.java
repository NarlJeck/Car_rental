package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarService;
import service.impl.CarServiceImpl;
import util.JspHelper;
import util.Path;

import java.io.IOException;

@WebServlet("/car")
public class CarServlet extends HttpServlet {

    private final CarService carService = CarServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var carId =Long.valueOf(req.getParameter("carId"));
        req.setAttribute("carCurrent",carService.findById(carId));
        req.getRequestDispatcher(JspHelper.getPath(Path.CAR.getPath()))
                .forward(req, resp);
        System.out.println(carService.findById(carId));
    }


}
