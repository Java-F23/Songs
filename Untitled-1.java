import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class CrossTrianglesDrawing extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int triangleSize = Math.min(width, height) / 4;

        // Calculate triangle vertices
        int centerX = width / 2;
        int centerY = height / 2;

        int x1 = centerX - triangleSize / 2;
        int y1 = centerY - triangleSize;
        int x2 = centerX - triangleSize / 2;
        int y2 = centerY;

        int x3 = centerX + triangleSize / 2;
        int y3 = centerY - triangleSize / 2;
        int x4 = centerX + triangleSize / 2;
        int y4 = centerY + triangleSize / 2;

        // Create and draw the triangles
        drawTriangle(g2d, x1, y1, x2, y2, x3, y3);
        drawTriangle(g2d, x1, y1, x2, y2, x4, y4);
        drawTriangle(g2d, x3, y3, x2, y2, x4, y4);
        drawTriangle(g2d, x3, y3, x4, y4, x2, y2);
    }

    private void drawTriangle(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3) {
        Path2D path = new Path2D.Double();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.closePath();

        g2d.setColor(Color.BLACK); // Change the color to black
        g2d.fill(path);
        g2d.setColor(Color.BLACK); // You can change the outline color as well
        g2d.draw(path);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cross Triangles Drawing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CrossTrianglesDrawing());
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
