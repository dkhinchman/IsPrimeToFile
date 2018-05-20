import java.io.*;
import java.util.Scanner;

public class IsPrimeToFile {
    public static void main(String[] args){
        Scanner user = new Scanner(System.in);
        PrintWriter pw = null;
        int totalPrimes = 0;

        totalPrimes = howManyPrimes(user);
        String outputFileName = getOutputFileName(user);
        pw = openOutputFile(outputFileName);

        long startTime = System.currentTimeMillis();
        getPrimes(pw, totalPrimes);
        long endTime = System.currentTimeMillis();

        printStats(totalPrimes, startTime, endTime, pw);

        pw.close();
    }

    private static void printStats(int totalPrimes, long startTime, long endTime, PrintWriter pw) {
        pw.println("There are " + totalPrimes + " primes");
        if(endTime - startTime <= 999)
            pw.println("It took " + (endTime - startTime) + " milliseconds");
        if(((endTime - startTime) >= 1000) && ((endTime - startTime) < 59999))
            pw.println("It took " + (endTime - startTime) / (double)1000 + " seconds");
    }

    private static PrintWriter openOutputFile(String outputFileName) {
        PrintWriter pw = null;

        try {
            File file = new File(outputFileName);
            FileWriter fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
        }catch(IOException e) {
            e.printStackTrace();
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return pw;
    }

    private static String getOutputFileName(Scanner user) {
        System.out.println("Enter output file name(ex: output.txt):");
        String outputFileName = user.nextLine();
        return outputFileName;
    }

    private static void getPrimes(PrintWriter pw, int totalPrimes) {
        boolean isPrime = false;
        int start = 0;
        int totalPrimesFound = 0;

        while(totalPrimesFound <= totalPrimes){
            isPrime = testPrime(start);
            if(isPrime) {
                pw.println(start);
                totalPrimesFound++;
            }
            start++;
            isPrime = false;
        }
    }

    private static boolean testPrime(int start) {
        if(start == 2)
            return true;
        if(start == 0 || start == 1 || start == 4)
                return false;
        for(int i = 2; i < start/2; i++){
            if(start % i == 0)
                return false;
        }
        return true;
    }

    private static int howManyPrimes(Scanner user) {
        System.out.println("How many Primes would you like?");
        int totalPrimes = user.nextInt();
        user.nextLine();
        return totalPrimes;
    }
}
