import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/download")
public class DownLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");
        String realPath = this.getServletContext().getRealPath("/WEB-INF/classes/" + filename);
        System.out.println(realPath);
        FileInputStream fileInputStream = new FileInputStream(realPath);
        resp.setHeader("content-disposition","attachment;filename="+filename);
        String mimeType = this.getServletContext().getMimeType(filename);
        resp.setContentType(mimeType);
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] byteBuffer=new byte[1014];
        int length=0;
        while ((length=fileInputStream.read(byteBuffer))!=-1)
            outputStream.write(byteBuffer,0,length);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
