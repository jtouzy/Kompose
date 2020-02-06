#!/bin/sh

# Build IOS Frameworks on Xcode build
# First param must be the Xcode configuration param $CONFIGURATION

# Variables
shell_dir=$(dirname "$0")
frameworkName=shared.framework
xcode_configuration=$1

# Move to parent project
cd "$shell_dir"/../

# Generate xcode frameworks by gradlew
./gradlew :shared:packForXCode -PXCODE_CONFIGURATION=${xcode_configuration}

# Move to destination project
cd iosApp

# Clean old framework + dSYM
rm -rf ./Frameworks/"$frameworkName"
rm -rf ./Frameworks/"$frameworkName".dSYM

# Copy generated framework + dSYM
cp -R ../shared/build/xcode-frameworks/"$frameworkName" ./Frameworks/.
cp -R ../shared/build/xcode-frameworks/"$frameworkName".dSYM ./Frameworks/.
