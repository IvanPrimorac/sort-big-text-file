# sort-big-text-file
Basic Java application which sorts line separated integer values inside a given 1024 MB text file using a memory pool limited to 100 MB (-Xmx100m).

To test the application do the following:

1. Pull the repository to your local machine and open it in an editor.
2. Open SortBigTextFile.java and comment out lines 30-49 and uncomment line 52.
3. Run the program, and a 1GB file containing line separated random integer values will be created inside the project root directory.
4. Return back to SortBigTextFile.java, uncomment lines 30-49, and comment line 52.
5. Run the program using one of the following ways:

    a.) If you are using Intellij Idea or Eclipse IDE, edit configuration, set "-Xmx100m" VM option, and press the Run button
    
    b.) Open the project root directory in terminal, hit mvn clean install to create a jar file, and run that file like this: java -Xmx100m -jar sort-big-text-file-1.0-SNAPSHOT
    
6. Wait for 10-20 minutes because this is a lot of work, and a new file named "result" will be created containing a 1GB file with sorted numbers inside.
