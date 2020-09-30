package club.banyuan;
class OuterInnerStatic {
    static String s1 = "Java";
    static String s2 = "2";
    public static void main(String[] args) {
        Inner inner = new Inner();
    }

    static class Inner {
        String s1 = "Certification";
        String s2 = "Exam";
        Inner() {
            System.out.println(OuterInnerStatic.s1);
            System.out.println(this.s1);
            System.out.println(s2);
        }
    }
}
