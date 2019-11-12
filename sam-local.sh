#!/bin/sh
docker build . -t chuck-norris-facts
mkdir -p build
docker run --rm --entrypoint cat chuck-norris-facts  /home/application/function.zip > build/function.zip

sam local start-api -t sam.yaml -p 3000

