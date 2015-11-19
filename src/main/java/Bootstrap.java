import com.github.gitrn.invitevb.controllers.ApplicationHandler;
import com.github.gitrn.invitevb.views.InviteVBWindow;
import java.awt.EventQueue;

public class Bootstrap {
    public static void main(String[] args) {
        new ApplicationHandler(new InviteVBWindow());
    }
}