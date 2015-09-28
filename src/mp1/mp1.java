package mp1;

import mp1.A_star.MazeObject;
import mp1.A_star.coor;
import mp1.read_maze.info;

import java.awt.EventQueue;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;

public class mp1 extends JFrame {
	public static info m_info;
	public static info b_info;
	public static info o_info;
	public static info tiny_s;
	public static info small_s;
	public static info medium_s;
	public static info ghost_s;
	public static info ghost_m;
	public static info ghost_b;
	public static info search_t;
	public static info search_s;
	public static info search_m;
	public static info dots_m;
	public static info dots_b;
	public static Stack<MazeObject> step;
     
	public mp1() {
		initUI();
	}

	private void initUI() {

		add(new Board());
		setTitle("Pacman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(360, 345);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void print_maze(int[][] maze) {
		int row = maze.length;
		int col = maze[0].length;
		for (int i = 0; i < row; i++) {
			String s = "";
			for (int j = 0; j < col; j++) {
				if (maze[i][j] == 0) {
					s += " ";
				} else if (maze[i][j] == 1) {
					s += "%";
				} else if (maze[i][j] == 2) {
					s += "P";
				} else if (maze[i][j] == 3 || maze[i][j] == 4) {
					s += ".";
				} else if (maze[i][j] == 5) {
					s += " ";
				} else if(maze[i][j] < 0){
					if(maze[i][j] > -10){
						s += String.valueOf((char) ('0'-maze[i][j]));
					}else if(maze[i][j] > -36){
						
						s += (char) ('a'-maze[i][j]-10);
					}else{
						s += (char) ('A'-maze[i][j]-36);
					}
					
				}
			}
			System.out.println(s);
		}
	}

	public static void bfs_basic() {
		BFS test = new BFS();
		test.basic(m_info);
		System.out.println("mediumMaze: ");
		print_maze(m_info.maze);
		System.out.println();

		BFS test2 = new BFS();
		test2.basic(b_info);
		System.out.println("bigMaze: ");
		print_maze(b_info.maze);
		System.out.println();

		BFS test3 = new BFS();
		test3.basic(o_info);
		System.out.println("openMaze: ");
		print_maze(o_info.maze);
		System.out.println();

	}

	public static void dfs_basic() {
		DFS test = new DFS();
		test.basic(m_info);
		System.out.println("mediumMaze: ");
		print_maze(m_info.maze);
		System.out.println();

		DFS test2 = new DFS();
		test2.basic(b_info);
		System.out.println("bigMaze: ");
		print_maze(b_info.maze);
		System.out.println();

		DFS test3 = new DFS();
		test3.basic(o_info);
		System.out.println("openMaze: ");
		print_maze(o_info.maze);
		System.out.println();

	}

	public static void greedy_best_first() {
		greedy_best_first test = new greedy_best_first();
		test.basic(m_info);
		System.out.println("mediumMaze: ");
		print_maze(m_info.maze);
		System.out.println();

		greedy_best_first test2 = new greedy_best_first();
		test2.basic(b_info);
		System.out.println("bigMaze: ");
		print_maze(b_info.maze);
		System.out.println();

		greedy_best_first test3 = new greedy_best_first();
		test3.basic(o_info);
		System.out.println("openMaze: ");
		print_maze(o_info.maze);
		System.out.println();

	}

	public static void A_star_basic() {
		A_star test = new A_star();
		test.basic(m_info);
		System.out.println("mediumMaze: ");
		print_maze(m_info.maze);
		System.out.println();

		A_star test2 = new A_star();
		test2.basic(b_info);
		System.out.println("bigMaze: ");
		print_maze(b_info.maze);
		System.out.println();

		A_star test3 = new A_star();
		test3.basic(o_info);
		System.out.println("openMaze: ");
		print_maze(o_info.maze);
		System.out.println();

	}

	public static void main(String[] args) {
		/* read maze files */
		read_maze r = new read_maze();
		m_info = r.in("mediumMaze.txt", 23, 23);
		b_info = r.in("bigMaze.txt", 37, 37);
		o_info = r.in("openMaze.txt", 20, 37);
		tiny_s = r.in("tinySearch.txt", 8, 23);
		small_s = r.in("smallSearch.txt", 13, 30);
		medium_s = r.in("mediumSearch.txt", 13, 49);
		ghost_s = r.in("smallGhost.txt", 10, 22);
		ghost_m = r.in("mediumGhost.txt", 15, 15);
		ghost_b = r.in("bigGhost.txt", 37, 37);
		search_t = r.in("tinySearch.txt", 8, 23);
		search_s = r.in("smallSearch.txt", 13, 30);
		search_m = r.in("mediumSearch.txt", 13, 49);
		dots_m = r.in("mediumDots.txt", 8, 31);
		dots_b = r.in("bigDots.txt", 17, 28);
		// bfs_basic();
		// dfs_basic();
		// greedy_best_first();
		// A_star_basic();

//		A_star test = new A_star();
//		test.multi_dot(search_t);
//		print_maze(search_t.maze);
//		System.out.println();
		
		 A_star test = new A_star();
		 step = test.with_ghost(ghost_m);
		 print_maze(ghost_m.maze);
		 System.out.println();
		 
//		 EventQueue.invokeLater(new Runnable() {
//		
//		 @Override
//		 public void run() {
//		 mp1 ex = new mp1();
//		 ex.setVisible(true);
//		 }
//		 });
	}
}
