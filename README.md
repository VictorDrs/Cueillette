# Cueillette




Work in progress




while(!fin){
  if(random(1)>p){
    coord_agent_x+random(-50,50)
    coord_agent_y+random(-50,50)
  }else{
    coord_agent_x+random(-5,5)
    coord_agent_y+random(-5,5)
  }
  if(trouve_nourriture()){
    p=p+q
  }else{
    p=p-q
  }
}
