docker stop JavaDoc
docker rm JavaDoc
javadoc -d ./JavaDoc *.java
docker run --name JavaDoc -v ./JavaDoc:/usr/share/nginx/html:ro -p 5555:80 -d nginx