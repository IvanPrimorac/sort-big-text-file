
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;


/**
 * Short java application able to sort a 1GB text file containing line separated random integer values, using
 * only 100MB heap size.
 *
 * @author Ivan Primorac
 * @date 08-Jul-2021
 */
public class SortBigTextFile {

    private static final int ONE_GB = 1073741824;

    private static final Integer MIN = Integer.MIN_VALUE;

    private static final Integer MAX = Integer.MAX_VALUE;

    private static final String FILE_NAME = "test1";



    public static void main(String args[]) {

        // split the large file into 32 smaller files
        try {

            splitIntoFiles(FILE_NAME);

        } catch (IOException e) {

            System.out.println(("Error while reading file from disk:  " + e.getMessage() ));
        }


        // sort all resulting files
        for(int i = 1; i < 33; i++) {

            sortNumbersInFile("part" + i);
        }

        //merge all sorted files into one

        mergeSortedFiles();


        // used only to generate the input 1GB file with random numbers
//        generateBigTextFile();

    }

    /**
     * Splits the input 1GB file into 32 smaller files of the same size
     *
     * @param fileName
     * @throws IOException
     */
    public static void splitIntoFiles (final String fileName) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(fileName,"r");
        long numSplits = 32; //make it configurable
        long sourceSize = raf.length();
        long bytesPerSplit = sourceSize/numSplits ;
        long remainingBytes = sourceSize % numSplits;

