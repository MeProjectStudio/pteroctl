# pteroctl

Binary to access Pterodactyl API in "Infrastructure As Code" style

Inspired by gcloud, doctl, awscli and etc.

Still Work In Progress. Not ready for production use.

## About
Built with [GraalVM](https://www.graalvm.org/) and [Pterodactyl4J](https://github.com/mattmalec/Pterodactyl4J)

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