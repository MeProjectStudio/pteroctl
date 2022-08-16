# pteroctl

Binary to access Pterodactyl API in "Infrastructure As Code" style

Inspired by gcloud, doctl, awscli and etc.

## About
Built with [GraalVM](https://www.graalvm.org/) and [Pterodactyl4J](https://github.com/mattmalec/Pterodactyl4J)

## Examples
**Before running any command you need to set up credential file for pteroctl to work**

This can be done with `pteroctl setup` command like so:
```commandline
pteroctl setup --url https://panel.example.com --api-key ptlc_0123456789ABCDEFGHIJKlmnopqr
```
This would create `pteroctl.json` file with specified credentials.
This design allows reuse of config file. For example, we can be added it as CI/CD variable to the pipeline.

Alternatively you can pass `-u` and `-k` options for each command explicitly.
### Send commands
Send command `list` to panel server with ID `ad332323`
```commandline
pteroctl command list -s ad332323
```
You can specify multiple servers on most commands to run against by iterating them with comma.
So to send command `stop` to servers `ad332323` and `bb585fb5` you need the following:
```commandline
pteroctl command stop --servers ad332323,bb585fb5
```
`pteroctl command` has proactive behaviour. It sends the command without checking the results of said command.
If you need more reactive behaviour look into WIP.

## File management
### Remove file/directory
You can delete remote file resource (either file or directory) with path `file/to/delete` for server `ad332323` the following way:
```commandline
pteroctl file rm file/to/delete -s ad332323
```
Note that `pteroctl file` commands consider `/home/container/` as root path, so paths to needed resources need to be specified relatively to it.
Meaning that actual path to file from example would be `/home/container/file/to/delete`.

## Building
## Ubuntu, MacOS
Run `nativeCompile` task

## Windows
Run `nativeCompile` task from x64 Native Tools Command Prompt for VS 2022

## TODO
- [ ] File Management
  - [x] Remove file/directory
  - [x] Upload file 
  - [ ] Compress/Decompress
  - [ ] Download file
- [ ] Send commands
  - [x] Proactive command send
  - [ ] Reactive command send (WebSocket action)
- [x] Proper secret handling (Reuse of an API key with config)
  - [ ] Config Base64 encoding for secrets
- [x] Binary for all platforms with GraalVM
- [ ] Docker image on Alpine
- [ ] Instance management (server creation and etc.)