        int maxReadBufferSize = 32*1024*1024; //32MB
        for(int destIx=1; destIx <= numSplits; destIx++) {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("part"+destIx));
            if(bytesPerSplit > maxReadBufferSize) {
                long numReads = bytesPerSplit/maxReadBufferSize;
                long numRemainingRead = bytesPerSplit % maxReadBufferSize;
                for(int i=0; i<numReads; i++) {
                    readWrite(raf, bw, maxReadBufferSize);
                }
                if(numRemainingRead > 0) {
                    readWrite(raf, bw, numRemainingRead);
                }
            }else {
                readWrite(raf, bw, bytesPerSplit);
            }
            bw.close();
        }
        if(remainingBytes > 0) {
            BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("part"+(numSplits+1)));
            readWrite(raf, bw, remainingBytes);
            bw.close();
        }
        raf.close();
    }

    static void readWrite(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
        byte[] buf = new byte[(int) numBytes];
        int val = raf.read(buf);
        if(val != -1) {
            bw.write(buf);
        }
    }

    /**
     * Reads all numbers from the given 32MB file into an array, sorts the numbers and writes them back to same file
     *
     * @param fileName
     */
    public static void sortNumbersInFile(final String fileName) {

        int[] numbers = null;

        try (BufferedReader br = new BufferedReader(new FileReader( fileName))) {


            //read all numbers from a file, sort them and than store them in an array
            numbers = br.lines()
                    .filter(s -> (!s.equals("") && !s.equals("-")))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

//            Arrays.sort(numbers);

        } catch (FileNotFoundException e) {

            System.out.println("File could not be found on the disk: " + e.getMessage() );

        } catch (IOException e) {

            System.out.println(("Error while reading file from disk:  " + e.getMessage() ));

        } catch (NumberFormatException e) {

            System.out.println("Line could not be mapped to integer: " + e.getMessage() );

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            // stream through the array and write the sorted numbers back to original file
            Arrays.stream(numbers)
                    .forEach(number -> {
                        try {

                            writer.write(String.valueOf(number));
                            writer.newLine();
                            writer.flush();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Merges all 32 sorted files into one resulting 1GB file
     *
     */
    public static void mergeSortedFiles() {

        try {

            //open up one writer for the resulting file and 32 readers for the parts

            BufferedWriter bwResult = new BufferedWriter(new FileWriter("result"));
            ArrayList<BufferedReader> readers = new ArrayList<>();

            BufferedReader br1 = new BufferedReader(new FileReader("part1"));
            readers.add(br1);

            BufferedReader br2 = new BufferedReader(new FileReader("part2"));
            readers.add(br2);

            BufferedReader br3 = new BufferedReader(new FileReader("part3"));
            readers.add(br3);

            BufferedReader br4 = new BufferedReader(new FileReader("part4"));
            readers.add(br4);

            BufferedReader br5 = new BufferedReader(new FileReader("part5"));
            readers.add(br5);

            BufferedReader br6 = new BufferedReader(new FileReader("part6"));
            readers.add(br6);

            BufferedReader br7 = new BufferedReader(new FileReader("part7"));
            readers.add(br7);

            BufferedReader br8 = new BufferedReader(new FileReader("part8"));
            readers.add(br8);

            BufferedReader br9 = new BufferedReader(new FileReader("part9"));
            readers.add(br9);

            BufferedReader br10 = new BufferedReader(new FileReader("part10"));
            readers.add(br10);

            BufferedReader br11 = new BufferedReader(new FileReader("part11"));
            readers.add(br11);

            BufferedReader br12 = new BufferedReader(new FileReader("part12"));
            readers.add(br12);

            BufferedReader br13 = new BufferedReader(new FileReader("part13"));
            readers.add(br13);

            BufferedReader br14 = new BufferedReader(new FileReader("part14"));
            readers.add(br14);

            BufferedReader br15 = new BufferedReader(new FileReader("part15"));
            readers.add(br15);

            BufferedReader br16 = new BufferedReader(new FileReader("part16"));
            readers.add(br16);

            BufferedReader br17 = new BufferedReader(new FileReader("part17"));
            readers.add(br17);

            BufferedReader br18 = new BufferedReader(new FileReader("part18"));
            readers.add(br18);

            BufferedReader br19 = new BufferedReader(new FileReader("part19"));
            readers.add(br19);

            BufferedReader br20 = new BufferedReader(new FileReader("part20"));
            readers.add(br20);

            BufferedReader br21 = new BufferedReader(new FileReader("part21"));
            readers.add(br21);

            BufferedReader br22 = new BufferedReader(new FileReader("part22"));
            readers.add(br22);

            BufferedReader br23 = new BufferedReader(new FileReader("part23"));
            readers.add(br23);

            BufferedReader br24 = new BufferedReader(new FileReader("part24"));
            readers.add(br24);

            BufferedReader br25 = new BufferedReader(new FileReader("part25"));
            readers.add(br25);

            BufferedReader br26 = new BufferedReader(new FileReader("part26"));
            readers.add(br26);

            BufferedReader br27 = new BufferedReader(new FileReader("part27"));
            readers.add(br27);

            BufferedReader br28 = new BufferedReader(new FileReader("part28"));
            readers.add(br28);

            BufferedReader br29 = new BufferedReader(new FileReader("part29"));
            readers.add(br29);

            BufferedReader br30 = new BufferedReader(new FileReader("part30"));
            readers.add(br30);

            BufferedReader br31 = new BufferedReader(new FileReader("part31"));
            readers.add(br31);

            BufferedReader br32 = new BufferedReader(new FileReader("part32"));
            readers.add(br32);

            boolean isFirstIteration = true;
            long[] numbers= new long[32];
            long currentMin = 0;
            List<Integer> closedReaders = new ArrayList<>();
            int readerIndex = 0;

            while(closedReaders.size() < 32) {

                //Fetch a number from each file and put them inside an array
                if(isFirstIteration) {

                    for(int i = 0; i < 32; i++) {

                        String line = readers.get(i).readLine();

                        if(!line.equals("") && !line.equals("-")) {

                            numbers[i] = Long.valueOf(Integer.parseInt(line));

                        } else {

                            line = readers.get(i).readLine();

                            numbers[i] = Long.valueOf(Integer.parseInt(line));
                        }


                    }

                    currentMin = Arrays.stream(numbers).min().getAsLong();
                    for(int i = 0; i < 32; i++) {
                        if(numbers[i] == currentMin) {
                            readerIndex = i;
                        }
                    }

                    bwResult.write(Long.toString(currentMin));
                    bwResult.newLine();
                    bwResult.flush();

                    isFirstIteration = false;

                } else {

                    //Read the next line of only that file which has had the min the iteration before
                    String nextLine = readers.get(readerIndex).readLine();

                    if(nextLine != null) {

                        if(!nextLine.equals("") && !nextLine.equals("-")) {

                            //change the number on the index where the champion (min) was found
                            numbers[readerIndex] = Long.parseLong(nextLine);
                        }
                        else {

                            //hope there won't be two blanks or hyphens in a row
                            nextLine = readers.get(readerIndex).readLine();

                            numbers[readerIndex] = Long.parseLong(nextLine);
                        }

                    } else {

                        //close the reader which has come to the end of file
                        readers.get(readerIndex).close();

                        closedReaders.add(readerIndex);

                        //set the closed reader index in array to max value of long to push
                        //it out of the game
                        numbers[readerIndex] = Long.MAX_VALUE;
                    }

                    currentMin = Arrays.stream(numbers).min().getAsLong();

                    for(int i = 0; i < 32; i++) {
                        if(numbers[i] == currentMin) readerIndex = i;
                    }

                    if(closedReaders.size() != 32) {

                        bwResult.write(Long.toString(currentMin));
                        bwResult.newLine();
                        bwResult.flush();

                    }

                }

            }

            bwResult.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }


    }


    /**
     * Opens up a new file and writes random integers inside till it gets to 1GB size
     *
     */
    public static void generateBigTextFile() {
        File file = new File(FILE_NAME);

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.ISO_8859_1)) {
            while (file.length() < ONE_GB) {
//                writer.write(getRandomNumberUsingInts(min, max));
                writer.write(String.valueOf(getRandomNumberUsingInts(MIN, MAX)));
                writer.newLine();
                writer.flush();
            }
            System.out.println("1 GB Data is written to the file.!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a random integer value from the range defined by arguments
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

}
