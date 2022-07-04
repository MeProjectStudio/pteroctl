# pteroctl

Binary to access Pterodactyl API in "Infrastructure As Code" style

Inspired by gcloud, doctl, awscli and etc.

## About
Built with [GraalVM](https://www.graalvm.org/) and [Pterodactyl4J](https://github.com/mattmalec/Pterodactyl4J)

## Examples
### Setup credentials
First you need to setup panel credentials with `pteroctl setup`
```commandline
pteroctl setup --url https://panel.example.com --api-key ptlc_0123456789ABCDEFGHIJKlmnopqr
```
This would create `pteroctl.json` file with specified credentials. 
Alternatively you can pass `-u` and `-k` options for each command explicitly
### Send commands
Send command `list` to panel server with ID `ad332323`
```commandline
pteroctl command list -s ad332323
```
You can send same command to multiple servers as follows 
```commandline
pteroctl command stop --servers  ad332323,bb585fb5
```
This command is proactive and doesn't consider remote's output or order of commands.
### Send files to server
```commandline
pteroctl upload plugins/ file.txt  -s ad332323
```
This would upload file `file.txt` to remote `plugins/` directory on server `ad332323`.

You can specify multiple servers here as well to upload the same file to all remotes.
## TODO
- [x] Single file upload
- [ ] Directory upload
- [x] Send commands (proactive command send)
- [x] Proper secret handling (API key reuse through config)
- [ ] Config Base64 encoding?
- [ ] Binary for all platforms with GraalVM
- [ ] Docker image on Alpine
- [ ] Reactive commands on WebSocket events?
- [ ] More features for instance management (server creations)