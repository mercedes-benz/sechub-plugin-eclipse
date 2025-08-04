## Build of plugin
The build of the plugin is done inside the main repository!
Build the eclipse plugin for the main repository on your local machine by IDE mechanism.

Afterwards call the signJar script and ensure the plugin is now signed.

## Deployment
Afterwards call the deploy script inside this repository with the path to the `sechub-eclipse-update-site` folder (here is a site.xml locatedi)

Example: ./deploy.sh ~/home/your-development/sechub/ide-plugins/eclipse/sechub-eclipse-update-site

