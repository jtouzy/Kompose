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
    def __init__(self, identifier, package, path):
        self.identifier = identifier
        self.package = package + '.' + identifier.lower()
        self.path = path


class ProjectFilesMaker:
    def __init__(self, project_data):
        self.project_data = project_data

    def movefile_to_project(self, file, new_file, replacements=True, enable_permissions=False):
        destination_file = os.path.join(self.project_data.path, new_file)
        os.makedirs(os.path.dirname(destination_file), exist_ok=True)
        shutil.copyfile(os.path.join(zip_extract_path, file), destination_file)
        if replacements:
            self.do_replacements_infile(destination_file)
        if enable_permissions:
            os.chmod(destination_file, 0o777)

    def copyfile_to_project(self, file, replacements=True, enable_permissions=False):
        self.movefile_to_project(file, file, replacements, enable_permissions)

    def movefolder_to_project(self, folder, new_folder):
        destination_folder = os.path.join(self.project_data.path, new_folder)
        shutil.copytree(os.path.join(zip_extract_path, folder), destination_folder)

    def do_replacements_infile(self, destination_file):
        file_data = ProjectFilesMaker.get_file_data(destination_file)
        file_data = self.make_replacements(file_data)
        ProjectFilesMaker.write_file_data(destination_file, file_data)

    def make_replacements(self, file_data):
        data = file_data
        # Generic data
        data = data.replace('$$PROJECT_IDENTIFIER$$', self.project_data.identifier)
        data = data.replace('$$PROJECT_PACKAGE$$', self.project_data.package)
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

    @staticmethod
    def get_file_data(destination_file):
        with open(destination_file) as file:
            file_data = file.read()
        return file_data

    @staticmethod
    def write_file_data(destination_file, data):
        with open(destination_file, 'w') as file:
            file.write(data)


def recursive_ask_for_input(message):
    response = input(message)
    if not response:
        return recursive_ask_for_input(message)
    return response


# ####################
# Script steps
# ####################

def print_title():
    print("")
    print(Colors.GREEN + Colors.BOLD + ">> Creating a new multi-platform project with Kompose" + Colors.END)
    print("")


def ask_for_options():
    project_identifier = recursive_ask_for_input("[1] Your project identifier (example: DemoApp): ")
    project_package = recursive_ask_for_input("[2] Your project package (example: com.jtouzy): ")
    project_path = input("[2] Your project absolute path ('Enter' for this location): ")
    if not project_path:
        project_path = os.getcwd()
    return ProjectData(project_identifier, project_package, os.path.join(project_path, project_identifier))


def create_project_directory(path):
    print("")
    print("Creating project directory... " + path)
    if os.path.exists(path):
        exit_with_error("Project directory already exists")
    else:
        os.mkdir(path)


def copy_structure_files(project_data):
    print("Generating files...")
    project_maker = ProjectFilesMaker(project_data)
    project_package_path_items = project_data.package.split('.')
    # # BASE FOLDER
    # Gradle binary + conf files
    project_maker.copyfile_to_project("gradlew", replacements=False, enable_permissions=True)
    project_maker.copyfile_to_project("gradlew.bat", replacements=False, enable_permissions=True)
    project_maker.copyfile_to_project("gradle.properties")
    project_maker.copyfile_to_project(os.path.join("gradle", "wrapper", "gradle-wrapper.jar"), replacements=False)
    project_maker.copyfile_to_project(os.path.join("gradle", "wrapper", "gradle-wrapper.properties"))
    # Project modules definition
    project_maker.copyfile_to_project("settings.gradle.kts")
    # Build options
    project_maker.copyfile_to_project("build.gradle.kts")
    # Gitignore
    project_maker.copyfile_to_project(".gitignore")
    # # FOLDER: buildSrc
    project_maker.copyfile_to_project(os.path.join("buildSrc", "build.gradle.kts"))
    project_maker.copyfile_to_project(os.path.join("buildSrc", "src", "main", "kotlin", "Config.kt"))
    # # FOLDER: scripts
    project_maker.copyfile_to_project(os.path.join("scripts", "generate_locales_ios.sh"), enable_permissions=True)
    project_maker.copyfile_to_project(os.path.join("scripts", "build_ios_frameworks.sh"), enable_permissions=True)
    # # FOLDER: shared
    project_maker.copyfile_to_project(os.path.join("shared", "locales", "locales.twine"))
    project_maker.copyfile_to_project(os.path.join("shared", "build.gradle.kts"))
    # # FOLDER: shared/common
    base_path = ["shared", "src", "commonMain", "kotlin"]
    move_coroutines_file(project_maker, base_path, project_package_path_items)
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "utils", "coroutines.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["utils", "coroutines.kt"]))
    )
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "ui", "Store.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["ui", "Store.kt"]))
    )
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "ui", "ViewState.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["ui", "ViewState.kt"]))
    )
    # # FOLDER: shared/ios
    base_path = ["shared", "src", "iosMain", "kotlin"]
    move_coroutines_file(project_maker, base_path, project_package_path_items)
    # # FOLDER: shared/android
    base_path = ["shared", "src", "androidMain", "kotlin"]
    move_coroutines_file(project_maker, base_path, project_package_path_items)
    project_maker.copyfile_to_project(os.path.join("shared", "src", "androidMain", "AndroidManifest.xml"))
    # # FOLDER: androidApp
    copy_android_app_files(project_maker, project_package_path_items)
    # # FOLDER: iosApp
    copy_ios_app_files(project_maker)


