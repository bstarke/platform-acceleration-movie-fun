#!/bin/bash

set -ex

pushd movie-fun-source
  echo "Packaging WAR"
  ./gradlew clean build -x test
popd

war_count=`find movie-fun-source/target -type f -name *.war | wc -l`

if [ $war_count -gt 1 ]; then
  echo "More than one jar found, don't know which one to deploy. Exiting"
  exit 1
fi

find movie-fun-source/target -type f -name *.war -exec cp "{}" package-output/moviefun.war \;

echo "Done packaging"
exit 0
