#!/bin/bash

mvn clean package
cp target/generated/js/teavm/classes.js docs/teavm/classes.js
git add docs/teavm/classes.js
git commit -m "update build"
git push origin master
