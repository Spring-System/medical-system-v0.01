#!/bin/bash

ROOT_DIR=$(pwd)

for project in $(find "$ROOT_DIR" -name "pom.xml" -exec dirname {} \;); do
    echo "Building project in $project"
l
    (
        cd "$project" || exit

        mvn clean package

        if [ $? -ne 0 ]; then
            echo "Build failed for $project"
            exit 1
        else
            echo "Build succeeded for $project"
        fi
    )

done

echo "Running Docker Compose..."
docker-compose up --build

if [ $? -ne 0 ]; then
    echo "Docker Compose failed"
    exit 1
else
    echo "Docker Compose started successfully!"
fi