def move_coroutines_file(project_maker, base_path, project_package_path_items):
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "utils", "coroutines.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["utils", "coroutines.kt"]))
    )


def copy_android_app_files(project_maker, project_package_path_items):
    project_maker.copyfile_to_project(os.path.join("androidApp", "build.gradle.kts"))
    project_maker.copyfile_to_project(os.path.join("androidApp", "proguard-rules.pro"))
    project_maker.copyfile_to_project(os.path.join("androidApp", ".gitignore"))
    project_maker.copyfile_to_project(os.path.join("androidApp", "src", "main", "AndroidManifest.xml"))
    base_path = ["androidApp", "src", "main", "kotlin"]
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "app", "App.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["app", "App.kt"]))
    )
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "app", "di", "appModule.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["app", "di", "appModule.kt"]))
    )
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "app", "ui", "MainActivity.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["app", "ui", "MainActivity.kt"]))
    )
    project_maker.movefile_to_project(
        os.path.join(*(base_path + ["package", "app", "ui", "ObservableStore.kt"])),
        os.path.join(*(base_path + project_package_path_items + ["app", "ui", "ObservableStore.kt"]))
    )


def copy_ios_app_files(project_maker):
    project_identifier = project_maker.project_data.identifier
    move_ios_file(project_maker, ["AppDelegate.swift"])
    project_maker.movefolder_to_project(
        os.path.join("iosApp", "App", "Assets.xcassets"),
        os.path.join("iosApp", project_identifier, "Assets.xcassets")
    )
    project_maker.movefolder_to_project(
        os.path.join("iosApp", "App", "Base.lproj"),
        os.path.join("iosApp", project_identifier, "Base.lproj")
    )
    move_ios_file(project_maker, ["Info.plist"])
    move_ios_file(project_maker, ["Localizable.strings"])
    move_ios_file(project_maker, ["SceneDelegate.swift"])
    move_ios_file(project_maker, ["UI", "ObservableStore.swift"])
    move_ios_file(project_maker, ["Modules", "BaseView.swift"])
    project_maker.movefolder_to_project(
        os.path.join("iosApp", "App.xcodeproj"),
        os.path.join("iosApp", project_identifier + ".xcodeproj")
    )
    project_maker.do_replacements_infile(
        os.path.join(project_maker.project_data.path, "iosApp", project_identifier + ".xcodeproj", "project.pbxproj")
    )
    project_maker.do_replacements_infile(
        os.path.join(
            project_maker.project_data.path,
            "iosApp",
            project_identifier + ".xcodeproj",
            "project.xcworkspace",
            "contents.xcworkspacedata"
        )
    )

    os.mkdir(os.path.join(project_maker.project_data.path, "iosApp", "Frameworks"))


def move_ios_file(project_maker, file_name):
    project_maker.movefile_to_project(
        os.path.join(*(["iosApp", "App"] + file_name)),
        os.path.join(*(["iosApp", project_maker.project_data.identifier] + file_name))
    )


# ####################
# Main
# ####################

print_title()
project_options = ask_for_options()
create_project_directory(project_options.path)
copy_structure_files(project_options)
