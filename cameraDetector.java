import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FaceDetection {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Webcam webcam = Webcam.getDefault();

            if (webcam == null) {
                System.out.println("No webcam found");
                return;
            }

            webcam.open();

            JFrame window = new JFrame("Face Detection");
            window.setSize(640, 480);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);

            window.add(panel);
            window.setVisible(true);

            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    webcam.close();
                    window.dispose();
                }
            });
        });
    }
}

