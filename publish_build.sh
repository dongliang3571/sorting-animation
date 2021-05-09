#!/bin/bash

mvn clean package
cp target/generated/js/teavm/classes.js docs/teavm/classes.js
cp target/sorting-animation-1.0-SNAPSHOT/index.html docs/index.html
git add docs/teavm/classes.js
git add docs/index.html
git commit -m "update build"
git push origin master
