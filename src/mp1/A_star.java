package mp1;
import java.util.*; 
import java.lang.Math;

import mp1.read_maze.dest;
import mp1.read_maze.info;



public class A_star {
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
	public class MazeObject{
		int x; 
		int y; 
		//int level; 
		MazeObject parent; 
		int cost; //cost from start to this node, if this is unvisited, initialize this to 800	
		int gdist; //distance to goal state 
		int tdist; //total distance = cost+gdist; 
		int  visited; 
		int ghost_y;
		int ghost_dir;
		MazeObject (int a, int b){
			x=a; 
			y=b; 
			//level = 0; 
			cost = 0; 	
			//tdist = cost+gdist; 
			visited=0; 
			ghost_y = 0;
			ghost_dir = 1;		//initial 1 means ghost is moving left to right
			
		}	
	    
	}	
	public class coor{
		int x;
		int y;
		int q;
		coor(int a, int b){
			x = a;
			y = b;
			q = -1;
		}
	}
	class MyComp implements Comparator<MazeObject> //compare function 
	{
		@Override
		public int compare (MazeObject e1, MazeObject e2)
		{
			if (e1.x==e2.x && e1.y==e2.y)
			{
				return 1; 
			}
					
			if (e1.tdist>=e2.tdist)
			{
				return 1; 				
			}
			else 
			{
				return -1; 
			}
		} 
		
	}
	public  void basic(info in){
		System.out.println("A* search for 1.1");
		int expand = 0;
		int Sx=in.start_x, Sy=in.start_y; 
		//System.out.println("Starting at: " + String.valueOf(Sx)+", "+String.valueOf(Sy));
		int Gx=in.d.get(0).x, Gy=in.d.get(0).y; 
		//System.out.println("Goal at: " + String.valueOf(Gx)+", "+String.valueOf(Gy));
		int x=Sx, y=Sy; 
		TreeSet<MazeObject> frontier = new TreeSet<MazeObject>(new MyComp());
		MazeObject temp = new MazeObject(x, y); 

		temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
		temp.cost=0; 
		temp.tdist = temp.gdist; //initial state total distance 
		temp.parent = null; //first node
		
		frontier.add(temp); 
		expand++;
		MazeObject front, goal; //goal here indicates one step to goal
	
		do
		{	
			
			if (frontier.size()==0) 
				return;  
			front = frontier.first(); 			
			x=front.x; 
			y=front.y; //set x and y coordinates
			in.maze[front.x][front.y]=5; //paint this cell
			frontier.pollFirst(); //remove first element of the queue; 
			if ((x-1==Gx&&y==Gy) || (x+1==Gx&&y==Gy)||  (x==Gx&&y-1==Gy) ||(x==Gx&&y+1==Gy)  )
			{
				goal = front; 
				System.out.println("Found goal"); 
				int cost = goal.cost+1; 
				System.out.println("Steps to reach goal is " + cost);
				System.out.println("node expanded " + expand);
				while (goal != null)
					{
						
						in.maze[goal.x][goal.y]=4; 
						goal=goal.parent; 			
					}
				in.maze[Sx][Sy]=2;
				return; 
			}
			
			
			if (in.maze[x-1][y]==0)
			{
				temp = new MazeObject(x, y); 
				temp.x=x-1;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
		
			}
				
			if (in.maze[x][y-1]==0)
			{
				temp = new MazeObject(x, y); 				
				temp.x=x;
				temp.y=y-1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
						
			}
			
			if (in.maze[x+1][y]==0)
			{
				
				temp = new MazeObject(x, y); 
				temp.x=x+1;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
				
			}
				
			if (in.maze[x][y+1]==0)
			{
				temp = new MazeObject(x, y); 
				temp.x=x;
				temp.y=y+1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 		
				expand++;
			}
			
		} while (in.maze[x][y]!=3); 
		 
		
	}
	
	public void penalizing_turns(int[][] maze){
		
	}
	
