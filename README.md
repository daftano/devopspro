# Demo docker container with simple node.js app

Quick startup 

~~~~
$ docker build -t appx:0.1.0 .
~~~~


~~~~
$ docker run --rm -d -p 8080:8080 -it appx:0.1.0
~~~~


## To change a app port 

~~~~
$ docker run --rm -d -e PORT=7000 -p 7000:7000 -it appx:0.1.0

$ curl http://localhost:7000/
Hello from 736a75394291
~~~~


