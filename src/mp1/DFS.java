package mp1;

import java.util.Stack;

import mp1.read_maze.info;


public class DFS {
	private class node{
		int x;
		int y;
		int cost;
		node prev;
		//int[][] path;
		node(){}
		node(int a, int b, int co/*, int[][] maze*/){
			x = a;
			y = b;
			cost = co;
			//path = maze.clone();
		}
		node(int a, int b, int co, node parent/*, int[][] maze*/){
			x = a;
			y = b;
			cost = co;
			prev = parent;
			//path = maze.clone();
		}
	}
	/* maze:
	 * 2d-int array represent the maze structure
	 * 0 stands for empty way
	 * 1 stands for wall
	 * 2 stands for the starting position
	 * 3 stands for the goal
	 * 4 stands for path
	 * 
	 * info:	int[0][0] and int[0][1] stores the x, y coordinates of start point
	 * 			int[1][0] and int[1][1] stores the x, y coordinates of goal point*/
	public void basic(info in){	
		Stack<node> q = new Stack<node>();
		System.out.println("DFS for 1.1");
		node first = new node(in.start_x, in.start_y, 0);
		first.prev = null;
		q.add(first);
		int row = in.maze.length;
		int col = in.maze[0].length;
		int node_expended = 0;
		//System.out.println("row: "+ Integer.toString(row) + " col: "+ Integer.toString(col) + " Starting at: " + Integer.toString(info[0][0])+ ","+ Integer.toString(info[0][1]));
		boolean[][] visit = new boolean[row][col];
		visit[in.start_x][in.start_y] = true;
		node dest = new node();
	
		
		while(!q.isEmpty()){
			node temp = q.pop();
			if(temp.x == in.d.get(0).x && temp.y == in.d.get(0).y ){
				dest = temp;
				System.out.println("found dest with cost: " + temp.cost);
				break;
			}
			if(temp.x+1 < row ){
				if(!visit[temp.x+1][temp.y] && in.maze[temp.x+1][temp.y] != 1){
					q.push(new node(temp.x+1, temp.y, temp.cost+1, temp));
					node_expended++;
					visit[temp.x+1][temp.y] = true;
					//System.out.print("queuing");
				}
			}
			if(temp.y-1 >= 0){
				if(!visit[temp.x][temp.y-1] && in.maze[temp.x][temp.y-1] != 1){
					q.push(new node(temp.x, temp.y-1, temp.cost+1, temp));
					node_expended++;
					visit[temp.x][temp.y-1] = true;
					//System.out.print("queuing");
				}
			}
			if(temp.y+1 < col){
				if(!visit[temp.x][temp.y+1] && in.maze[temp.x][temp.y+1] != 1){
					q.push(new node(temp.x, temp.y+1, temp.cost+1, temp));
					node_expended++;
					visit[temp.x][temp.y+1] = true;
					//System.out.print("queuing");
				}
			}
			if(temp.x-1 >= 0 ){
				if(!visit[temp.x-1][temp.y]&& in.maze[temp.x-1][temp.y] != 1){
					q.push(new node(temp.x-1, temp.y, temp.cost+1, temp));
					node_expended++;
					visit[temp.x-1][temp.y] = true;
					//System.out.print("queuing");
				}
			}
		}
		dest = dest.prev;
		while(dest != null){
			in.maze[dest.x][dest.y] = 4;
			dest = dest.prev;
		}
		in.maze[in.start_x][in.start_y] = 2;
		System.out.println("Node expanded: "+Integer.toString(node_expended));
		
	}
	
	public static void penalizing_turns(int[][] maze){
		
	}
	
	public static void with_ghost(int[][] maze){
		
	}
}
