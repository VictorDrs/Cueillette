package Cueillette;

import java.util.Random;

public class Agent {

	protected int x,y;//Coord de l'agent
	protected int monde[][];//Utile pour le deplacement (dimension du monde)
	protected int destX,destY;
	
	public Agent(int x,int y,int[][] tab){
		this.x=x;
		this.y=y;
		destX=x;
		destY=y;
		monde=tab;
	}

	public void deplacementLevy(){
		if(x==destX && y==destY){
			Random rand=new Random();
			if(rand.nextFloat()>0.99){
				destX=rand.nextInt(monde.length-1)+1;
				destY=rand.nextInt(monde.length-1)+1;
			}else{
				deplacementAlea();
				destX=x;
				destY=y;
			}
		}
		if(x>destX){
			deplacement(1);
		}else if(x<destX){
			deplacement(0);
		}else if(y>destY){
			deplacement(3);
		}else if(y<destY){
			deplacement(2);
		}
		verifDim();
		
	}
	public void deplacement(int dir){
		if(dir==0){
			x+=1;
		}else if(dir==1){
			x-=1;
		}else if(dir==2){
			y+=1;
		}else if(dir==3){
			y-=1;
		}
		verifDim();
	}
	public void verifDim(){
		if(x>=monde.length){
			x=monde.length-x;
		}
		if(y>=monde.length){
			y=monde.length-y;
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
		int bouge=rand.nextInt(4);
		if(bouge==0){
			x+=1;
		}else if(bouge==1){
			x-=1;
		}else if(bouge==2){
			y+=1;
		}else if(bouge==3){
			y-=1;
		}
		verifDim();
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
}