	public Stack<MazeObject> with_ghost(info in){
		System.out.println("A* search for 1.3");
		Stack<MazeObject> ret = new Stack<MazeObject>();
		int Sx=in.start_x, Sy=in.start_y; 
		
		int Gx=in.d.get(0).x, Gy=in.d.get(0).y; 
	
		int x=Sx, y=Sy; 
		TreeSet<MazeObject> frontier = new TreeSet<MazeObject>(new MyComp());
		MazeObject temp = new MazeObject(x, y); 

		temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
		temp.cost=0; 
		temp.tdist = temp.gdist; //initial state total distance 
		temp.parent = null; //first node
		temp.ghost_y = in.ghost.y;
		temp.ghost_dir = 1;
		frontier.add(temp); 
		MazeObject front, goal; //goal here indicates one step to goal
	
		do
		{	
			
			if (frontier.size()==0) 
				return ret;  
			front = frontier.first(); 			
			x=front.x; 
			y=front.y; //set x and y coordinates
			in.maze[front.x][front.y]=5; //paint this cell
			int ghost_y = front.ghost_y;
			int ghost_dir = front.ghost_dir;
			frontier.pollFirst(); //remove first element of the queue; 
			if ((x-1==Gx&&y==Gy) || (x+1==Gx&&y==Gy)||  (x==Gx&&y-1==Gy) ||(x==Gx&&y+1==Gy) )
			{
				goal = front; 
				System.out.println("Found goal"); 
				int cost = goal.cost+1; 
				System.out.println("Steps to reach goal is " + cost);
				while (goal != null)
					{
						//System.out.println("current x, y axis are "+goal.x+" "+goal.y);
						in.maze[goal.x][goal.y]=4; 
						ret.push(goal);
						goal=goal.parent; 			
					}
				ret.push(new MazeObject(in.start_x, in.start_y));
				in.maze[Sx][Sy]=2;
				return ret; 
			}
			
			
			int[] ghost_next = g_next(in.maze, in.ghost.x, ghost_y, ghost_dir);
			if (in.maze[x-1][y]==0 && ghost_next[1]!=y)
			{
				temp = new MazeObject(x, y); 
				temp.x=x-1;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 
		
			}else if(in.maze[x-1][y]==0 && ghost_next[1]==y){
				//System.out.println("Detected ghost, stop");
				temp = new MazeObject(x, y); 
				temp.x=x;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 				
			}
				
			if (in.maze[x][y-1]==0 && ghost_next[1]!=y-1)
			{
				temp = new MazeObject(x, y); 				
				temp.x=x;
				temp.y=y-1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 
						
			}else if(in.maze[x][y-1]==0 && ghost_next[1]==y-1){
				//System.out.println("Detected ghost, stop");
				temp = new MazeObject(x, y); 				
				temp.x=x;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 
			}
			
			if (in.maze[x+1][y]==0 && ghost_next[1]!=y)
			{
				
				temp = new MazeObject(x, y); 
				temp.x=x+1;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 
				
			}else if(in.maze[x+1][y]==0 && ghost_next[1]==y){
				//System.out.println("Detected ghost, stop");
				temp = new MazeObject(x, y); 
				temp.x=x;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 
			}
				
			if (in.maze[x][y+1]==0 && ghost_next[1]!=y+1)
			{
				temp = new MazeObject(x, y); 
				temp.x=x;
				temp.y=y+1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 				
			}else if(in.maze[x][y+1]==0 && ghost_next[1]==y+1){
				//System.out.println("Detected ghost, stop");
				temp = new MazeObject(x, y); 
				temp.x=x;
				temp.y=1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				temp.ghost_y = ghost_next[1];
				temp.ghost_dir = ghost_next[0];
				frontier.add(temp); 
			}
			
		} while (in.maze[x][y]!=3);
		return ret; 
		
	}
	
