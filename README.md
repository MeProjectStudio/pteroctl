# pteroctl

Binary to access Pterodactyl API in "Infrastructure As Code" style

Inspired by gcloud, doctl, awscli and etc.

## About
Built with [GraalVM](https://www.graalvm.org/) and [Pterodactyl4J](https://github.com/mattmalec/Pterodactyl4J)

## TODO
- [x] Single file upload
- [ ] Directory upload
- [x] Send commands (proactive command send)
- [x] Proper secret handling (API key reuse through config)
- [ ] Binary for all platforms with GraalVM
- [ ] Docker image on Alpine
- [ ] Reactive commands on WebSocket events?
- [ ] More features for instance management (server creations)