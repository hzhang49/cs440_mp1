package mp1;

import java.util.Comparator;
import java.util.TreeSet;

import mp1.A_star.MazeObject;
import mp1.A_star.MyComp;
import mp1.read_maze.info;

public class greedy_best_first {
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
		//int tdist; //total distance = cost+gdist; 
		int  visited; 
		MazeObject (int a, int b){
			x=a; 
			y=b; 
			//level = 0; 
			cost = 0; 	
			//tdist = cost+gdist; 
			visited=0; 
		}	
	    
	}	
	class MyComp implements Comparator<MazeObject> //compare function 
	{
		@Override
		public int compare (MazeObject e1, MazeObject e2)
		{
			if (e1.x==e2.x && e1.y==e2.y)
			{
				return 0; 
			}
					
			if (e1.gdist>=e2.gdist)
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
		System.out.println("greedy-best_first search for 1.1");
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
		//temp.tdist = temp.gdist; //initial state total distance 
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
			if ((x-1==Gx&&y==Gy) || (x+1==Gx&&y==Gy)||  (x==Gx&&y-1==Gy) ||(x==Gx&&y+1==Gy) )
			{
				goal = front; 
				System.out.println("Found goal"); 
				int cost = goal.cost+1; 
				System.out.println("Steps to reach goal is " + cost);
				System.out.println("node expanded " + expand);
				while (goal != null)
					{
						//System.out.println("current x, y axis are "+goal.x+" "+goal.y);
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
				//temp.tdist = temp.cost+temp.gdist; 
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
				//temp.tdist = temp.cost+temp.gdist; 
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
				//temp.tdist = temp.cost+temp.gdist; 
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
				//temp.tdist = temp.cost+temp.gdist; 
				frontier.add(temp); 
				expand++;
			}
			
		} while (in.maze[x][y]!=3); 
		 
		
	}
	

}