	public static int[] g_next(int[][] maze, int curr_x, int curr_y, int dir){
		int[] ret = new int[2];
		if(dir == 1){
			if(maze[curr_x][curr_y+1] != 1){
				ret[0] = 1;
				ret[1] = curr_y+1;
				return ret;
			}else{
				ret[0] = 0;
				ret[1] = curr_y-1;
				return ret;
			}			
		}else{
			if(maze[curr_x][curr_y-1] != 1){
				ret[0] = 0;
				ret[1] = curr_y-1;
				return ret;
			}else{
				ret[0] = 1;
				ret[1] = curr_y+1;
				return ret;
			}			
		}

	}
	public  int[] get_cost(int[][] maze, coor d, coor g){
		int[] ret = new int[2];
		int expand = 0;
		int Sx=d.x, Sy=d.y; 
		//System.out.println("Starting at: " + String.valueOf(Sx)+", "+String.valueOf(Sy));
		int Gx=g.x, Gy=g.y; 
		//System.out.println("Goal at: " + String.valueOf(Gx)+", "+String.valueOf(Gy));
		int x=Sx, y=Sy; 
		TreeSet<MazeObject> frontier = new TreeSet<MazeObject>(new MyComp());
		MazeObject temp = new MazeObject(x, y); 

		temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
		temp.cost=0; 
		temp.tdist = temp.gdist; //initial state total distance 
		temp.parent = null; //first node
		
		frontier.add(temp); 
		expand++;
		MazeObject front, goal; //goal here indicates one step to goal
	
		do
		{	
			
			if (frontier.size()==0) 
				return ret;  
			front = frontier.first(); 			
			x=front.x; 
			y=front.y; //set x and y coordinates
			maze[front.x][front.y]=5; //paint this cell
			frontier.pollFirst(); //remove first element of the queue; 
			if ((x-1==Gx&&y==Gy) || (x+1==Gx&&y==Gy)||  (x==Gx&&y-1==Gy) ||(x==Gx&&y+1==Gy)  )
			{
				goal = front; 
				//System.out.println("Found goal"); 
				int cost = goal.cost+1; 
				//System.out.println("Steps to reach goal is " + cost);
				//System.out.println("node expanded " + expand);

				maze[Sx][Sy]=2;
				ret[0] = cost;
				ret[1] = expand;
				return ret; 
			}
			
			
			if (maze[x-1][y]==0)
			{
				temp = new MazeObject(x, y); 
				temp.x=x-1;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
		
			}
				
			if (maze[x][y-1]==0)
			{
				temp = new MazeObject(x, y); 				
				temp.x=x;
				temp.y=y-1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
						
			}
			
			if (maze[x+1][y]==0)
			{
				
				temp = new MazeObject(x, y); 
				temp.x=x+1;
				temp.y=y; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
				
			}
				
			if (maze[x][y+1]==0)
			{
				temp = new MazeObject(x, y); 
				temp.x=x;
				temp.y=y+1; 
				temp.parent = front; 
				temp.cost=front.cost+1; 
				temp.gdist = Math.abs(Gx-temp.x)+Math.abs(Gy-temp.y); 
				temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 		
				expand++;
			}
			
		} while (maze[x][y]!=3);
		return ret; 
		 
		
	}
	public void multi_dot(info in){
		int[][] orig = new int[in.maze.length][in.maze[0].length];
		for(int i = 0; i < in.maze.length; i++){
			for(int j = 0; j < in.maze[0].length; j++){
				if(in.maze[i][j] != 3 && in.maze[i][j] != 2){
					orig[i][j] = in.maze[i][j];
				}else{
					orig[i][j] = 0;
				}
			}
		}
		
		List<coor> goal = new ArrayList<coor>();
		for(int i = 0; i < in.d.size(); i++){
			goal.add(new coor(in.d.get(i).x, in.d.get(i).y));
		}
		int total_cost = 0;
		int total_expand = 0;
		int total = goal.size();
		coor start = new coor(in.start_x, in.start_y);
		for(int k = 1; k < total+1; k++){
			int cost = 999;
			coor best_goal = null;
			int expand = 0;
			for(int i = 0; i < goal.size(); i++){
				coor curr = goal.get(i);
				if(curr.q < 0){
					int[][] maze = new int[orig.length][orig[0].length];
					for(int m = 0; m < orig.length; m++){
						for(int n = 0; n < orig[0].length; n++){
							maze[m][n] = orig[m][n];
						}
					}
					maze[curr.x][curr.y] = 3;
					maze[start.x][start.y] = 2;
					//System.out.println("start: "+ String.valueOf(start.x)+ ", "+String.valueOf(start.y)+" goal: "+String.valueOf(curr.x)+" , "+String.valueOf(curr.y));
					int[] ret = get_cost(maze, start, curr);
					int curr_cost = ret[0];
					//System.out.println("with cost: "+ String.valueOf(curr_cost));
					
					if(curr_cost < cost ){
						cost = curr_cost;
						expand = ret[1];
						best_goal = curr;
					}
				}
				
			}
			//System.out.println("found best goal with cost: "+ String.valueOf(cost));
			total_cost += cost;
			total_expand += expand;
			best_goal.q = k;
			start = best_goal;
		}
		System.out.println("Total cost is: "+ String.valueOf(total_cost));
		System.out.println("Total expanded node is: "+ String.valueOf(total_expand));
		for(int i = 0; i < goal.size(); i++){
			//System.out.println("this goal is step: " + String.valueOf(goal.get(i).q));
			in.maze[goal.get(i).x][goal.get(i).y] = -goal.get(i).q;
		}
		

	}
}
