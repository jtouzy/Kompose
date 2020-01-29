#!/usr/bin/env python3

import os
import sys
import shutil
import datetime

# ####################
# General utils
# ####################

class Colors:
    BOLD = '\033[1m'
    GREEN = '\033[92m'
    FAIL = '\033[91m'
    END = '\033[0m'


def exit_with_error(message):
    print(Colors.FAIL + "ERROR: " + message + "." + Colors.END)
    print("")
    sys.exit(1)


# ####################
# Scripts utils
# ####################

zip_extract_path = '/Users/JTO/develop/github/Kompose/KomposeCli/Templates'


class ProjectData:
    def __init__(self, identifier, path):
        self.identifier = identifier
        self.path = path


class ProjectFilesMaker:
    def __init__(self, project_data):
        self.project_data = project_data

    def copyfile_to_project(self, file, replacements=True):
        destination_file = os.path.join(self.project_data.path, file)
        os.makedirs(os.path.dirname(destination_file), exist_ok=True)
        shutil.copyfile(os.path.join(zip_extract_path, file), destination_file)
        if replacements:
            self.do_replacements_infile(destination_file)

    def do_replacements_infile(self, destination_file):
        file_data = self.get_file_data(destination_file)
        file_data = self.make_replacements(file_data)
        self.write_file_data(destination_file, file_data)

    def make_replacements(self, file_data):
        data = file_data
        # Generic data
        data = data.replace('$$AUTHOR$$', self.project_data.identifier)
        data = data.replace('$$YEAR$$', str(datetime.datetime.now().year))
        # Versions: Build
        data = data.replace('$$VERSION_KOTLIN$$', '1.3.61')
        data = data.replace('$$VERSION_ANDROID_GRADLE$$', '4.0.0-alpha09')
        data = data.replace('$$VERSION_XCODE_SYNC$$', '0.2')
        # Versions: Android
        data = data.replace('$$VERSION_ANDROID_MIN_SDK$$', '21')
        data = data.replace('$$VERSION_ANDROID_TARGET_SDK$$', '29')
        data = data.replace('$$VERSION_ANDROID_COMPILE_SDK$$', '29')
        # Versions: Libs
        data = data.replace('$$VERSION_LIBS_TIMBER$$', '4.7.1')
        data = data.replace('$$VERSION_LIBS_KOIN$$', '2.0.1')
        data = data.replace('$$VERSION_LIBS_COIL$$', '0.9.2')
        data = data.replace('$$VERSION_LIBS_JSR310$$', '1.2.1')
        # Versions: AndroidX
        data = data.replace('$$VERSION_ANDROIDX_APPCOMPAT$$', '1.1.0')
        data = data.replace('$$VERSION_ANDROIDX_COREKTX$$', '1.1.0')
        data = data.replace('$$VERSION_ANDROIDX_COMPOSE$$', '0.1.0-dev03')
        # Versions: Coroutines
        data = data.replace('$$VERSION_KOTLINX_COROUTINES$$', '1.3.3')
        # Versions: Serialization
        data = data.replace('$$VERSION_KOTLINX_SERIALIZATION$$', '0.14.0')
        # Versions: Ktor
        data = data.replace('$$VERSION_KTOR$$', '1.2.6')
        return data

    def get_file_data(self, destination_file):
        with open(destination_file) as file:
            file_data = file.read()
        return file_data

    def write_file_data(self, destination_file, data):
        with open(destination_file, 'w') as file:
            file.write(data)


def recursive_ask_for_project_identifier():
    project_identifier = input("[1] Your project identifier (example: DemoApp): ")
    if not project_identifier:
        return recursive_ask_for_project_identifier()
    return project_identifier


# ####################
# Script steps
# ####################

def print_title():
    print("")
    print(Colors.GREEN + Colors.BOLD + ">> Creating a new multi-platform project with Kompose" + Colors.END)
    print("")


def ask_for_options():
    project_identifier = recursive_ask_for_project_identifier()
    project_path = input("[2] Your project absolute path (Enter for this location): ")
    if not project_path:
        project_path = os.getcwd()
    return ProjectData(project_identifier, os.path.join(project_path, project_identifier))


def create_project_directory(path):
    print("")
    print("Creating project directory... " + path)
    if os.path.exists(path):
        exit_with_error("Project directory already exists")
    else:
        os.mkdir(path)


def copy_structure_files(project_path):
    project_maker = ProjectFilesMaker(project_path)
    # # BASE FOLDER
    # Gradle binary + conf files
    project_maker.copyfile_to_project("gradlew", replacements=False)
    project_maker.copyfile_to_project("gradlew.bat", replacements=False)
    project_maker.copyfile_to_project("gradle.properties")
    # Project modules definition
    project_maker.copyfile_to_project("settings.gradle.kts")
    # Build options
    project_maker.copyfile_to_project("build.gradle.kts")
    # Gitignore
    project_maker.copyfile_to_project(".gitignore", replacements=False)
    # # FOLDER: buildSrc
    project_maker.copyfile_to_project(os.path.join("buildSrc", "build.gradle.kts"), replacements=True)
    project_maker.copyfile_to_project(os.path.join("buildSrc", "src", "main", "kotlin", "Config.kt"), replacements=True)


# ####################
# Main
# ####################

print_title()
project_options = ask_for_options()
create_project_directory(project_options.path)
copy_structure_files(project_options)
