#!/bin/bash

set -e

apt-get update && apt-get install -y curl

set -x

if [ -z $MOVIE_FUN_HEALTH_URL ]; then
  echo "MOVIE_FUN_HEALTH_URL not set"
  exit 1
fi

pushd movie-fun-source
  echo "Running smoke tests for Attendee Service deployed at $MOVIE_FUN_HEALTH_URL"
  smoke-tests/bin/test $MOVIE_FUN_HEALTH_URL
popd

exit 0
