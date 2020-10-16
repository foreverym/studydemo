package club.banyuan;

public class UserFactory {

    public <T>T create(Class<T> classz) {
        T t = null;
        try {
            t = (T)classz.forName(classz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }
}
