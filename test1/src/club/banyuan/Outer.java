package club.banyuan;

class Outer{
    public static void main(String[] args){
        new Outer().new Inner();
        Outer outer = new Outer();
    }

    public Outer() {
        Inner inner = new Inner();
    }

    class Inner {
        Inner() {}


    }

    static class ll{
        public static void main(String[] args){

        }
        private class Inner{
            Inner() {}
        }
    }
}


