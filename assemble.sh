#!/bin/bash
set -xeuo pipefail

CUR_DIR=$(pwd)

cd customer/
./gradlew clean assemble
cd $CUR_DIR
cd menu/
./gradlew clean assemble
cd $CUR_DIR
cd order/
./gradlew clean assemble
cd $CUR_DIR
