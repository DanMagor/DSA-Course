/**
 * Created by anybi on 21.01.2017.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Winners {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int n,m;
        n = in.nextInt();
        m = in.nextInt();
        int i,fact_n,fact_m,fact_nm;
        fact_n = 1; fact_m = 1; fact_nm = 1;
        for (i=1;i<=n;i++){
            fact_n = fact_n*i;
        }



        for (i=1;i<=(n-m);i++){
            fact_nm = fact_nm*i;
        }
        int res;
        res = fact_n / (fact_nm);
        System.out.println("res = "+ res);

        FileWriter writer = new FileWriter("output.txt",true);
        String s = Integer.toString(res);
        System.out.println(s);
        writer.write(s);
        writer.close();

    }
}
