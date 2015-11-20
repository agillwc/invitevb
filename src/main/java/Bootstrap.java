import com.github.rnbr.invitevb.controllers.ApplicationHandler;
import com.github.rnbr.invitevb.views.InviteVBWindow;
import java.awt.EventQueue;

public class Bootstrap {
    public static void main(String[] args) {
        new ApplicationHandler(new InviteVBWindow());
    }
}