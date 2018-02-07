package web.servlet.wizard;

import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.entity.Hotel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import util.FileReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(
        "/wizard/add/hotel"
)
public class AddHotelWizardServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddHotelWizardServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        PrintWriter out = resp.getWriter();

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String[] requestParameters = new String[4];
            try {
                Hotel hotel = new Hotel();
                String imagePath;

                List items = upload.parseRequest(req);
                Iterator it = items.iterator();
                for (int i = 0; it.hasNext(); i++) {
                    FileItem item = (FileItem) it.next();
                    if (item.isFormField()) {
                        requestParameters[i] = item.getString();
                        LOGGER.trace("Request parameter " + item.getFieldName() + ": " + requestParameters[i]);
                    } else {
                        imagePath = Path.HOTEL_DIR + File.separator + requestParameters[0] + ".png";
                        File file = new File(imagePath);
                        item.write(file);
                        LOGGER.trace("Image path: " + imagePath);
                        hotel.setImagePath(imagePath);
                    }
                }
                hotel.setName(requestParameters[0]);
                hotel.setStars(Integer.valueOf(requestParameters[1]));
                hotel.setPrice(Float.valueOf(requestParameters[2]));
                hotel.setDescription(requestParameters[3]);

                HttpSession session = req.getSession();
                session.setAttribute("hotel", hotel);
                LOGGER.trace("Hotel with name: " + hotel.getName() + " successfully added to session");

                String path = getServletContext().getRealPath(Path.FILE_ADD_TOUR_WIZARD_PART);
                File file = new File(path);
                FileReader reader = new util.FileReader(file);
                String page = reader.read();

                LOGGER.debug(InfoMessages.INFO_ACTION_END);
                out.write(page);
            } catch (FileUploadException ex) {
                out.write(ErrorMessages.ERROR_CAN_NOT_UPLOAD_IMAGE);
            } catch (Exception ex) {
                System.out.println(ex.toString());
                out.write("Something wrong, try again later");
            } finally {
                out.close();
            }
        } else {
            out.write(ErrorMessages.ERROR_CAN_NOT_UPLOAD_IMAGE_NOT_MULTIPART_DATA);
        }
    }
}

