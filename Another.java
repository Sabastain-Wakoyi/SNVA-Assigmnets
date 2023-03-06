
// this is the asynchronous and non-blocking
class DoAnotherThing extends Thread {

    @Override
    public void run() {
        for (int u = 0; u < 10; u++) {
            try {
                System.out.println("U Working .... " + u);
                Thread.sleep(10);
            } catch (Exception ee) {
                System.out.println(ee);
            }
        }
    }
}

class DoNextThing extends Thread{
    @Override
    public void run(){
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


public class Another{

    public static void main(String[] args) {
        DoAnotherThing doAnotherThing = new DoAnotherThing();
        DoNextThing doNextThing = new DoNextThing();
        doAnotherThing.start();
       doNextThing.start();


    }

}