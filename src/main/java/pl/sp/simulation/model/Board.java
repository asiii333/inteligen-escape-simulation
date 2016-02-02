package pl.sp.simulation.model;

/**
 * Created by Asia on 2015-06-22.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;



public class Board extends JComponent implements MouseInputListener, ComponentListener {
    private static final long serialVersionUID = 1L;
    private Point[][] points;
    private int size = 14;

    public Board(int length, int height) {
        addMouseListener(this);
        addComponentListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    // single iteration
    public void iteration() {
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y].calculateNewState();

        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y].changeState();
        this.repaint();
    }

    // clearing board
    public void clear() {
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].setState(0);
            }
        this.repaint();
    }

    private void initialize(int length, int height) {
        points = new Point[length][height];

        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y)
                points[x][y] = new Point();

        initailizeNeighbors();
    }

/*    // load pattern
    public void loadPattern(Pattern shape) {

        String[] lines = shape.getPattern();
        int x0=35;
        int y0=25;

        for (int i = 0; i < lines.length; ++i) {
            for (int j = 0; j < lines[i].length(); ++j) {
                if (((x0 + j) < points.length) && ((x0 + j) > 0) && ((y0 + i) < points[(x0 + j)].length) && ((y0 + i) > 0))
                    if (lines[i].charAt(j) == '#')
                        points[x0 + j][y0 + i].setState(1);
                    else
                        points[x0 + j][y0 + i].setState(0);
            }
        }
        this.repaint();
    }*/

    //paint background and separators between cells
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(Color.GRAY);
        drawNetting(g, size);
    }

    // draws the background netting
    private void drawNetting(Graphics g, int gridSpace) {
        Insets insets = getInsets();
        int firstX = insets.left;
        int firstY = insets.top;
        int lastX = this.getWidth() - insets.right;
        int lastY = this.getHeight() - insets.bottom;

        int x = firstX;
        while (x < lastX) {
            g.drawLine(x, firstY, x, lastY);
            x += gridSpace;
        }

        int y = firstY;
        while (y < lastY) {
            g.drawLine(firstX, y, lastX, y);
            y += gridSpace;
        }

        for (x = 0; x < points.length; ++x) {
            for (y = 0; y < points[x].length; ++y) {
                if (points[x][y].getState() != 0) {
                    switch (points[x][y].getState()) {
                        case 1:
                            g.setColor(new Color(0xE65100));
                            break;
                        case 2:
                            g.setColor(new Color(0x00ff00));
                            break;
                        case 3:
                            g.setColor(new Color(0xff0000));
                            break;
                        case 4:
                            g.setColor(new Color(0x000000));
                            break;
                        case 5:
                            g.setColor(new Color(0x444444));
                            break;
                        case 6:
                            g.setColor(new Color(0xffffff));
                            break;
                    }
                    g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
                }
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            points[x][y].clicked();
            this.repaint();
        }
    }

    public void componentResized(ComponentEvent e) {
        int dlugosc = (this.getWidth() / size) + 1;
        int wysokosc = (this.getHeight() / size) + 1;
        initialize(dlugosc, wysokosc);
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            points[x][y].setState(1);
            this.repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }
    private void initailizeNeighbors(){
        //inicjalizacja sasiadow w naroznikach

        //inicjalizacja sasiadow dla komorek brzegowych poziomych
        for(int i=0; i< points[0].length; i++ ){
            int max = points.length-1;
            int min = 0;
            if(i!=0){
                points[min][i].getNeighbors().add(points[min + 1][i - 1]);
                points[min][i].getNeighbors().add(points[min][i-1]);
                points[max][i].getNeighbors().add(points[max-1][i-1]);
                points[max][i].getNeighbors().add(points[max][i-1]);
            }
            if(i != points[0].length-1){
                points[min][i].getNeighbors().add(points[min+1][i+1]);
                points[min][i].getNeighbors().add(points[min][i+1]);
                points[max][i].getNeighbors().add(points[max-1][i+1]);
                points[max][i].getNeighbors().add(points[max][i+1]);
            }
            points[min][i].getNeighbors().add(points[min+1][i]);
            points[max][i].getNeighbors().add(points[max-1][i]);

        }

        //inicjalizacja sasiadow dla komorek brzegowych pionowych
        for(int i=0; i< points.length; i++ ){
            int max = points[0].length-1;
            int min = 0;
            if(i!=0){
                points[i][min].getNeighbors().add(points[i-1][min+1]);
                points[i][min].getNeighbors().add(points[i-1][min]);
                points[i][max].getNeighbors().add(points[i-1][max]);
                points[i][max].getNeighbors().add(points[i-1][max-1]);
            }
            if(i!=points.length-1){
                points[i][min].getNeighbors().add(points[i+1][min+1]);
                points[i][min].getNeighbors().add(points[i+1][min]);
                points[i][max].getNeighbors().add(points[i+1][max-1]);
                points[i][max].getNeighbors().add(points[i+1][max]);

            }
            points[i][max].getNeighbors().add(points[i][max-1]);
            points[i][min].getNeighbors().add(points[i][min+1]);
        }
        //inicjalizacja sasiadow dla kazdej komorki, sasiedztwo moora
        for (int x = 1; x < points.length-1; ++x) {
            for (int y = 1; y < points[x].length-1; ++y) {
                points[x][y].getNeighbors().add(points[x-1][y-1]);
                points[x][y].getNeighbors().add(points[x-1][y]);
                points[x][y].getNeighbors().add(points[x-1][y+1]);
                points[x][y].getNeighbors().add(points[x][y-1]);
                points[x][y].getNeighbors().add(points[x][y+1]);
                points[x][y].getNeighbors().add(points[x+1][y-1]);
                points[x][y].getNeighbors().add(points[x+1][y]);
                points[x][y].getNeighbors().add(points[x+1][y+1]);
            }
        }
    }
}
