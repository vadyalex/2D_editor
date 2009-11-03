import com.vady.editor.gui.MainWindow;
import org.junit.Test;


public class SimpleTest {

    @Test
    public void testIt() {

    }

    public static void main(String[] args) {

        System.setProperty("apple.laf.useScreenMenuBar", "true");

        new MainWindow();
    }

}
