#!/bin/sh
docker build . -t chuck-norris-facts
mkdir -p build
docker run --rm --entrypoint cat chuck-norris-facts  /app/chuck-norris-facts > build/server
docker run --rm --entrypoint cat chuck-norris-facts  /app/libsunec.so > build/libsunec.so

chmod +x build/server
chmod +x bootstrap
zip -j build/function.zip bootstrap build/server build/libsunec.so

sam local start-api -t sam.yaml -p 3000

