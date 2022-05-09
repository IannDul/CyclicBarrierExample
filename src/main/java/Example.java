import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Example {
    public static  void main(String[] args){
        System.out.println("Играем в русскую рулетку, если первое - победа, то я победил!!");
        System.out.println("YES or NO");
        Scanner scanner = new Scanner(System.in);
        while (true){
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("YES")){

                CyclicBarrier barrier = new CyclicBarrier(5, () -> {
                    System.out.println(System.lineSeparator() + "Спасибо большое!");
                });

                Thread thread1 = new Thread(new Task("победа", barrier));
                thread1.setPriority(Thread.MAX_PRIORITY);
                thread1.start();

                Thread thread2 = new Thread(new Task("хвххвхвхвхв", barrier));
                thread2.setPriority(Thread.MIN_PRIORITY);
                thread2.start();

                Thread thread3 = new Thread(new Task("хахахахха", barrier));
                thread3.setPriority(Thread.MIN_PRIORITY);
                thread3.start();

                Thread thread4 = new Thread(new Task("хахахахах", barrier));
                thread4.setPriority(Thread.MIN_PRIORITY);
                thread4.start();

                Thread thread5 = new Thread(new Task("ухуххухухух", barrier));
                thread5.setPriority(Thread.MIN_PRIORITY);
                thread5.start();

                break;

            }
            System.out.println("Подумай ещё");
        }



    }


}
class Task implements Runnable {
    String msg;
    CyclicBarrier barrier;
    public Task(String msg, CyclicBarrier barrier) {
        this.msg = msg;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(msg);
        try {
            this.barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

