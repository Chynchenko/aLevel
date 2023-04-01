package servlets;

import model.CarBot;
import model.Type;
import service.CarService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CarServlet", value = "/carBot")
public class CarServlet extends HttpServlet {
    private CarBot bot;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        bot = new CarBot(Type.CAR,
                CarService.getInstance().create(Type.randomType()),
                CarService.getInstance().getAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doGet");

        String action = request.getParameter("action");

        request.setAttribute("carBot", bot);

        switch (action == null ? "info" : action) {
            case "create" -> request.getRequestDispatcher("/create.jsp").forward(request, response);
            case "info" -> request.getRequestDispatcher("/carBot.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Enter doPost");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("submit".equals(action)) {
            bot.setType(Type.valueOf(request.getParameter("type")));
            bot.setCar(CarService.getInstance().create(Type.valueOf(request.getParameter("type"))));
        }

        request.setAttribute("carBot", bot);
        request.getRequestDispatcher("/carBot.jsp").forward(request, response);
    }
}