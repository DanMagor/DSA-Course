/**
 * Created by anybi on 22.01.2017.
 */
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MergeSorterTest {
    private int[] numbers;
    private  final static int SIZE =10000;
    private final static int MAX = 100;

    @Before
    public void setUp() throws Exception{
        numbers = new int[SIZE];
        Random generator = new Random();
        for (int i = 0; i< numbers.length;i++){
            numbers[i] = generator.nextInt(MAX);
        }
    }
    @Test
    public void testMergeSort(){
        long startTime = System.currentTimeMillis();

        Main.MergeSort(numbers,0,SIZE-1);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("MergeSort " + elapsedTime);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                fail("Should not happen");
            }
        }
        assertTrue(true);
    }
    @Test
    public  void itWorksRepeatably() {
        for(int i =0;i<200;i++){
            numbers = new int[SIZE];
            Random generator = new Random();
            for (int a=0; a<numbers.length; a++){
                numbers[a] = generator.nextInt(MAX);
            }
            Main.MergeSort(numbers,0,SIZE-1);
            for (int j = 0; j<numbers.length - 1; j++){
                if (numbers[j] > numbers[j+1]){
                    fail("Should not happen");
                }
            }
            assertTrue(true);
        }
    }
    @Test
    public void testStandartSort(){
        long startTime = System.currentTimeMillis();
        Arrays.sort(numbers);
        long stopTime  = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Standart Java sort " + elapsedTime);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                fail("Should not happen");
            }
        }
        assertTrue(true);
    }
}
