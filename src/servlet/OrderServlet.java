package servlet;

import dao.impl.orderImpl.StatusOrderDaoImpl;
import dao.orderDao.StatusOrderDao;
import dto.clientDto.ClientDto;
import dto.orderDto.OrderRentalDto;
import entity.order.OrderRental;
import entity.order.StatusOrder;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mapper.OrderMapper;
import mapper.impl.OrderMapperImpl;
import service.CarService;
import service.OrderService;
import service.impl.CarServiceImpl;
import service.impl.OrderServiceImpl;
import util.JspHelper;
import util.Path;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private final CarService carService = CarServiceImpl.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final StatusOrderDao statusOrderDao = StatusOrderDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var carId = Long.valueOf(req.getParameter("carId"));
        req.setAttribute("carOrder", carService.findById(carId));

        HttpSession session = req.getSession();
        var client = session.getAttribute("client");
        if (client != null) {
            req.setAttribute("client", client);
        } else
            req.setAttribute("clientName", "Guest");

        req.getRequestDispatcher(JspHelper.getPath(Path.ORDER.getPath()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspHelper.getPath(Path.ORDER.getPath()));

        var carId = Long.valueOf(req.getParameter("carId"));
        req.setAttribute("carOrder", carService.findById(carId));
        ClientDto client = (ClientDto) req.getSession().getAttribute("client");
        Optional<StatusOrder> byId = statusOrderDao.findById(1L);
        StatusOrder statusOrder = byId.orElse(new StatusOrder());

        OrderRentalDto orderRentalDto = OrderRentalDto.builder()
                .client(client)
                .car(carService.findById(carId))
                .rentalStartDate(req.getParameter("startDate"))
                .rentalEndDate(req.getParameter("endDate"))
                .totalRentalCost("cost")
                .statusOrder(statusOrder)
                .build();
        try {
            orderService.create(orderRentalDto);
            resp.sendRedirect("/personalAccount");
        } catch (ValidationException exception) {
            System.out.println("ВСЕ ERROR в SERVLET" + exception.getErrors());

            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }


    }
}
