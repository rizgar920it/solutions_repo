import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RealisticWaveSimulation extends JPanel {
    private final List<Drop> drops = new ArrayList<>();
    private final int width = 600;
    private final int height = 600;
    private final double A = 1.0;
    private final double lambda = 20.0;
    private final double k = 2 * Math.PI / lambda;
    private final double omega = 0.2;
    private double t = 0;

    private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    public RealisticWaveSimulation(int numDrops) {
        setPreferredSize(new Dimension(width, height));
        Random rand = new Random();
        for (int i = 0; i < numDrops; i++) {
            drops.add(new Drop(rand.nextInt(width), rand.nextInt(height)));
        }

        Timer timer = new Timer(33, e -> {
            t += 1;
            renderFrame();
            repaint();
        });
        timer.start();
    }

    private void renderFrame() {
        for (int y = 0; y < height; y += 2) {
            for (int x = 0; x < width; x += 2) {
                double sum = 0;
                for (Drop drop : drops) {
                    double dx = x - drop.x;
                    double dy = y - drop.y;
                    double r = Math.sqrt(dx * dx + dy * dy) + 1e-6;
                    sum += A / Math.sqrt(r) * Math.cos(k * r - omega * t);
                }
                // Normalize and map to color
                int gray = (int) ((sum + drops.size()) / (2 * drops.size()) * 255);
                gray = Math.max(0, Math.min(255, gray));
                int color = new Color(gray, gray, 255).getRGB();

                for (int yy = y; yy < y + 2 && yy < height; yy++) {
                    for (int xx = x; xx < x + 2 && xx < width; xx++) {
                        image.setRGB(xx, yy, color);
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    static class Drop {
        int x, y;

        public Drop(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int numDrops = 3;
        try {
            String input = JOptionPane.showInputDialog("How many drops?");
            if (input != null) numDrops = Integer.parseInt(input.trim());
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to 3 drops.");
        }

        JFrame frame = new JFrame("Realistic Wave Interference");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new RealisticWaveSimulation(numDrops));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
