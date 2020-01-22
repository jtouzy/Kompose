#!/bin/sh

#  buildKotlinFrameworks.sh
#  DemoApp
#
#  Created by Jérémy TOUZY on 22/01/2020.
#  Copyright © 2020 jtouzy. All rights reserved.

# Variables
frameworkName=shared.framework

# Move to parent project
cd "$PROJECT_DIR"/../

# Generate xcode frameworks by gradlew
./gradlew :shared:packForXCode -PXCODE_CONFIGURATION=${CONFIGURATION}

# Move to destination project
cd "$PROJECT_DIR"

# Clean old framework + dSYM
rm -rf ./Frameworks/"$frameworkName"
rm -rf ./Frameworks/"$frameworkName".dSYM

# Copy generated framework + dSYM
cp -R ../shared/build/xcode-frameworks/"$frameworkName" ./Frameworks/.
cp -R ../shared/build/xcode-frameworks/"$frameworkName".dSYM ./Frameworks/.
