package arprast.qiyosq.test;

public class Singleton {
    private volatile static Singleton instance;
    public static String str;
    private Singleton() {}

    static Singleton getSingleInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}

//still retains all the requirements from the first, and is just more concise. (technically speaking it's a form of lazy initialization, since it eagerly initializes on demand)
//class Singleton {
//    private Singleton() {}
//
//    private static class SingletonHolder {
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    public static Singleton getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//}
