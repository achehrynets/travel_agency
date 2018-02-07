package web.servlet;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet(
        "/imageServlet"
)
public class ImageServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Image servlet start");
        resp.setContentType("image/png");
        String path = req.getParameter("path");

        LOGGER.trace("Image path: " + path);

        File file = new File(path);
        BufferedImage bufferedImage = ImageIO.read(file);
        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        outputStream.close();
        LOGGER.debug("Image servlet end");
    }
}
