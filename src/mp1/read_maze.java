package mp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class read_maze {
	public class info{
		int start_x;
		int start_y;
		List<dest> d;
		int[][] maze;
		dest ghost;
		info(int m, int n){
			d = new ArrayList<dest>();
			maze = new int[m][n];
			ghost = new dest(0, 0);
		}
	}
	public class dest{
		int x;
		int y;
		dest(int a, int b){
			x = a;
			y = b;
		}
	}
	/*
	 * read maze file and transfer it to int[][] map and return start point and goal point
	 * 0 stands for empty way
	 * 1 stands for wall
	 * 2 stands for the starting position
	 * 3 stands for the goal*/
	public info in(String file, int m, int n){
		BufferedReader br;
		info ret=  new info(m, n);
		try {
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			int i = 0;
			while(line!= null){
				char[] in = line.toCharArray();
				for(int j = 0; j < in.length; j++){
					
					switch(in[j]){
					case '%':
						ret.maze[i][j] = 1;
						break;
					case ' ':
						ret.maze[i][j] = 0;
						break;
					case 'P':
						ret.maze[i][j] = 2;
						ret.start_x = i;
						ret.start_y = j;
						break;
					case '.':
						ret.maze[i][j] = 3;
						ret.d.add(new dest(i, j));
						break;
					case 'G':
						//System.out.println("found ghost start");
						ret.ghost.x = i;
						//System.out.println(i);
						ret.ghost.y = j;
						//System.out.println(j);
						break;
						
					default:
						break;
					}
					
				}
				i++;
				line = br.readLine();
			}
					
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;		
	}
}
