class Test {

    void test1 () {
        String str = "HeyThereIAmHere";
        String current = null;
        for(int i = 0 ; i < str.length(); i++) {
            char c = str.charAt(i);
            if((int)c >= 65 && (int)c<=90) {
                if (current != null)
                    System.out.println(current);
                current = "" + c;
            } else {
                current = current + "" + c;
            }
        }
        System.out.println(current);
    }

    public void test2(int position) {
        int hundredMultiples = 10;
        if (position > 1) {
            for (int i = 1; i < position; i++)
                hundredMultiples = hundredMultiples * 10;
        }
        //input: 12345
        //expected output: 45123
        int number = 12345;
        int rem = number % hundredMultiples;
        Integer rem2 = number / hundredMultiples;


        int num = 10;
        for (int i = 1; i<rem2.toString().length(); i++)
            num *= 10;
        rem *= num;
        System.out.println(rem + rem2);
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.test2(1);
    }
}