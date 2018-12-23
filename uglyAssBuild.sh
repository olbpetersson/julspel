#!/usr/bin/env bash
DOCKER_TAG=$(date +'%Y%-m%d%M%S') &&
cd ../julspel-frontend &&
npm run build &&
cp -r build/ ../julspel/src/main/resources/webapp &&
cd ../julspel/ &&
./gradlew clean build &&
docker build -t gotoola/julspelet:${DOCKER_TAG} . &&
docker push gotoola/julspelet:${DOCKER_TAG} &&
echo "Pushed gotoola/julspelet:$DOCKER_TAG"
