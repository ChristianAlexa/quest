#!/bin/bash
# This script is used to start the app

# get the location of this script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# source the required environment variables
source ${SCRIPT_DIR}/env.sh

# env-local.sh can be used to override env.sh for local development
# env-local.sh should be ignored by git
if [ -e ${SCRIPT_DIR}/env-local.sh ]; then
    source ${SCRIPT_DIR}/env-local.sh
fi

# build resources
chmod +x ${SCRIPT_DIR}/build_resources.sh
${SCRIPT_DIR}/build_resources.sh

# run the app
npx shadow-cljs -d cider/cider-nrepl:0.26.0 watch :app
