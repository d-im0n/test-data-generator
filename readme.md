How to build and run
--------------------

    mvn package

###Task 1

    cd operations-generator/target
    java -jar task1.jar offices.txt 90000 operations.txt

Copy file operations.txt to ../../operations-statistic/target

###Task 2

    cd operations-statistic/target
    java -jar task2.jar operations.txt sums-by-dates.txt sums-by-offices.txt
