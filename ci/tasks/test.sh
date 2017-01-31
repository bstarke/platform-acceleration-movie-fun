#!/bin/bash

set -ex

pushd movie-fun-source
  echo "Fetching Dependencies"
  ./gradlew clean build -x test > /dev/null

  echo "Running Tests"
  ./gradlew test
popd

exit 0
