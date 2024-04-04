package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.util.StoreUtil;
//Http Servlet extended class for showing the about information
public class AboutServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    // Handle request processing
    // For example, read request parameters, perform business logic, etc.
    
    // Call method to render HTML response
    renderHTML(res);
}

private void renderHTML(HttpServletResponse res) throws IOException {
    PrintWriter pw = res.getWriter();
    res.setContentType("text/html");

    // Generate HTML content here
    pw.println("<html>");
    pw.println("<head><title>Sample Page</title></head>");
    pw.println("<body>");
    pw.println("<h1>Hello, World!</h1>");
    pw.println("</body>");
    pw.println("</html>");

    // Ensure proper resource cleanup
    pw.close();
}
        //If the store is logged in as customer or seller show about info
        if (StoreUtil.isLoggedIn(UserRole.CUSTOMER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
            rd.include(req, res);
            StoreUtil.setActiveTab(pw, "about");
            pw.println("<iframe src=\"https://flowcv.me/shashirajraja\" class=\"holds-the-iframe\"\r\n"
                    + "        title=\"My Personal Website\" width=\"100%\" height=\"100%\"></iframe>");

        } else if (StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
            rd.include(req, res);
            StoreUtil.setActiveTab(pw, "about");
            pw.println("<iframe src=\"https://flowcv.me/shashirajraja\" class=\"holds-the-iframe\"\r\n"
                    + "        title=\"My Personal Website\" width=\"100%\" height=\"100%\"></iframe>");

        } else {
            //If the user is not logged in, ask to login first
            //Proceed only if logged in or forword to login page
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
            pw.println("<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>");
        }

    }

}
