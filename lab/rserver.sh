mkdir -p target/server 2>/dev/null
cd target/server
java -cp postgresql-42.7.3.jar:../../server.jar ru.ifmo.se.server.Main
