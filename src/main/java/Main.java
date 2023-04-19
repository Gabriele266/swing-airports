import gui.MainWindow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //MainWindow mainWindow = new MainWindow();
        //mainWindow.setVisible(true);

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            threads.add(new Thread() {
                @Override
                public void run() {
                    System.out.println("Sono un thread");

                    for (int i = 0; i < 30; i++)
                        System.out.println(i);
                    super.run();
                }
            });
        }

        threads.forEach(it -> it.start());
//      NON ha senso
//        try {
//            for (int i = 0; i < 2; i ++)
//                threads.get(i).join();
//
//        } catch (InterruptedException e) {
//        }
//        System.out.println("Ciao");
//
//        try {
//            for (int i = 2; i < 4; i ++)
//                threads.get(i).join();
//
//        } catch (InterruptedException e) {
//        }
    }
}
