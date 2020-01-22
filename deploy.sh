#!/bin/bash
docker build . -t chuck-norris-facts
mkdir -p build
docker run --rm --entrypoint cat chuck-norris-facts  /home/application/function.zip > build/function.zip

S3_BUCKET="oci-bucket-ilopmar"
STACK_NAME="ChuckNorrisFactsMnFunction1931"

aws cloudformation package --template-file sam.yaml --output-template-file output-sam.yaml --s3-bucket $S3_BUCKET

aws cloudformation deploy --template-file output-sam.yaml --stack-name $STACK_NAME --capabilities CAPABILITY_IAM

aws cloudformation describe-stacks --stack-name $STACK_NAME

# To delete everything
#aws cloudformation delete-stack --stack-name $STACK_NAME
