mkdir -p target/client 2>/dev/null
cd target/client
java -cp postgresql-42.7.3.jar:../../client.jar ru.ifmo.se.client.Main $1
