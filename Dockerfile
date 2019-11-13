FROM oracle/graalvm-ce:19.2.1 as graalvm
COPY . /home/app/chuck-norris-facts
WORKDIR /home/app/chuck-norris-facts
RUN gu install native-image
RUN native-image --no-server -cp build/libs/chuck-norris-facts-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /opt/graalvm-ce-19.2.1/jre/lib/amd64/libsunec.so /app/libsunec.so
COPY --from=graalvm /home/app/chuck-norris-facts/chuck-norris-facts /app/chuck-norris-facts
ENTRYPOINT ["/app/chuck-norris-facts", "-Djava.library.path=/app"]
