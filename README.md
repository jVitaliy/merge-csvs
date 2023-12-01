# COMPANY ORDERS
## Build
to build project type in console:
- linux
```
gradlew build
``` 

- windows
```
gradlew.bat build
``` 

## Run
Then go to the folder `build/libs` and get the jar file.
how to run
```
java -jar merge-csvs-0.0.1.jar [OPTIONS] file1 ... fileN
```
there are some options
 - `-top=N` set max of top records (default 10)
 - `-dateFormat=MM/dd/yyyy` set date format inside csv files, (default is MM/dd/yyyy) 
 - `-skipErrors` skip lines with errors 