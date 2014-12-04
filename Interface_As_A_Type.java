

class Ball extends Rubber implements Jumping, Rolling, Squeezing {
   public void jump(){}
   public void roll(){}
   public void squeeze(){}
}

Ball b = new Ball();
Jumping j = new Ball();
j.jump();

