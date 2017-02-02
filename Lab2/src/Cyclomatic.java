
public class Cyclomatic {

    void foo() {
        int i = 0;
        if (i > 0) {
            System.out.print("Positive");
        } else if (i < 0) {
            System.out.print("Negative");
        } else {
            System.out.print("Zero");
        }

        switch ((int)Math.signum(i)) {
            case 1:
                System.out.print("Positive");
                break;
            case -1:
                System.out.print("Negative");
                break;
            case 0:
                System.out.print("Zero");
                break;
        }
    }
}
