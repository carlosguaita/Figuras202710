import Views.SistemaCLI;
import Views.SistemaSwing;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && "--cli".equals(args[0])) {
            new SistemaCLI().inicio();
        } else {
            SwingUtilities.invokeLater(() -> new SistemaSwing().setVisible(true));
        }
    }
}
