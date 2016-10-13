package Cueillette;

import java.util.Random;

public class Agent {

	protected int x,y;//Coord de l'agent
	protected int monde[][];//Utile pour le deplacement (dimension du monde)
	protected int destX,destY;
	protected int horsX=0,horsY=0;
	
	public Agent(int x,int y,int[][] tab){
		this.x=x;
		this.y=y;
		destX=x;
		destY=y;
		monde=tab;
	}

	public void deplacementLevy(){
		Random rand=new Random();
		
		int n = 100;
		double alpha = 1.5;
		double somme = 0;
		
		for(int i=1;i<=n;i++){
			somme += (rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),alpha));
		}
		somme = somme/Math.pow(n,alpha);
		
		angle(somme);
/*		
		destX+= (int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),2))%monde.length);		
		destY+=(int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),2))%monde.length);

		if(destX<0) destX+=(monde.length-1);
		else if(destX>monde.length-1) destX-=(monde.length-1);

		if(destY<0) destY+=(monde.length-1);
		else if(destY>monde.length-1) destY-=(monde.length-1);

		x=destX;
		y=destY;
		
		System.out.println("x: "+x+" - y: "+y);
		
		verifDim();
*/		
		/*
		if(x==destX && y==destY){
			Random rand=new Random();
			destX+= (int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),1.5)) % monde.length);		
			destY+=(int) Math.round((rand.nextGaussian()/ Math.pow(Math.abs(rand.nextGaussian()),1.5)) % monde.length);	
			if(destX<0) horsX=destX + (monde.length-1);
			else if(destX>monde.length-1) horsX=destX - (monde.length-1);
			if(destY<0) horsY=destY + (monde.length-1);
			else if(destY>monde.length-1) horsY=destY - (monde.length-1);
			System.out.println("destX: "+destX+" - destY: "+ destY);
		}
		if(horsX!=0){
			destX=horsX;
			horsX=0;
		}
		x=destX;
		
		if(horsY!=0){
			destY=horsY;
			horsY=0;
		}
		y=destY;
		

		if(horsX!=0 && x-1<0){			
			deplacement(1);
			destX=horsX;
			horsX=0;
		}else if(horsX!=0 && monde.length-1<x+1){				
			deplacement(0);
			destX=horsX;
			horsX=0;
		}else if(horsY!=0 && y-1<0){
			deplacement(3);
			destY=horsY;
			horsY=0;
		}else if(horsY!=0 && monde.length-1<y+1){				
			deplacement(2);
			destY=horsY;
			horsY=0;
		}

		else if(x>destX){
			deplacement(1);
		}else if(x<destX){
			deplacement(0);
		}else if(y>destY){
			deplacement(3);
		}else if(y<destY){
			deplacement(2);
		}
		verifDim();
*/
	}
	public void angle(double dist){
		dist=(Math.abs(dist)%monde.length);
		if((int)dist==0){
			dist++;
		}
		Random rand=new Random();
		int x=(int)dist;
		double reste=dist - x;
		if(rand.nextFloat()<reste){
			x++;
		}
		int r=rand.nextInt(4);
		boolean rb=rand.nextBoolean();
		for(int i=0; i<x;i++){
			if(r==0){
				if(rb){
					this.x++;
				}else{
					this.y++;
				}
			}else if(r==1){
				if(rb){
					this.x--;
				}else{
					this.y++;
				}
			}else if(r==2){
				if(rb){
					this.x--;
				}else{
					this.y--;
				}
			}else if(r==3){
				if(rb){
					this.x++;
				}else{
					this.y--;
				}
			}
			rb=rand.nextBoolean();
		}
		verifDim();
		System.out.println("x : "+x+" y : "+y+" Dist : "+dist);
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
			x=x-monde.length;
		}
		if(y>=monde.length){
			y=y-monde.length;
		}
		if(x<0){
			x=monde.length+x;
		}
		if(y<0){
			y=monde.length+y;
		}
	}
	public void deplacementAlea() {
		if(x==destX && y==destY){
			Random rand=new Random();
			destX+= (int) Math.round((rand.nextGaussian()));		
			destY+=(int) Math.round((rand.nextGaussian()));	
			if(destX<0) horsX=destX + (monde.length-1);
			else if(destX>monde.length-1) horsX=destX - (monde.length-1);
			if(destY<0) horsY=destY + (monde.length-1);
			else if(destY>monde.length-1) horsY=destY - (monde.length-1);
			System.out.println("destX: "+destX+" - destY: "+ destY);
		}
		if(horsX!=0){
			destX=horsX;
			horsX=0;
		}
		x=destX;
		
		if(horsY!=0){
			destY=horsY;
			horsY=0;
		}
		y=destY;
		
		/*Random rand=new Random();
		int bouge=rand.nextInt(4);
		if(bouge==0){
			x+=1;
		}else if(bouge==1){
			x-=1;
		}else if(bouge==2){
			y+=1;
		}else if(bouge==3){
			y-=1;
		}*/
		verifDim();
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
}
