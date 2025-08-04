#!/bin/bash
# SPDX-License-Identifier: MIT

set -e


# Get the directory of the script
script_folder=$(dirname "$(realpath "$0")")

# Check if a parameter was provided
if [ -z "$1" ]; then
    echo "Please specify as first parameter the location of the sechub-eclipse-update-site folder."
    exit 1
fi

# Store the first parameter as the source directory
source_folder="$1"
destination_folder="$script_folder/update-site"

# Check if the specified directory exists and contains site.xml
if [ -d "$source_folder" ] && [ -f "$source_folder/site.xml" ]; then
    # Create the destination directory if it doesn't exist
    mkdir -p "$destination_folder"
    
    # Copy the contents of the source directory to the destination directory
    cp -rf "$source_folder/features/"* "$destination_folder/features"
    cp -rf "$source_folder/plugins/"* "$destination_folder/plugins"
    cp -f "$source_folder/artifacts.jar" "$destination_folder/artifacts.jar"
    cp -f "$source_folder/content.jar" "$destination_folder/content.jar"
    cp -f "$source_folder/site.xml" "$destination_folder/site.xml"
    echo "Contents of $source_folder have been copied to $destination_folder."

    cd $destination_folder
    git status

    # Prompt the user for confirmation
    read -p "Do you want to add + push the changes? (yes/y to confirm): " response

    # Check the user's response
    if [[ "$response" == "yes" || "$response" == "y" ]]; then
        git add --all
        git commit
        git push
        echo "Changes have been pushed."
    else
        echo "Push canceled."
    fi

else
    echo "The specified directory does not exist or does not contain site.xml."
    exit 1
fi




