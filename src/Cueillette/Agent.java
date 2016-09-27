package Cueillette;

import java.util.Random;

public class Agent {

	protected int x,y; 
	protected int monde[][];
	
	public Agent(int x,int y,int[][] tab){
		this.x=x;
		this.y=y;
		monde=tab;
	}
	
	public void deplacementLevy(){
		Random rand=new Random();
		if(rand.nextFloat()>0.5){
			x+=rand.nextInt(30)-15;//-15;15
			y+=rand.nextInt(30)-15;
		}else{
			x+=rand.nextInt(10)-5;//-3;3
			y+=rand.nextInt(10)-5;
		}
		if(x>=monde.length){
			x=0;
		}
		if(y>=monde.length){
			y=0;
		}
		if(x<0){
			x=monde.length-1;
		}
		if(y<0){
			y=monde.length-1;
		}
	}

	public void deplacementAlea() {
		Random rand=new Random();
		int bouge=rand.nextInt(3);
		if(bouge==0){
			x+=1;
		}else if(bouge==1){
			x-=1;
		}else if(bouge==2){
			y+=1;
		}else if(bouge==3){
			y-=1;
		}
		if(x>=monde.length){
			x=0;
		}
		if(y>=monde.length){
			y=0;
		}
		if(x<0){
			x=monde.length-1;
		}
		if(y<0){
			y=monde.length-1;
		}
		
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
