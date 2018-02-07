package web.servlet;

import constant.AppConst;
import db.DAO.CountryDAO;
import db.DAOFactory;
import db.entity.Country;
import exception.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(
        name = "StartupServlet",
        urlPatterns = "/"
)
public class StartupServlet extends HttpServlet{



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Country> countries = new ArrayList<>();

        try {
            DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
            CountryDAO countryDAO = factory.countryDAO();
            countries = countryDAO.getAllCountries();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        req.setAttribute("countries", countries);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
