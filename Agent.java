package Cueillette;

import java.util.Random;

public class Agent {

	protected int x,y; 
	
	public Agent(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void deplacementLevy(){
		Random rand=new Random();
		if(rand.nextFloat()>0.5){
			x+=rand.nextInt();//-15;15
			y+=rand.nextInt();
		}else{
			x+=rand.nextInt();//-3;3
			y+=rand.nextInt();
		}
	}
}
