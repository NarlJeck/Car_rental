package servlet;

import dto.carDto.CarDto;
import dto.carDto.CarModelDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CarModelService;
import service.CarService;
import service.impl.CarModelServiceImpl;
import service.impl.CarServiceImpl;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

import static util.UrlPath.*;

@WebServlet(MAIN)
public class MainServlet extends HttpServlet {

    private final CarService carService = CarServiceImpl.getInstance();
    private final CarModelService carModelService = CarModelServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CarDto> carDtoList = carService.findAll();
        List<CarModelDto> carModelAll = carModelService.findAll();

        request.setAttribute("carModels", carModelAll);
        request.setAttribute("cars", carDtoList);

        request.getRequestDispatcher(JspHelper.getPath(CARS_MAIN))
                .forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
