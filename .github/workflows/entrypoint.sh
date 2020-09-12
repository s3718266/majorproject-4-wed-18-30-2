chmod +x entrypoint.sh

#!/bin/sh -l

echo "Hello $1"
time=$(date)
echo "::set-output name=time::$time"
