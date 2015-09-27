package mp1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import mp1.A_star.MazeObject;

public class Board extends JPanel implements ActionListener {

    private Dimension d;

    private  MazeObject a;
    private int ghost_x;
    private int ghost_y;
    private int ghost_dir;


    private Image ghost;
    private Image pacman1;
    private Image wall;
    private Image goal;

    private Timer timer;

    public Board() {

        ghost = new ImageIcon("ghost.png").getImage();
        pacman1 = new ImageIcon("pacman.png").getImage();
        wall = new ImageIcon("wall.png").getImage();
        goal = new ImageIcon("goal.png").getImage();
        
        d = new Dimension(460, 220);
        ghost_x = mp1.ghost_s.ghost.x;
        ghost_y = mp1.ghost_s.ghost.y;

        
        timer = new Timer(1000, this);
        timer.start();
        
        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);
    }


    @Override
    public void addNotify() {
        super.addNotify();

    }



    private void moveGhosts(Graphics2D g2d) {
    	if(ghost_dir == 1){
    		if(mp1.ghost_s.maze[ghost_x][ghost_y+1] != 1){
    			ghost_y++;
    		}else{
    			ghost_y--;
    			ghost_dir = 0;
    		}
    		
    	}else{
    		if(mp1.ghost_s.maze[ghost_x][ghost_y-1] != 1){
    			ghost_y--;
    		}else{
    			ghost_y++;
    			ghost_dir = 1;
    		}    		
    	}
    	g2d.drawImage(ghost, ghost_y*20, ghost_x*20, this);
        
    }


    private void movePacman(Graphics2D g2d) {
    	if(!mp1.step.isEmpty()){
    		a = mp1.step.pop();
    		g2d.drawImage(pacman1, a.y*20, a.x*20, this);
    	}else{
    		g2d.drawImage(pacman1, mp1.ghost_s.d.get(0).y*20, mp1.ghost_s.d.get(0).x*20, this);
    	}

    }


    private void drawMaze(Graphics2D g2d) {
    	for(int i = 0; i < 10; i++){
    		for(int j = 0; j < 22; j++){
    			if(mp1.ghost_s.maze[i][j] == 1){
    				g2d.drawImage(wall,j*20 ,i*20 , this);
    			}else if(mp1.ghost_s.maze[i][j] == 3){
    				g2d.drawImage(goal,j*20 ,i*20 , this);
    			}
    		}
    	}
       
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);

        movePacman(g2d);
        moveGhosts(g2d);


    }

    

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}
