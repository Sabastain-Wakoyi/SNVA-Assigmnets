

    class doSomeThing{


        public void doOneThing(){
            for (int u = 0 ; u < 10 ; u ++){
                try {
                    System.out.println("U Working .... " + u);
                    Thread.sleep(10);
                }
                catch (Exception ee){
                    System.out.println(ee );
                }
            }
        }
        public void doSecondThing(){
            for (int u = 0 ; u < 10 ; u ++){
                try {
                    System.out.println("U Do Some thhing  Working .... " + u);
                    Thread.sleep(10);
                }
                catch (Exception ee){
                    System.out.println(ee );
                }
            }
        }
    }

    public class WebfluxFunctionalApp{

        public static void main(String[] args) {
            doSomeThing doSomeThing = new doSomeThing();
            doSomeThing.doOneThing();
            doSomeThing.doSecondThing();

        }

    }