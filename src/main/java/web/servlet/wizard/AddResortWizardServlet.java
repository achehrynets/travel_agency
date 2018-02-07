package web.servlet.wizard;

import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.entity.Resort;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import util.FileReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@WebServlet(
        "/wizard/add/resort"
)
public class AddResortWizardServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddResortWizardServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        PrintWriter out = resp.getWriter();

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String[] requestParameters = new String[2];
            try {
                Resort resort = new Resort();
                String imagePath = "";

                List items = upload.parseRequest(req);
                Iterator  it = items.iterator();
                for (int i = 0; it.hasNext(); i++) {
                    FileItem item = (FileItem) it.next();
                    if (item.isFormField()) {
                        requestParameters[i] = item.getString();
                        LOGGER.trace("Request parameter " + item.getFieldName() + ": " + requestParameters[i]);
                    } else {
                        imagePath = Path.RESORT_DIR + File.separator + requestParameters[0] + ".png";
                        File file = new File(imagePath);
                        item.write(file);
                        LOGGER.trace("Image path: " + imagePath);
                        resort.setImagePath(imagePath);
                    }
                }
                resort.setName(requestParameters[0]);
                resort.setDescription(requestParameters[1]);

                HttpSession session = req.getSession();
                session.setAttribute("resort", resort);
                LOGGER.trace("Resort with name: " + resort.getName() + " successfully added to session");

                String path = getServletContext().getRealPath(Path.FILE_ADD_HOTEL_WIZARD_PART);
                File file = new File(path);
                FileReader reader = new util.FileReader(file);
                String page = reader.read();

                LOGGER.debug(InfoMessages.INFO_ACTION_END);
                out.write(page);
            } catch (FileUploadException ex) {
                out.write(ErrorMessages.ERROR_CAN_NOT_UPLOAD_IMAGE);
            } catch (Exception ex) {
                out.write("Something wrong, try again later");
            } finally {
                out.close();
            }
        } else {
            out.write(ErrorMessages.ERROR_CAN_NOT_UPLOAD_IMAGE_NOT_MULTIPART_DATA);
        }
    }

}
