#!/bin/bash

echo "Building resources"

#source bulma css
cp ../node_modules/bulma/css/bulma.min.css ../resources/public/css/bulma.min.css

echo "Resources built successfully"

exit 0