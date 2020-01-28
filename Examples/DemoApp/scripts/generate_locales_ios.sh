#!/bin/sh

# Generate locales for iOS app

# Variables
shell_dir=$(dirname "$0")
locales_dir="$shell_dir"/../shared/locales
ios_destination="$shell_dir"/../iosApp/DemoApp

# Twine launch
twine generate-localization-file \
  "$locales_dir"/locales.twine \
  "$ios_destination"/Localizable.strings \
  --format apple \
  --lang en
