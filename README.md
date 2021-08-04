## Sorting Animation

This project visualizes different kinds of sorting algorithm in animations.

It's written in java and the java bytecodes are compiled into Javascripts and [WebAssembly](https://webassembly.org) instructions using [TeaVM](https://teavm.org) and running on browser(AMAZING!).

### Demo link

https://dongliang3571.github.io/sorting-animation/

### How to run from source code

- Install [Maven](https://maven.apache.org/download.cgi)
- From the project root folder run `mvn clean package`. If the project is built succesfully, you should see a `target` folder created with `sorting-animation-1.0-SNAPSHOT.war`, `sorting-animation-1.0-SNAPSHOT` and other files in it.
- Open this html file(`./target/sorting-animation-1.0-SNAPSHOT/index.html`) in your browser and there you go.

### Screenshots

![snapshot1](https://github.com/dongliang3571/sorting-animation/blob/master/images/image1.png?raw=true)

### Currently supported sorting algorithm
- Bubble sort
- Bucket sort
- Couting sort
- Heap Sort
- Insertion sort
- Merge sort
- Quick sort
- Radix sort LSD
- Radix sort MSD
- Selection sort
